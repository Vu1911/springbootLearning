package com.example.springsocial.payload.response;

import com.example.springsocial.util.TimeRelatedUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
public abstract class AbstractTimeResponse {
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime deletedAt;

    public void setCreatedAt(Timestamp createdAt, ZoneId zoneId) {
        this.createdAt = TimeRelatedUtil.sqlTimestampToZonedDateTime(createdAt, zoneId);
    }

    public void setUpdatedAt(Timestamp updatedAt, ZoneId zoneId) {
        this.updatedAt = TimeRelatedUtil.sqlTimestampToZonedDateTime(updatedAt, zoneId);
    }

    public void setDeletedAt(Timestamp deletedAt, ZoneId zoneId) {
        this.deletedAt = TimeRelatedUtil.sqlTimestampToZonedDateTime(deletedAt, zoneId);;
    }
}
