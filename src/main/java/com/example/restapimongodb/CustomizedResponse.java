package com.example.restapimongodb;

import java.util.List;

public class CustomizedResponse<T> {
    //<T> means generic so use this object for all customized responses like Movie,TV,User

    private String message;
    private List<T> body;

    public CustomizedResponse() {
    }

    public CustomizedResponse(String message, List<T> body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }
}
