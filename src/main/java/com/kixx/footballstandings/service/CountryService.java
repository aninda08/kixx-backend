package com.kixx.footballstandings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.kixx.footballstandings.client.APIFootballClient;
import com.kixx.footballstandings.config.ApiProperties;
import com.kixx.footballstandings.model.Country;
import com.kixx.footballstandings.response.CountryResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryService {
    private final APIFootballClient apiFootballClient;
    private final ApiProperties apiProperties;

    private final Set<Country> countryCache = new HashSet<>();

    public CountryResponse getAllCountries(boolean refresh) {
        log.info("Fetching countries");
        CountryResponse response = new CountryResponse();
        try {
            if(!refresh && !countryCache.isEmpty()) {
                log.info("Using cache");
                response.setCountries(new ArrayList<>(countryCache));
                return response;
            }
            log.info("Fetching countries from API");
            log.info("API Key: " + apiProperties.getApiFootball().getApiKey());
            List<Country> apiFootballCountriesResponse = apiFootballClient.getCountries(apiProperties.getApiFootball().getApiKey(), "get_countries");
            log.info("Fetched countries from API size: " + apiFootballCountriesResponse.size());
            countryCache.addAll(apiFootballCountriesResponse);
            response.setCountries(apiFootballCountriesResponse);
            return response;
        }
        catch(Exception e) {
            log.error("Error fetching countries", e);
            return getFallbackData();
        }
        
    }

    public CountryResponse getFallbackData() {
        CountryResponse response = new CountryResponse();
        Country country = new Country("Fallback", "unknown", "unknown");
        response.setCountries(List.of(country));
        return response;
    }
}
