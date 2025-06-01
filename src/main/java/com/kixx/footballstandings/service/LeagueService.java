package com.kixx.footballstandings.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.kixx.footballstandings.client.APIFootballClient;
import com.kixx.footballstandings.config.ApiProperties;
import com.kixx.footballstandings.model.League;
import com.kixx.footballstandings.response.LeagueResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeagueService {
private final APIFootballClient apiFootballClient;
    private final ApiProperties apiProperties;

    private final Map<String, Set<League>> leagueCache = new HashMap<>();

    public LeagueResponse getAllLeagues(String countryId, boolean refresh) {
        log.info("Fetching leagues");
        LeagueResponse response = new LeagueResponse();
        try {
            if(!refresh && !leagueCache.isEmpty() && leagueCache.containsKey(countryId)) {
                log.info("Using cache");
                response.setLeagues(new ArrayList<>(leagueCache.get(countryId)));
                return response;
            }
            log.info("Fetching leagues from API");
            log.info("API Key: " + apiProperties.getApiFootball().getApiKey());
            List<League> apiFootballLeaguesResponse = apiFootballClient.getLeagues(apiProperties.getApiFootball().getApiKey(), "get_leagues", countryId);
            log.info("Fetched leagues from API size: " + apiFootballLeaguesResponse.size());
            leagueCache.put(countryId, new HashSet<>(apiFootballLeaguesResponse));
            response.setLeagues(apiFootballLeaguesResponse);
            return response;
        }
        catch(Exception e) {
            log.error("Error fetching leagues", e);
            return getFallbackData();
        }
        
    }

    public LeagueResponse getFallbackData() {
        LeagueResponse response = new LeagueResponse();
        League league = new League("Fallback", "unknown", "unknown", "unknown", "unknown", "unknown", "unknown");
        response.setLeagues(List.of(league));
        return response;
    }
}
