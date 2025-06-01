package com.kixx.footballstandings.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kixx.footballstandings.model.Country;
import com.kixx.footballstandings.model.League;
import com.kixx.footballstandings.model.Standings;

@Component
public class APIFootballClientFallback implements APIFootballClient{
    @Override
    public List<Country> getCountries(String apiKey, String action) {
        List<Country> response = new ArrayList<>();
        Country fallbackCountry = new Country("Fallback", "Fallback Country", "unknown");
        response.add(fallbackCountry);
        return response;
    }

    @Override
    public List<League> getLeagues(String apiKey, String action, String countryId) {
        List<League> response = new ArrayList<>();
        League fallbackLeague = new League("Fallback", "Fallback League", "unknown", "unknown", "unknown", "unknown", "unknown");
        response.add(fallbackLeague);
        return response;
    }

    @Override
    public List<Standings> getStandings(String apiKey, String action, String leagueId) {
        List<Standings> response = new ArrayList<>();
        Standings fallbackStandings = new Standings("Fallback", "Fallback League", "unknown", "unknown", "unknown", "unknown", "unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown","unknown");
        response.add(fallbackStandings);
        return response;
    }
}
