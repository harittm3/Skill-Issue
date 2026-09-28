package com.skillissue.roastapp.provider;

import com.skillissue.roastapp.config.GeminiProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

@Component
public class GeminiProvider implements RoastProvider {

    private final GeminiProperties geminiProperties;
    private final RestClient restclient;

    public GeminiProvider(GeminiProperties geminiProperties, RestClient restclient) {
        this.geminiProperties = geminiProperties;
        this.restclient = restclient;
    }

    @Override
    public String generateRoast(String role, Map<String, Integer> ratings, int percentage) throws RoastProviderException {

        String prompt = "You have to generate a brutal roast based on the role " + role
                + " the user wants a job for. Their percentage chance of being hired is " + percentage
                + "%. Here are the subcategories they rated themselves on a scale of 1 to 10: " + ratings
                + ". Roast them hard and keep it between 80-100 words.";

        GeminiPart part = new GeminiPart(prompt);
        GeminiContent content = new GeminiContent("user", List.of(part));
        GeminiRequest request = new GeminiRequest(List.of(content));

        String url = geminiProperties.getUrl() + "/" + geminiProperties.getModel() + ":generateContent";

        try {
            GeminiResponse response = restclient.post()
                    .uri(url)
                    .header("x-goog-api-key", geminiProperties.getKey())
                    .body(request)
                    .retrieve()
                    .body(GeminiResponse.class);

            if (response == null
                    || response.candidates() == null
                    || response.candidates().isEmpty()
                    || response.candidates().get(0) == null
                    || response.candidates().get(0).content() == null
                    || response.candidates().get(0).content().parts() == null
                    || response.candidates().get(0).content().parts().isEmpty()
                    || response.candidates().get(0).content().parts().get(0) == null
                    || response.candidates().get(0).content().parts().get(0).text() == null
                    || response.candidates().get(0).content().parts().get(0).text().isBlank()) {
                throw new RoastProviderException("Gemini returned an empty or filtered response");
            }

            return response.candidates().get(0).content().parts().get(0).text();

        } catch (RestClientException e) {
            throw new RoastProviderException("Gemini API call failed", e);
        }
    }
}