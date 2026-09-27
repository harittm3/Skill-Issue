package com.skillissue.roastapp.provider;

import com.skillissue.roastapp.config.GroqProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Map;

@Component
public class GroqProvider implements RoastProvider {

    private final GroqProperties groqProperties;
    private final RestClient restclient;

    public GroqProvider(GroqProperties groqProperties, RestClient restclient) {
        this.groqProperties = groqProperties;
        this.restclient = restclient;
    }

    @Override
    public String generateRoast(String role, Map<String, Integer> ratings, int percentage) throws RoastProviderException {
        String prompt = "You have to generate a witty roast based on the role " + role
                + " the user wants a job for. Their percentage chance of being hired is " + percentage
                + "%. Here are the subcategories they rated themselves on a scale of 1 to 10: " + ratings
                + ". Roast them and keep it between 100-150 words.";

        GroqMessage message = new GroqMessage("user", prompt);

        GroqRequest request = new GroqRequest(groqProperties.getModel(), List.of(message));

        try {
            GroqResponse response = restclient.post()
                    .uri(groqProperties.getUrl())
                    .header("Authorization", "Bearer " + groqProperties.getKey())
                    .body(request)
                    .retrieve()
                    .body(GroqResponse.class);

            return response.choices().get(0).message().content();

        } catch (RestClientException e) {
            throw new RoastProviderException("Groq API call failed", e);
        }
    }
}
