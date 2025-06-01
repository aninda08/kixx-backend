package com.kixx.footballstandings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kixx.footballstandings.response.CountryResponse;
import com.kixx.footballstandings.service.CountryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/football-standings")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    @Operation(summary = "Get country list")
    public ResponseEntity<CountryResponse> getCountryList(@RequestParam(defaultValue = "false") boolean refresh) {
        long start = System.currentTimeMillis();
        CountryResponse response = countryService.getAllCountries(refresh);
        long end = System.currentTimeMillis();
        response.setTimeTaken(end - start);
        return ResponseEntity.ok(response);
    }
}
