package com.skillissue.roastapp.service;

import com.skillissue.roastapp.provider.GroqProvider;
import com.skillissue.roastapp.provider.RoastProviderException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RoastService {

    private final GroqProvider groqProvider;

    public RoastService(GroqProvider groqProvider) {
        this.groqProvider = groqProvider;
    }

    public String generateRoast(String role, Map<String, Integer> ratings, int percentage) {
        try {
            return groqProvider.generateRoast(role, ratings, percentage);
        } catch (RoastProviderException e) {
            throw new RuntimeException(e);
        }
    }
}
