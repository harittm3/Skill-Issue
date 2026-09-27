package com.skillissue.roastapp.provider;

import java.util.List;

public record GeminiContent(String role, List<GeminiPart> parts) {
}
