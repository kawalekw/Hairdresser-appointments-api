package com.javaretards.hairdresserapponintments.Entity;

import lombok.Getter;

@Getter
public class StringResponse {
    private String response;

    public StringResponse(String str){
        this.response=str;
    }
}
