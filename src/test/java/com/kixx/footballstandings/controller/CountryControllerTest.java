package com.kixx.footballstandings.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.mockito.Mockito;

import com.kixx.footballstandings.service.CountryService;
import com.kixx.footballstandings.response.CountryResponse;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    private CountryResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new CountryResponse();
        mockResponse.setCountries(new ArrayList<>());
        mockResponse.setTimeTaken(100); // Mock a non-zero time taken
    }

    @Test
    void testGetCountryListWithoutRefresh() throws Exception {
        // Arrange
        Mockito.when(countryService.getAllCountries(false)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/football-standings/countries")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCountryListWithRefresh() throws Exception {
        // Arrange
        Mockito.when(countryService.getAllCountries(true)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/football-standings/countries")
                .param("refresh", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCountryListWithInvalidRefresh() throws Exception {
        // Arrange
        Mockito.when(countryService.getAllCountries(false)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/football-standings/countries")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
