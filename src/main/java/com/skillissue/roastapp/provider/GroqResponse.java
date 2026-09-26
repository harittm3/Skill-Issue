package com.skillissue.roastapp.provider;

import java.util.List;

public record GroqResponse(List<Choice> choices) {
    public record Choice(GroqMessage message) {
    }
}
