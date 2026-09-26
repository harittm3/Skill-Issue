package com.skillissue.roastapp.provider;

import java.util.List;

public record GroqRequest(String model, List<GroqMessage> messages) {
}
