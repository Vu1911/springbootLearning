package com.example.springsocial.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;

@Getter
@Setter
public abstract class AbstractRequest {

    @NotNull
    private ZoneId zoneId;

    public void setZoneId (String zoneId){

        this.zoneId = ZoneId.of(zoneId);
    }
}
