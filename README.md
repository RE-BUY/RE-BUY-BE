# RE:BUY Backend 🌱

> 친환경 제품 구매로 적립한 크레딧을 지역 환경 활동 참여에 사용하고, 활동 보상으로 다시 제품을 할인 구매하는 **선순환 녹색 소비 플랫폼**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📋 목차

- [기술 스택](#-기술-스택)
- [주요 기능](#-주요-기능)
- [프로젝트 구조](#-프로젝트-구조)
- [시작하기](#-시작하기)
- [API 문서](#-api-문서)
- [환경 설정](#-환경-설정)
- [팀원](#-팀원)

---

## 🛠 기술 스택

### Backend
- **Framework:** Spring Boot 3.2.1
- **Language:** Java 17
- **Build Tool:** Gradle 8.14
- **ORM:** Spring Data JPA (Hibernate)

### Database & Cache
- **Database:** PostgreSQL 15
- **Cache:** Redis 7.x

### Security & Authentication
- **Spring Security** 6.x
- **JWT** (JSON Web Token)
- 비밀번호 암호화 (BCrypt)

### AI & External API
- **Upstage Solar-Pro2** - AI 상품 추천 및 리포트 생성
- **WebFlux** - 비동기 API 통신

### Documentation & Testing
- **Swagger/OpenAPI** 3.0
- **JUnit 5** & **Mockito**

### Deployment
- **Docker** & **Docker Compose**
- **GitHub Actions** (CI/CD)

---

## ✨ 주요 기능

### 1. 사용자 인증 및 관리
- ✅ 회원가입 및 이메일 인증
- ✅ JWT 기반 로그인/로그아웃
- ✅ 비밀번호 찾기 및 재설정
- ✅ 사용자 프로필 관리

### 2. 친환경 상품 관리
- ✅ 상품 카탈로그 (29개 실제 친환경 제품)
- ✅ 5개 카테고리: 식품, 음료, 패션, 문구류, 생활용품
- ✅ 환경 점수 시스템 (0~100점)
- ✅ 상품별 환경 영향 데이터 (CO2, 물, 석유, 플라스틱 절감량)
- ✅ 검색 및 필터링

### 3. 주문 및 결제
- ✅ 장바구니 기능
- ✅ 주문 생성 및 관리
- ✅ 주문 내역 조회
- ✅ 주문 상태 추적

### 4. 크레딧 시스템
- ✅ 상품 구매 시 크레딧 적립
- ✅ 크레딧 사용 및 잔액 조회
- ✅ 크레딧 거래 내역 확인

### 5. 환경 활동 관리
- ✅ 지역 환경 활동 목록 조회
- ✅ 활동 참여 신청
- ✅ 활동 인증샷 제출
- ✅ 관리자 승인 및 보상 지급
- ✅ 참여 이력 조회

### 6. AI 추천 시스템 🤖 (Upstage Solar-Pro2)
- ✅ 사용자 구매 패턴 분석
- ✅ AI 기반 맞춤형 상품 추천
- ✅ 월별 녹색 소비 리포트 자동 생성
- ✅ 환경 기여도 분석 및 인사이트 제공
- ✅ 사용자 순위 및 달성 과제

### 7. 대시보드 및 통계
- ✅ 마이페이지 (구매 내역, 크레딧, 환경 점수)
- ✅ 월별 환경 기여 통계
- ✅ 친환경 소비 트렌드 시각화

### 8. 관리자 기능
- ✅ 상품 관리 (등록, 수정, 삭제)
- ✅ 주문 관리
- ✅ 활동 참여 승인/거부
- ✅ 사용자 관리

---

## 📁 프로젝트 구조

```
src/main/java/com/rebuy/
├── controller/              # REST API 컨트롤러
│   ├── ActivityController          # 환경 활동 관리
│   ├── AIRecommendationController  # AI 추천 시스템
│   ├── AuthController              # 인증/회원가입
│   ├── CartController              # 장바구니
│   ├── CheckoutController          # 주문/결제
│   ├── CreditController            # 크레딧 관리
│   ├── DashboardController         # 대시보드
│   ├── MyPageController            # 마이페이지
│   ├── OrderController             # 주문 관리
│   ├── ProductController           # 상품 관리
│   ├── UserController              # 사용자 관리
│   └── dto/                        # 데이터 전송 객체
│       ├── ai/                     # AI 관련 DTO
│       ├── auth/                   # 인증 관련 DTO
│       ├── cart/                   # 장바구니 DTO
│       ├── mypage/                 # 마이페이지 DTO
│       ├── order/                  # 주문 DTO
│       ├── product/                # 상품 DTO
│       └── user/                   # 사용자 DTO
│
├── service/                 # 비즈니스 로직
│   ├── ActivityService             # 환경 활동 서비스
│   ├── AIRecommendationService     # AI 추천 서비스
│   ├── AuthExtensionService        # 인증 확장 서비스
│   ├── CartService                 # 장바구니 서비스
│   ├── CheckoutService             # 결제 서비스
│   ├── CreditService               # 크레딧 서비스
│   ├── OrderService                # 주문 서비스
│   ├── ProductService              # 상품 서비스
│   ├── UpstageAIService            # Upstage API 연동
│   └── UserService                 # 사용자 서비스
│
├── repository/              # 데이터 액세스 레이어
│   ├── CartItemRepository
│   ├── CreditTransactionRepository
│   ├── EmailVerificationTokenRepository
│   ├── EnvironmentalActivityRepository
│   ├── EnvironmentalImpactRepository
│   ├── OrderItemRepository
│   ├── OrderRepository
│   ├── ParticipationRepository
│   ├── PasswordResetTokenRepository
│   ├── ProductRepository
│   └── UserRepository
│
├── entity/                  # JPA 엔티티
│   ├── CartItem                    # 장바구니 아이템
│   ├── CreditTransaction           # 크레딧 거래
│   ├── EmailVerificationToken      # 이메일 인증 토큰
│   ├── EnvironmentalActivity       # 환경 활동
│   ├── EnvironmentalImpact         # 환경 영향
│   ├── Order                       # 주문
│   ├── OrderItem                   # 주문 상품
│   ├── Participation               # 활동 참여
│   ├── PasswordResetToken          # 비밀번호 재설정 토큰
│   ├── Product                     # 상품
│   ├── User                        # 사용자
│   └── enums/                      # Enum 클래스
│       ├── OrderStatus             # 주문 상태
│       ├── ParticipationStatus     # 참여 상태
│       ├── ProductCategory         # 상품 카테고리
│       └── Role                    # 사용자 역할
│
├── global/                  # 공통 설정 및 유틸리티
│   ├── base/                       # 기본 엔티티
│   │   └── BaseTimeEntity          # 생성/수정 시간 추적
│   ├── config/                     # 설정 클래스
│   │   ├── SecurityConfig          # Spring Security 설정
│   │   ├── SwaggerConfig           # Swagger 설정
│   │   └── UpstageConfig           # Upstage API 설정
│   ├── exception/                  # 예외 처리
│   │   ├── GlobalExceptionHandler  # 전역 예외 핸들러
│   │   └── ResourceNotFoundException
│   ├── security/                   # 보안 관련
│   │   ├── JwtAuthenticationFilter # JWT 인증 필터
│   │   ├── JwtTokenProvider        # JWT 토큰 생성/검증
│   │   └── CustomUserDetailsService
│   └── util/                       # 유틸리티
│       └── EcoScoreCalculator      # 환경 점수 계산
│
└── ReBuyApplication.java    # 애플리케이션 진입점

src/main/resources/
├── application.yml          # 기본 설정
├── application-dev.yml      # 개발 환경 설정
├── application-prod.yml     # 운영 환경 설정
└── application-local.yml    # 로컬 설정 (Git 제외)
```

---

## 🚀 시작하기

### 사전 요구사항

- **Java 17** 이상
- **Docker** & **Docker Compose**
- **PostgreSQL** 15 (Docker로 실행 가능)
- **Upstage API Key** (AI 기능 사용 시)

### 1. 프로젝트 클론

```bash
git clone https://github.com/RE-BUY/RE-BUY-BE.git
cd ReBuy
```

### 2. Docker로 PostgreSQL 실행

#### 방법 1: Docker Compose (추천)
```bash
docker-compose up -d
```

#### 방법 2: Docker 직접 실행
```bash
docker run --name rebuy-postgres \
  -e POSTGRES_DB=rebuy \
  -e POSTGRES_USER=rebuy_user \
  -e POSTGRES_PASSWORD=rebuy_password \
  -p 5432:5432 \
  -d postgres:15
```

### 3. Upstage API 키 설정 (AI 기능 사용 시)

#### 방법 1: application-local.yml 생성 (추천)
```bash
# 템플릿 복사
cp src/main/resources/application-local.yml.example \
   src/main/resources/application-local.yml

# 편집기로 열어서 실제 API 키 입력
vi src/main/resources/application-local.yml
```

```yaml
# application-local.yml
upstage:
  api:
    key: your-actual-upstage-api-key
```

⚠️ **주의**: `application-local.yml`은 `.gitignore`에 포함되어 Git에 커밋되지 않습니다.

#### 방법 2: 환경 변수 사용
```bash
export UPSTAGE_API_KEY="your-actual-upstage-api-key"
```

### 4. 애플리케이션 실행

```bash
# Gradle로 실행
./gradlew bootRun

# 또는 빌드 후 실행
./gradlew build
java -jar build/libs/ReBuy-1.0.0.jar
```

### 5. 접속 확인

- **서버**: http://localhost:8080
- **API 문서 (Swagger)**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health

---

## 📚 API 문서

### Swagger UI
http://localhost:8080/swagger-ui.html

### 주요 API 엔드포인트

#### 인증 (Authentication)
```
POST   /api/v1/auth/register          # 회원가입
POST   /api/v1/auth/login             # 로그인
POST   /api/v1/auth/logout            # 로그아웃
GET    /api/v1/auth/verify-email      # 이메일 인증
POST   /api/v1/auth/password-reset    # 비밀번호 재설정
```

#### 상품 (Products)
```
GET    /api/v1/products               # 상품 목록 조회
GET    /api/v1/products/{id}          # 상품 상세 조회
GET    /api/v1/products/category/{category}  # 카테고리별 조회
GET    /api/v1/products/search        # 상품 검색
POST   /api/v1/admin/products         # 상품 등록 (관리자)
PUT    /api/v1/admin/products/{id}    # 상품 수정 (관리자)
DELETE /api/v1/admin/products/{id}    # 상품 삭제 (관리자)
```

#### 주문 (Orders)
```
POST   /api/v1/orders                 # 주문 생성
GET    /api/v1/orders                 # 주문 목록 조회
GET    /api/v1/orders/{id}            # 주문 상세 조회
PUT    /api/v1/orders/{id}/cancel     # 주문 취소
```

#### 장바구니 (Cart)
```
GET    /api/v1/cart                   # 장바구니 조회
POST   /api/v1/cart/items             # 장바구니 추가
PUT    /api/v1/cart/items/{id}        # 수량 변경
DELETE /api/v1/cart/items/{id}        # 상품 제거
DELETE /api/v1/cart                   # 장바구니 비우기
```

#### 환경 활동 (Activities)
```
GET    /api/v1/activities             # 활동 목록 조회
GET    /api/v1/activities/{id}        # 활동 상세 조회
POST   /api/v1/activities/{id}/apply  # 활동 신청
POST   /api/v1/activities/participations/{id}/verify  # 인증샷 제출
POST   /api/v1/activities/admin/participations/{id}/confirm  # 승인 (관리자)
GET    /api/v1/activities/my-participations  # 내 참여 이력
```

#### AI 추천 시스템 (AI Recommendations)
```
GET    /api/v1/ai/recommendations     # AI 상품 추천
GET    /api/v1/ai/report/current      # 이번 달 리포트
GET    /api/v1/ai/report/monthly      # 특정 월 리포트
```

#### 크레딧 (Credits)
```
GET    /api/v1/credits/balance        # 크레딧 잔액 조회
GET    /api/v1/credits/transactions   # 거래 내역 조회
```

#### 마이페이지 (My Page)
```
GET    /api/v1/mypage/profile         # 프로필 조회
GET    /api/v1/mypage/orders          # 주문 내역
GET    /api/v1/mypage/eco-stats       # 환경 기여 통계
```

---

## 🧪 API 사용 예시

### 1. 회원가입 및 로그인

```bash
# 회원가입
curl -X POST 'http://localhost:8080/api/v1/auth/register' \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "eco_user",
    "password": "EcoUser123!",
    "email": "user@example.com",
    "phone": "010-1234-5678"
  }'

# 로그인
TOKEN=$(curl -s -X POST 'http://localhost:8080/api/v1/auth/login' \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "eco_user",
    "password": "EcoUser123!"
  }' | jq -r '.accessToken')

echo "토큰: $TOKEN"
```

### 2. 상품 조회

```bash
# 전체 상품 목록
curl -X GET 'http://localhost:8080/api/v1/products' | jq

# 카테고리별 조회
curl -X GET 'http://localhost:8080/api/v1/products/category/FOOD' | jq

# 상품 검색
curl -X GET 'http://localhost:8080/api/v1/products/search?keyword=대나무' | jq
```

### 3. 장바구니 및 주문

```bash
# 장바구니에 추가
curl -X POST 'http://localhost:8080/api/v1/cart/items' \
  -H "Authorization: Bearer $TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
    "productId": 11,
    "quantity": 2
  }'

# 주문 생성
curl -X POST 'http://localhost:8080/api/v1/orders' \
  -H "Authorization: Bearer $TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
    "items": [
      {"productId": 11, "quantity": 2}
    ],
    "shippingAddress": "서울시 강남구 테헤란로 123",
    "useCredit": 1000
  }'
```

### 4. 환경 활동 참여

```bash
# 활동 목록 조회
curl -X GET 'http://localhost:8080/api/v1/activities' | jq

# 활동 신청
curl -X POST 'http://localhost:8080/api/v1/activities/2/apply' \
  -H "Authorization: Bearer $TOKEN"

# 인증샷 제출
curl -X POST 'http://localhost:8080/api/v1/activities/participations/1/verify' \
  -H "Authorization: Bearer $TOKEN" \
  -H 'Content-Type: application/json' \
  -d '{
    "proofImageUrl": "https://example.com/proof.jpg"
  }'
```

### 5. AI 추천 시스템

```bash
# AI 상품 추천 (맞춤형)
curl -X GET 'http://localhost:8080/api/v1/ai/recommendations?limit=5' \
  -H "Authorization: Bearer $TOKEN" | jq

# 월별 녹색 소비 리포트
curl -X GET 'http://localhost:8080/api/v1/ai/report/current' \
  -H "Authorization: Bearer $TOKEN" | jq

# 특정 월 리포트
curl -X GET 'http://localhost:8080/api/v1/ai/report/monthly?year=2025&month=11' \
  -H "Authorization: Bearer $TOKEN" | jq
```

---

## ⚙️ 환경 설정

### application.yml 구조

```yaml
# 기본 설정
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/rebuy
    username: rebuy_user
    password: rebuy_password
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

# JWT 설정
jwt:
  secret: ${JWT_SECRET}
  expiration: 7200000  # 2시간

# Upstage AI 설정
upstage:
  api:
    url: https://api.upstage.ai/v1/solar
    key: ${UPSTAGE_API_KEY}
    model: solar-pro-2
    timeout: 30000
```

### Docker 명령어

```bash
# PostgreSQL 시작
docker-compose up -d

# PostgreSQL 중지
docker-compose down

# PostgreSQL 중지 + 데이터 삭제
docker-compose down -v

# 로그 확인
docker-compose logs -f postgres

# PostgreSQL 접속
docker exec -it rebuy-postgres psql -U rebuy_user -d rebuy

# 컨테이너 상태 확인
docker-compose ps
```

### 데이터베이스 초기화

```bash
# 초기 데이터 로드
psql -h localhost -U rebuy_user -d rebuy < db_init.sql

# 또는 Docker Compose가 자동으로 로드 (docker-compose.yml 설정됨)
```

---

## 🗃️ 데이터베이스 스키마

### 주요 테이블

- **users** - 사용자 정보 (6명)
- **products** - 상품 정보 (29개)
- **orders** - 주문 정보
- **order_items** - 주문 상품
- **cart_items** - 장바구니
- **environmental_activities** - 환경 활동 (3개)
- **participations** - 활동 참여
- **credit_transactions** - 크레딧 거래 내역
- **environmental_impacts** - 환경 영향 데이터

### 상품 카테고리

- **FOOD** (식품) - 7개
- **BEVERAGE** (음료) - 1개
- **FASHION** (패션) - 6개
- **STATIONERY** (문구류) - 7개
- **HOUSEHOLD** (생활용품) - 8개

---

## 🔐 보안

### API 키 관리

⚠️ **중요**: API 키는 절대 Git에 커밋하지 마세요!

#### 안전한 방법
1. `application-local.yml` 사용 (Git 제외됨)
2. 환경 변수 사용
3. CI/CD 환경에서는 Secret 사용

#### 금지 사항
- ❌ `application.yml`에 실제 키 입력
- ❌ 코드에 하드코딩
- ❌ 공개 저장소에 커밋

자세한 내용은 `API_KEY_보안_가이드.txt` 참고

---

## 🧪 테스트

```bash
# 전체 테스트 실행
./gradlew test

# 특정 테스트 실행
./gradlew test --tests com.rebuy.service.ProductServiceTest

# 테스트 커버리지 확인
./gradlew jacocoTestReport
```

---

## 📦 빌드 및 배포

### 빌드

```bash
# 빌드 (테스트 포함)
./gradlew build

# 빌드 (테스트 제외)
./gradlew build -x test

# 클린 빌드
./gradlew clean build
```

### JAR 실행

```bash
java -jar build/libs/ReBuy-1.0.0.jar
```

### Docker 이미지 빌드 (향후 추가 예정)

```bash
docker build -t rebuy-backend:latest .
docker run -p 8080:8080 rebuy-backend:latest
```

---

## 👥 팀원

| 이름  | 역할 | 담당 기능                                 |
|-----|------|---------------------------------------|
| 이정민 | Backend Developer | 인증, 상품, 주문, 결제, AI 추천 시스템, Upstage 연동 |
| 김서준 | Backend Developer | 사용자 관리 , 환경 활동, 크레딧 시스템                             |

---

<div align="center">
[⬆ 맨 위로 돌아가기](#rebuy-backend-)

</div>

