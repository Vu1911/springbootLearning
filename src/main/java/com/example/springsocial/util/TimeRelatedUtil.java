package com.example.springsocial.util;

import com.example.springsocial.exception.BadRequestException;

import java.sql.Timestamp;
import java.time.*;

public class TimeRelatedUtil {

    public static ZonedDateTime utcNow() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }

    public static ZonedDateTime nowWithZone (ZoneId zoneId) {
        if (zoneId == null)
            throw new BadRequestException("[Time zone] the zone id is wrong or not declared");
        return ZonedDateTime.now(zoneId);
    }

    public static Timestamp zonedDateTimeToSQLTimestamp (ZonedDateTime zonedDateTime) {
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return Timestamp.valueOf(localDateTime);
    }

    public static ZonedDateTime sqlTimestampToZonedDateTime (Timestamp timestamp, ZoneId zoneId) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return ZonedDateTime.of(localDateTime, zoneId);
    }

    public static Timestamp getCurrentUtcTimestamp(){
        return zonedDateTimeToSQLTimestamp(utcNow());
    }

}

