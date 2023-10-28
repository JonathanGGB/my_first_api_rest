package com.apiRest.myFirstApi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude
@Data
public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(String error, String message){
        this.error = error;
        this.message = message;
    }
}
