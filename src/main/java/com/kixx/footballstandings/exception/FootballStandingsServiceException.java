package com.kixx.footballstandings.exception;

import lombok.Getter;

public class FootballStandingsServiceException extends RuntimeException {
    @Getter
    protected final String code;

    public FootballStandingsServiceException(String message, String code) {
        super(message);
        this.code = code;
    }
}
