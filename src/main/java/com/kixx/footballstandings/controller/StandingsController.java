package com.kixx.footballstandings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kixx.footballstandings.response.StandingsResponse;
import com.kixx.footballstandings.service.StandingsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/football-standings")
public class StandingsController {
    @Autowired
    private StandingsService standingsService;

    @GetMapping("/standings")
    @Operation(summary = "Get standings")
    public ResponseEntity<StandingsResponse> getStandings(@RequestParam String leagueId, @RequestParam(defaultValue = "false") boolean refresh) {
        long start = System.currentTimeMillis();
        StandingsResponse response = standingsService.getAllStandings(leagueId, refresh);
        long end = System.currentTimeMillis();
        response.setTimeTaken(end - start);
        return ResponseEntity.ok(response);
    }
}
