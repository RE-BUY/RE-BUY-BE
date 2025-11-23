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
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> {
                                log.error("Upstage API 에러 - Status: {}", clientResponse.statusCode());
                                return clientResponse.bodyToMono(String.class)
                                        .flatMap(body -> {
                                            log.error("Upstage API 에러 응답: {}", body);
                                            return Mono.error(new RuntimeException("Upstage API 호출 실패: " + body));
                                        });
                            })
                    .bodyToMono(UpstageResponse.class)
                    .timeout(Duration.ofMillis(timeout))
                    .doOnError(error -> log.error("Upstage API 호출 중 에러 발생", error))
                    .block();

            if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                String content = response.getChoices().get(0).getMessage().getContent();
                log.debug("Upstage API 응답: {}", content);
                return content;
            }

            log.warn("Upstage API 응답이 비어있습니다.");
            return generateFallbackResponse();

        } catch (Exception e) {
            log.error("Upstage API 호출 실패 - Message: {}, Type: {}", e.getMessage(), e.getClass().getSimpleName(), e);
            return generateFallbackResponse();
        }
    }

    private String generateFallbackResponse() {
        return "AI 서비스가 일시적으로 사용 불가능합니다. 환경 점수가 높은 상품들을 기본 추천으로 제공합니다.";
    }

    public String generateJsonCompletion(String systemPrompt, String userPrompt) {
        String enhancedSystemPrompt = systemPrompt +
                "\n\n반드시 유효한 JSON 형식으로만 응답하세요. 다른 텍스트는 포함하지 마세요.";
        return generateCompletion(enhancedSystemPrompt, userPrompt);
    }
}

