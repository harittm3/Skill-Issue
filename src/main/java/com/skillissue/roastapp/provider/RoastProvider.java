package com.skillissue.roastapp.provider;

import java.util.Map;

public interface RoastProvider {

    String generateRoast(String role, Map<String, Integer> ratings, int percentage) throws RoastProviderException;
}
