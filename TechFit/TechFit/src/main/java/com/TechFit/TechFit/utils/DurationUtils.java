package com.TechFit.TechFit.utils;

import com.TechFit.TechFit.exeptions.Exceptions;

import java.time.Duration;

public class DurationUtils {
    public static Duration ParseMmSs(String value) {
        String[] split = value.split(":");
        if (split.length != 2) {
            throw new Exceptions.BadRequest("Use o formato Minutos:segundos");

        }
        else {
            if (value == null || value.isBlank()) {
                throw new Exceptions.BadRequest("tempo não pode ser vazio");
            }
        }
        return Duration.ofMinutes(Long.parseLong(split[0]))
                .plusSeconds(Long.parseLong(split[1]));

    }

    public static String formatMmSs(Duration duration) {
        long minutes = duration.toMinutes();
        long seconds = duration.minusMinutes(minutes).toSeconds();

        return String.format("%02d:%02d", minutes, seconds);

    }
}
