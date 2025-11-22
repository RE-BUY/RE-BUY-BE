package com.rebuy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.controller.dto.ai.UpstageRequest;
import com.rebuy.controller.dto.ai.UpstageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpstageAIService {

    private final WebClient upstageWebClient;
    private final ObjectMapper objectMapper;

    @Value("${upstage.api.model}")
    private String model;

    @Value("${upstage.api.timeout}")
    private int timeout;

    public String generateCompletion(String systemPrompt, String userPrompt) {
        try {
            UpstageRequest request = UpstageRequest.builder()
                    .model(model)
                    .messages(List.of(
                            UpstageRequest.Message.builder()
                                    .role("system")
                                    .content(systemPrompt)
                                    .build(),
                            UpstageRequest.Message.builder()
                                    .role("user")
                                    .content(userPrompt)
                                    .build()
                    ))
                    .temperature(0.7)
                    .maxTokens(2000)
                    .build();

            log.debug("Upstage API 요청: {}", objectMapper.writeValueAsString(request));

            UpstageResponse response = upstageWebClient.post()
                    .uri("/chat/completions")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(UpstageResponse.class)
                    .timeout(Duration.ofMillis(timeout))
                    .block();

            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                String content = response.getChoices().get(0).getMessage().getContent();
                log.debug("Upstage API 응답: {}", content);
                return content;
            }

            log.warn("Upstage API 응답이 비어있습니다.");
            return "AI 응답을 생성할 수 없습니다.";

        } catch (Exception e) {
            log.error("Upstage API 호출 실패", e);
            return "AI 서비스 일시적 오류. 기본 추천을 제공합니다.";
        }
    }

    public String generateJsonCompletion(String systemPrompt, String userPrompt) {
        String enhancedSystemPrompt = systemPrompt +
                "\n\n반드시 유효한 JSON 형식으로만 응답하세요. 다른 텍스트는 포함하지 마세요.";
        return generateCompletion(enhancedSystemPrompt, userPrompt);
    }
}

