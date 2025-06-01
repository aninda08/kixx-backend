package com.kixx.footballstandings.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kixx.footballstandings.model.Country;
import com.kixx.footballstandings.model.League;
import com.kixx.footballstandings.model.Standings;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "api-football", url = "${api-football.url}", fallback = APIFootballClientFallback.class)
public interface APIFootballClient {
    @GetMapping
    @CircuitBreaker(name = "apiFootballCountryService", fallbackMethod = "fallbackGetCountries")
    @Retry(name = "apiFootballCountryService")
    List<Country> getCountries(
        @RequestParam("APIkey") String apiKey,
        @RequestParam("action") String action
    );

    @GetMapping
    @CircuitBreaker(name = "apiFootballService", fallbackMethod = "fallbackGetLeagues")
    @Retry(name = "apiFootballService")
    List<League> getLeagues(
        @RequestParam("APIkey") String apiKey,
        @RequestParam("action") String action,
        @RequestParam("country_id") String countryId
    );

    @GetMapping
    @CircuitBreaker(name = "apiFootballService", fallbackMethod = "fallbackGetStandings")
    @Retry(name = "apiFootballService")
    List<Standings> getStandings(
        @RequestParam("APIkey") String apiKey,
        @RequestParam("action") String action,
        @RequestParam("league_id") String leagueId
    );
}
