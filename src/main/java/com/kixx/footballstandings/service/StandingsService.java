package com.kixx.footballstandings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.kixx.footballstandings.client.APIFootballClient;
import com.kixx.footballstandings.config.ApiProperties;
import com.kixx.footballstandings.model.Standings;
import com.kixx.footballstandings.response.StandingsResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StandingsService {
    private final APIFootballClient apiFootballClient;
    private final ApiProperties apiProperties;

    private final Set<Standings> standingsCache = new HashSet<>();

    public StandingsResponse getAllStandings(String leagueId, boolean refresh) {
        log.info("Fetching standings");
        StandingsResponse response = new StandingsResponse();
        try {
            if(!refresh && !standingsCache.isEmpty()) {
                log.info("Using cache");
                response.setStandings(new ArrayList<>(standingsCache));
                return response;
            }
            log.info("Fetching standings from API");
            log.info("API Key: " + apiProperties.getApiFootball().getApiKey());
            List<Standings> apiFootballStandingsResponse = apiFootballClient.getStandings(apiProperties.getApiFootball().getApiKey(), "get_standings", leagueId);
            log.info("Fetched standings from API size: " + apiFootballStandingsResponse.size());
            standingsCache.addAll(apiFootballStandingsResponse);
            response.setStandings(apiFootballStandingsResponse);
            return response;
        }
        catch(Exception e) {
            log.error("Error fetching standings", e);
            return getFallbackData();
        }
    }

    public StandingsResponse getFallbackData() {
        StandingsResponse response = new StandingsResponse();
        Standings fallbackStandings = new Standings("Fallback", "Fallback League", "unknown", "unknown", "unknown", "unknown", "unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown");
        response.setStandings(List.of(fallbackStandings));
        return response;
    }
}
