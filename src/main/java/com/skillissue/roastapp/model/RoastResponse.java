package com.skillissue.roastapp.model;

import java.util.Map;

public record RoastResponse(
        int percentage,
        String roast,
        String role,
        Map<String, Integer> breakdown
) {
    public RoastResponse(int percentage, String roast) {
        this(percentage, roast, null, null);
    }
}
