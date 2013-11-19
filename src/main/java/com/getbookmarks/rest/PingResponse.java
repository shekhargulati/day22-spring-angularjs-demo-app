package com.getbookmarks.rest;

public class PingResponse {

    private String message;

    public PingResponse(String message) {
        this.message = message; 
    }

    public PingResponse() {
    }
    
    public String getMessage() {
        return message;
    }
    
}
