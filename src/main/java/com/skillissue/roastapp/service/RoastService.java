package com.skillissue.roastapp.service;

import com.skillissue.roastapp.provider.CannedProvider;
import com.skillissue.roastapp.provider.GeminiProvider;
import com.skillissue.roastapp.provider.GroqProvider;
import com.skillissue.roastapp.provider.RoastProviderException;
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
            return groqProvider.generateRoast(role, ratings, percentage);
        } catch (RoastProviderException e) {
            try{
                return geminiProvider.generateRoast(role, ratings, percentage);
            } catch (RoastProviderException e1){
                log.error("Gemini API failed, falling back to canned", e1);
                return cannedProvider.generateRoast(role, ratings, percentage);
            }
        }
    }
}
