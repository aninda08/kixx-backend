package com.kixx.footballstandings.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kixx.footballstandings.model.League;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeagueResponse {
    private List<League> leagues;
    private long timeTaken;
}
