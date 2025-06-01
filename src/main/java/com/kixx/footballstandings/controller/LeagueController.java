package com.kixx.footballstandings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kixx.footballstandings.response.LeagueResponse;
import com.kixx.footballstandings.service.LeagueService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/football-standings")
public class LeagueController {
    @Autowired
    private LeagueService leagueService;

    @GetMapping("/leagues")
    @Operation(summary = "Get league list")
    public ResponseEntity<LeagueResponse> getLeagueList(@RequestParam String countryId, @RequestParam(defaultValue = "false") boolean refresh) {
        long start = System.currentTimeMillis();
        LeagueResponse response = leagueService.getAllLeagues(countryId, refresh);
        long end = System.currentTimeMillis();
        response.setTimeTaken(end - start);
        return ResponseEntity.ok(response);
    }
}
