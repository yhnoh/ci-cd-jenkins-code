package com.example.cicdjenkinscode.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HelloDTO {

    private String helloValue;


    public HelloDTO(String value) {
        if("start".equalsIgnoreCase(value)){
            this.helloValue = "hello start";
        }else if("last".equalsIgnoreCase(value)){
            this.helloValue = "hello last";
        }else{
            this.helloValue = value;
        }
    }
}
