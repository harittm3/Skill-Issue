package com.skillissue.roastapp.service;

import com.skillissue.roastapp.provider.CannedProvider;
import com.skillissue.roastapp.provider.GeminiProvider;
import com.skillissue.roastapp.provider.GroqProvider;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class RoastService {

    private final GroqProvider groqProvider;
    private final GeminiProvider geminiProvider;
    private final CannedProvider cannedProvider;
    private static final Logger log = LoggerFactory.getLogger(RoastService.class);

    public RoastService(GroqProvider groqProvider, GeminiProvider geminiProvider, CannedProvider cannedProvider) {
        this.groqProvider = groqProvider;
        this.geminiProvider = geminiProvider;
        this.cannedProvider = cannedProvider;
    }

    public String generateRoast(String role, Map<String, Integer> ratings, int percentage) {
        try {
            String roast = groqProvider.generateRoast(role, ratings, percentage);
            log.info("Roast generated successfully via Groq for role: {}", role);
            return roast;
        } catch (Exception e) {
            log.warn("Groq failed for role: {}, falling back to Gemini. Reason: {}", role, e.getMessage());
            try {
                String roast = geminiProvider.generateRoast(role, ratings, percentage);
                log.info("Roast generated successfully via Gemini for role: {}", role);
                return roast;
            } catch (Exception e1) {
                log.warn("Gemini also failed for role: {}, falling back to canned roast. Reason: {}", role, e1.getMessage());
                try {
                    return cannedProvider.generateRoast(role, ratings, percentage);
                } catch (Exception e2) {
                    log.error("CannedProvider also failed for role: {}. This should never happen.", role, e2);
                    return "Your skills are so bad even our roast generator gave up. Skill issue detected.";
                }
            }
        }
    }
}
