package com.skillissue.roastapp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Map;

public record RoastRequest(
        @NotBlank String role,
        @NotEmpty Map<String, @Min(1) @Max(10) Integer> ratings
) {}
