package com.javaretards.hairdresserapponintments.Entity;

import lombok.Getter;

@Getter
public class StringResponse {
    private String message;

    public StringResponse(String str){
        this.message=str;
    }
}
