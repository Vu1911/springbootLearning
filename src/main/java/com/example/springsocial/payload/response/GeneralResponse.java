package com.example.springsocial.payload.response;

import com.example.springsocial.util.TimeRelatedUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GeneralResponse {
    private int code;
    private String message;
    private String type;
    private Object details;
    private ZonedDateTime timestamp;

    public GeneralResponse(){
        this.setTimestamp();
    }

    public void setTimestamp(){
        this.timestamp = TimeRelatedUtil.utcNow();
    }
}
