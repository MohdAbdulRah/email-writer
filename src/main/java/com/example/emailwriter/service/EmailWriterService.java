package com.example.emailwriter.service;

import com.example.emailwriter.models.Requests.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmailWriterService {
    private final WebClient webClient;
    private final String apiKey;

    public EmailWriterService(WebClient.Builder webClientBuilder,
                              @Value("${gemini.api.key}") String apiKey,
                              @Value("${gemini.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
        this.apiKey = apiKey;
    }


    public String generate(EmailRequest request){
        //build Prompt
        if(request.getEmailContent().trim().equals("")){
            throw new RuntimeException("Please Give some email Content");
        }
        String prompt = buildPrompt(request);

    }

    private String buildPrompt(EmailRequest request) {

        return """
            You are a professional email writing assistant.

            Your task is to write an appropriate reply to the email provided below.

            Email Content:
            %s

            Desired Tone:
            %s

            Instructions:
            1. Carefully understand the original email before generating a reply.
            2. Write a relevant and meaningful response that directly addresses the sender's message.
            3. Maintain the specified tone throughout the reply.
            4. Keep the response professional, clear, natural, and grammatically correct.
            5. Ensure the reply sounds human-written rather than AI-generated.
            6. Maintain appropriate email etiquette.
            7. Do not introduce assumptions or fabricate information that is not available in the original email.
            8. Keep the response concise while including all necessary information.
            9. Do not repeat the original email unnecessarily.
            10. Return only the email reply. Do not include explanations, notes, or introductory statements.

            Generate the email reply now.
            """.formatted(
                request.getEmailContent(),
                request.getTone()
        );
    }
}
