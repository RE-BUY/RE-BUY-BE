# RE:BUY 🌱

> 친환경 제품 구매로 적립한 크레딧을 지역 환경 활동 참여에 사용하고, 활동 보상으로 다시 제품을 할인 구매하는 **선순환 녹색 소비 플랫폼**

[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-FF7800?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.java.net/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![React](https://img.shields.io/badge/React-087EA4?style=for-the-badge&logo=react&logoColor=white)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![TailwindCSS](https://img.shields.io/badge/TailwindCSS-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)](https://tailwindcss.com/)
[![Vite](https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white)](https://vitejs.dev/)
[![Axios](https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white)](https://axios-http.com/)

---

## 📋 목차

- [기술 스택](#-기술-스택)
- [주요 기능](#-주요-기능)
- [프로젝트 구조](#-프로젝트-구조)
- [시작하기](#-시작하기)
- [API 문서](#-api-연동-및-문서)
- [환경 설정](#-환경-설정)
- [팀원](#-팀원)

---

## 🛠 기술 스택

### 🎨 Frontend
- **Framework:** React + TypeScript  
- **Routing:** React Router 6  
- **CSS:** Tailwind CSS  
- **HTTP Client:** Axios  
- **Build Tool:** Vite 4.x
- **State Management:** Context API
- **Icons:** React Icons


### ⚙️ Backend
- **Framework:** Spring Boot 3.2.1  
- **Language:** Java 17  
- **Build Tool:** Gradle 8.14  
- **ORM:** Spring Data JPA (Hibernate)  

### 🗄️ Database & Cache
- **Database:** PostgreSQL 15  
- **Cache:** Redis 7.x  

### 🔐 Security & Authentication
- Spring Security 6.x  
- JWT (JSON Web Token)  
- Password Encryption: BCrypt  

### 🤖 AI & External API
- Upstage Solar-Pro2 – AI 상품 추천 및 리포트 생성  
- WebFlux – 비동기 API 처리  

### 🧪 Documentation & Testing
- Swagger / OpenAPI 3.0  
- JUnit 5 & Mockito  

### 🚀 Deployment & DevOps
- Docker & Docker Compose  
- GitHub Actions (CI/CD)

---

## ✨ 주요 기능

### 1. 사용자 인증 및 계정 관리
- ✅ 회원가입 및 이메일 인증
- ✅ JWT 기반 로그인 / 로그아웃
- ✅ 로그인 후 자동 이동(Home)
- ✅ 사용자 프로필 조회 및 수정
- ✅ UI 상에서 로그인 상태 및 정보 표시

### 2. 친환경 상품 관리
- ✅ 총 29개 실제 친환경 제품 데이터 제공
- ✅ 식품 / 음료 / 패션 / 문구류 / 생활용품 5개 카테고리
- ✅ 상품별 환경 점수 (0~100점)
- ✅ 환경 절약량 데이터 제공  (물, 나무 절감)
- ✅ 상품 목록, 상세 페이지 제공
- ✅ 검색, 정렬, 카테고리 필터링
- ✅ 카드형 UI 및 환경 정보 시각화

### 3. 주문 및 결제
- ✅ 장바구니 담기, 수량 변경, 삭제
- ✅ 주문 생성 및 주문 상태 관리
- ✅ 결제 시 총 금액 / 배송비 적용
- ✅ 주문 내역 조회 및 상태 추적

### 4. 활동량 및 포인트 시스템
- ✅ 상품 구매 시 포인트 자동 적립
- ✅ 활동량 순위 확인 가능
- ✅ 포인트 사용 및 조회
- ✅ 마이페이지에서 시각화된 UI로 표시

### 5. 지역 환경 활동 참여
- ✅ 환경 활동(예: 플로깅) 목록 조회
- ✅ 참여 신청 기능
- ✅ 인증샷 업로드 기능
- ✅ 관리자 승인 시 크레딧 또는 보상 지급
- ✅ 참여 내역 마이페이지에서 열람

### 6. AI 추천 시스템 (Upstage Solar-Pro2)
- ✅ 사용자 구매 데이터를 분석해 맞춤형 상품 추천
- ✅ WebFlux 기반 비동기 AI 통신
- ✅ 월별 녹색 소비 리포트 자동 생성
- ✅ 사용자 환경 기여 분석 (절약량, 성과 지표)
- ✅ 사용자 랭킹 및 도전 과제 제공
- ✅ 프론트에서 차트 및 그래프 시각화

### 7. 대시보드 & 통계 시각화
- ✅ 마이페이지에서 사용자 데이터 시각화
- ✅ 구매 이력
- ✅ 적립 포인트 / 크레딧
- ✅ 월별 환경 점수 및 기여 분석
- ✅ 친환경 소비 트렌드 차트 제공

### 8. 관리자 기능
- ✅ 상품 등록 / 수정 / 삭제
- ✅ 환경 활동 참여 승인 / 거부
- ✅ 주문 데이터 확인 및 관리
- ✅ 사용자 리스트 및 상태 관리

### 9. UI/UX 특징
- ✅ 반응형 디자인 (모바일 / PC 대응)
- ✅ Tailwind CSS 기반 디자인 시스템
- ✅ 공통 레이아웃 적용 (TopNav / BottomNav / Layout)
- ✅ React Icons 활용한 직관적 UI

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

## 📌 사전 요구사항

### 공통
- Git
- Docker & Docker Compose

### Backend
- **Java 17** 이상
- **Gradle 8.x**
- **PostgreSQL 15**
- **Upstage API Key** (AI 기능 사용 시)

### Frontend
- **Node.js 20.x** 이상
- **npm 또는 Yarn**

---

## 📥 프로젝트 클론

```bash
# 메인 프로젝트
git clone https://github.com/RE-BUY/RE-BUY-BE.git

# 또는 프론트엔드 저장소도 함께 클론
git clone https://github.com/RE-BUY/RE-BUY-FE.git
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

### 4. 실행

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

## 🎨 Frontend Setup (React + TypeScript)

### 의존성 설치

```bash
yarn install
 또는
npm install
```

### 환경 변수 설정

```bash
cp .env.example .env
```

### 개발 서버 실행

```bash
yarn dev
 또는
npm run dev
```

### 빌드

```bash
yarn build
 또는
npm run build
```

---


## 🐳 Docker로 배포하기

### 빠른 시작 (자동 배포)

```bash
# 1. 환경 변수 설정
cp .env.example .env
# .env 파일 편집하여 실제 값 입력

# 2. 자동 배포 스크립트 실행
./docker-deploy.sh
```

### 수동 배포

```bash
# 1. JAR 파일 빌드
./gradlew clean build -x test

# 2. Docker 이미지 생성
docker build -t rebuy-backend:latest .

# 3. Docker Compose 실행
docker-compose up -d

# 4. 로그 확인
docker-compose logs -f

# 5. 중지
docker-compose down
```

### 클라우드 배포

#### AWS EC2
```bash
# 1. EC2 인스턴스에 파일 업로드
scp -r . ubuntu@your-ec2-ip:~/ReBuy

# 2. SSH 접속
ssh ubuntu@your-ec2-ip

# 3. 배포 실행
cd ReBuy
./docker-deploy.sh
```

#### AWS ECS / Azure Container Instances / GCP Cloud Run
상세한 가이드는 `Docker_클라우드_배포_가이드.txt` 참고

---

## 📚 API 문서
=======

## 📚 API 연동 및 문서

### API 연동
- **Base URL** : .env에서 설정
- Axios로 REST API 호출 (로그인, 상품, 주문, 장바구니 등)
- JWT 토큰은 AuthContext에서 관리

### Swagger UI
http://localhost:8080/swagger-ui.html

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

### Backend

```bash
# 전체 테스트 실행
./gradlew test

# 특정 테스트 실행
./gradlew test --tests com.rebuy.service.ProductServiceTest

# 테스트 커버리지 확인
./gradlew jacocoTestReport
```
### Frontend

```bash
# 전체 테스트 실행
yarn test
 또는
npm run test
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

### Docker 이미지 빌드

```bash
# Docker 이미지 빌드
docker build -t rebuy-backend:latest .

# Docker 실행
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/rebuy \
  -e UPSTAGE_API_KEY=your-api-key \
  --name rebuy-backend \
  rebuy-backend:latest

# 로그 확인
docker logs -f rebuy-backend

# 중지 및 제거
docker stop rebuy-backend
docker rm rebuy-backend
```

**또는 Docker Compose 사용 (추천):**
```bash
docker-compose up -d
```

---

## 👥 팀원

| 이름  | 역할 | 담당 기능                                 |
|-----|------|---------------------------------------|
| 이정민 | Backend Developer | 인증, 상품, 주문, 결제, AI 추천 시스템, Upstage 연동 |
| 김서준 | Backend Developer | 사용자 관리 , 환경 활동, 크레딧 시스템                             | 최예윤   | Frontend Developer | 카테고리 및 상품 상세 페이지, 환경 활동 참여 / 인증, 장바구니 페이지, API 연동 |
| 우윤수   | Frontend Developer | 홈 화면, 회원가입 및 로그인, 마이페이지, 구매내역, 포인트 조회 |


---

<div align="center">
[⬆ 맨 위로 돌아가기](#rebuy-backend-)

</div>

