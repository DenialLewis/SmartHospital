package com.example.smart_hospital.service;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AIChatService {

    private static final Logger logger = LoggerFactory.getLogger(AIChatService.class);
    
    @Value("${openai.api.key}")
    private String openAiApiKey;

    private static final String API_URL = "https://api.openai.com/v1/completions";

    public String getAIResponse(String userMessage) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String prompt = "The patient says: " + userMessage + ". Provide a medical response.";

        RequestBody body = RequestBody.create(
            "{\"model\": \"text-davinci-003\", \"prompt\": \"" + prompt + "\", \"max_tokens\": 100}",
            MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
            .url(API_URL)
            .header("Authorization", "Bearer " + openAiApiKey)
            .post(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.error("API call failed with response code: {}", response.code());
                throw new IOException("Unexpected code " + response);
            }

            // Parse the JSON response to get the AI response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body().string());

            // Extract the first choice text
            String aiResponse = jsonNode.get("choices").get(0).get("text").asText().trim();
            logger.info("AI Response: {}", aiResponse); // Log the AI response
            return aiResponse;
        } catch (IOException e) {
            logger.error("Error occurred while calling OpenAI API: {}", e.getMessage());
            throw e; // Re-throw the exception after logging
        }
    }
}
