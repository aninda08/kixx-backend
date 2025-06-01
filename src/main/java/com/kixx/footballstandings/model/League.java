package com.kixx.footballstandings.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class League {
    private String country_id;
    private String country_name;
    private String country_logo;
    private String league_id;
    private String league_name;
    private String league_logo;
    private String league_season;
}
