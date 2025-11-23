package com.rebuy.global.exception.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RE:BUY API Documentation")
                        .version("1.0.0")
                        .description("""
                                ## RE:BUY Backend API
                                
                                친환경 제품 구매로 적립한 크레딧을 지역 환경 활동 참여에 사용하고,
                                활동 보상으로 다시 제품을 할인 구매하는 선순환 녹색 소비 플랫폼
                                
                                ### 주요 기능
                                - 🔐 사용자 인증 및 ��원가입
                                - 🛍️ 친환경 상품 관리 (29개)
                                - 🛒 장바구니 및 주문
                                - 💰 크레딧 시스템
                                - 🌱 환경 활동 관리
                                - 🤖 AI 기반 상품 추천 (Upstage Solar-Pro2)
                                - 📊 월별 녹색 소비 리포트
                                
                                ### 인증 방법
                                1. `/api/v1/auth/login`으로 로그인
                                2. 응답에서 받은 `accessToken` 복사
                                3. 우측 상단 **Authorize** 버튼 클릭
                                4. Value에 `Bearer {token}` 입력
                                5. Authorize 클릭
                                
                                ### 테스트 계정
                                - **일반 사용자**: admin2 / Admin123!
                                - **관리자**: admin / admin1234
                                """)
                        .contact(new Contact()
                                .name("RE:BUY Team")
                                .email("rebuy-team@example.com")
                                .url("https://github.com/RE-BUY/RE-BUY-BE"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("로컬 개발 서버 (본인만)"),
                        new Server()
                                .url("http://192.168.45.170:" + serverPort)
                                .description("팀 서버 (같은 Wi-Fi 네트워크)"),
                        new Server()
                                .url("http://your-ngrok-url.ngrok.io")
                                .description("외부 접속용 (ngrok - 다른 네트워크)")
                ))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT 토큰을 입력하세요. 'Bearer ' 접두사는 자동으로 추가됩니다.")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
