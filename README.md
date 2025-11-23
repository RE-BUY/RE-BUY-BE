# RE:BUY 🌱

> 친환경 제품 구매로 적립한 크레딧을 지역 환경 활동 참여에 사용하고, 활동 보상으로 다시 제품을 할인 구매하는 **선순환 녹색 소비 플랫폼**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-FF7800?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.java.net/)
[![React](https://img.shields.io/badge/React-18-087EA4?style=for-the-badge&logo=react&logoColor=white)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.0-3178C6?style=for-the-badge&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Redis](https://img.shields.io/badge/Redis-7.x-DC382D?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Vite](https://img.shields.io/badge/Vite-5.0-646CFF?style=for-the-badge&logo=vite&logoColor=white)](https://vitejs.dev/)
[![TailwindCSS](https://img.shields.io/badge/TailwindCSS-3.0-06B6D4?style=for-the-badge&logo=tailwindcss&logoColor=white)](https://tailwindcss.com/)

---

## 📋 목차

- [서비스 개요](#-서비스-개요)
- [타 서비스와의 차별점](#-타-서비스와의-차별점)
- [주요 기능](#-주요-기능)
- [구현 내용 및 결과물](#-구현-내용-및-결과물)
- [기술 스택](#-기술-스택)
- [시작하기](#-시작하기)
- [API 문서](#-api-문서)
- [향후 개선 및 발전 방안](#-향후-개선-및-발전-방안)
- [팀원](#-팀원)

---

## 🌟 서비스 개요

**RE:BUY**는 소비자들이 일상에서 쉽게 친환경 제품을 구매하고, 탄소중립 실천에 참여할 수 있도록 돕는 **AI 기반 친환경 쇼핑 플랫폼**입니다.

### 핵심 기능
1. **AI 맞춤형 추천**: Upstage Solar-Pro2를 활용하여 사용자의 과거 구매 패턴을 분석하고 개인화된 친환경 제품을 추천합니다.
2. **크레딧 시스템**: 구매 금액의 5%를 크레딧으로 자동 적립하여 다음 구매 시 사용 가능하며, 지역 환경 활동 참여를 통해 추가 크레딧을 획득할 수 있습니다.
3. **환경 기여도 분석**: 월별 구매 통계와 탄소 절감량, 물 절약량 등을 직관적인 대시보드로 시각화하여 제공합니다.

사용자는 단순히 제품을 구매하는 것을 넘어, 자신의 소비가 환경에 미치는 긍정적 영향을 확인하고, 오프라인 환경 활동에도 참여할 수 있는 **통합 친환경 경험**을 제공받습니다.

### 핵심 가치
- 🤖 **AI 기반 개인화**: 구매 패턴 분석을 통한 맞춤형 상품 추천
- 💳 **선순환 구조**: 구매 → 적립 → 환경 활동 → 재구매의 지속 가능한 사이클
- 📊 **가시화된 임팩트**: 개인의 환경 기여도를 수치와 그래프로 명확히 확인
- 🌍 **온·오프라인 연계**: 쇼핑과 지역 환경 활동을 하나의 플랫폼에서 통합 관리

---

## 🎯 타 서비스와의 차별점

### 1. AI 기반 개인화 추천 시스템
기존 친환경 쇼핑몰과 달리 **Upstage Solar-Pro2** AI 모델을 활용하여:
- 개인 맞춤형 친환경 제품 추천
- 녹색 소비 리포트 자동 생성
- 환경 기여도 인사이트 제공

### 2. 선순환 크레딧 생태계
단순 적립이 아닌 **선순환 구조**:
- 제품 구매 시 5% 크레딧 자동 적립
- 지역 환경 활동(플로깅 등) 참여로 추가 크레딧 획득
- 크레딧으로 다시 친환경 제품 구매 가능

### 3. 환경 임팩트 가시화
추상적인 개념이 아닌 **구체적인 수치**로:
- 개인별 CO2 절감량 실시간 계산
- 물 절약량, 나무 보호 효과 등 직관적 지표 제공
- 월별/연도별 환경 기여도 트렌드 분석
- 사용자 랭킹 시스템으로 동기 부여

### 4. 온·오프라인 통합 경험
온라인 쇼핑과 오프라인 활동의 연결:
- 플랫폼 내에서 지역 환경 활동 신청
- 활동 인증샷 제출 및 관리자 승인 시스템
- 활동 참여 이력 및 보상 관리
- 온라인 소비와 오프라인 실천의 통합

### 5. 모바일 최적화 플랫폼
- 상품 구매부터 환경 활동 참여, 기여도 분석까지 **하나의 플랫폼**에서 완결
- 일관된 UX/UI로 seamless한 사용자 경험
- 모바일 최적화된 반응형 디자인

---

## ✨ 주요 기능

### 🔐 1. 사용자 인증 및 관리
- 회원가입 및 이메일 인증
- JWT 기반 안전한 로그인/로그아웃
- 비밀번호 찾기 및 재설정
- 사용자 프로필 관리 및 수정
- 로그인 상태 실시간 반영

### 🛍️ 2. 친환경 상품 쇼핑
- **22개 실제 친환경 제품** 카탈로그
- 5개 카테고리: 식품, 음료, 패션, 문구류, 생활용품
- 환경 점수 시스템 (0~100점)
- 상품별 환경 영향 데이터 (CO2, 물, 석유, 플라스틱 절감량)
- 상품 검색 및 카테고리별 필터링
- 상세 페이지에서 환경 정보 시각화
- 반응형 카드형 UI

### 🛒 3. 장바구니 및 주문
- 장바구니 추가/수정/삭제 기능
- 수량 변경 및 실시간 가격 계산
- 주문 생성 및 배송지 입력
- 크레딧 사용 옵션
- 주문 내역 조회 및 상태 추적
- 주문 취소 기능

### 💰 4. 크레딧 시스템
- 구매 금액의 **5% 자동 적립**
- 실시간 크레딧 잔액 조회
- 크레딧 거래 내역 확인
- 다음 구매 시 크레딧 사용
- 환경 활동 참여 시 추가 보상

### 🌱 5. 환경 활동 관리
- 지역 환경 활동(플로깅 등) 목록 조회
- 활동 상세 정보 및 참여 신청
- 활동 인증샷 업로드
- 관리자 승인 후 크레딧 보상 지급
- 내 참여 이력 및 인증 상태 확인

### 🤖 6. AI 추천 시스템 (Upstage Solar-Pro2)
- **사용자 구매 패턴 학습 및 분석**
- AI 기반 맞춤형 상품 추천
- 녹색 소비 리포트 자동 생성
- 환경 기여도 분석 및 예측
- 사용자 랭킹 및 달성 과제 제시
- 맞춤형 인사이트 제공

### 📊 7. 마이페이지 및 대시보드
- **프로필 정보** 조회 및 수정
- **구매 내역** 확인
- **크레딧 잔액** 및 거래 내역
- **월별 환경 기여 통계** (CO2 절감량, 물 절약량 등)
- **환경 점수** 및 랭킹 확인
- **참여한 환경 활동** 이력
- 시각화된 차트 및 그래프

### 🔧 8. 관리자 기능
- 상품 등록/수정/삭제
- 주문 관리 및 모니터링
- 환경 활동 참여 승인/거부
- 사용자 관리
- 크레딧 보상 지급 관리

### 🎨 9. UI/UX 특징
- **완전 반응형** 디자인 (모바일/태블릿/데스크톱)
- Tailwind CSS 기반 일관된 디자인 시스템
- 직관적인 네비게이션 (TopNav / BottomNav)
- React Icons 활용한 세련된 아이콘
- 로딩 상태 및 에러 처리
- 부드러운 페이지 전환

---

## 💡 구현 내용 및 결과물

### ✅ 완성된 풀스택 웹 애플리케이션
- **Frontend**: React 18 + TypeScript + Vite 기반 SPA
- **Backend**: Spring Boot 3.2.1 + Java 17 RESTful API
- **Database**: PostgreSQL 15 + Redis 7.x
- **AI Integration**: Upstage Solar-Pro2 연동
- **Deployment**: Docker & Docker Compose 컨테이너화

### ✅ 실제 데이터 기반 서비스
- **22개 실제 친환경 제품** 데이터베이스 구축
- **5개 카테고리** 체계적 분류

### ✅ 완전한 사용자 플로우 구현
1. **회원가입 → 로그인 → 이메일 인증**
2. **상품 검색/탐색 → 장바구니 담기**
3. **주문 생성 → 크레딧 적립**
4. **환경 활동 신청 → 인증샷 제출 → 추가 크레딧 획득**
5. **AI 리포트 확인 → 맞춤 추천 받기**
6. **마이페이지에서 환경 기여도 확인**

### ✅ 보안 및 인증
- **JWT 기반** 토큰 인증
- **BCrypt** 비밀번호 암호화
- **Spring Security** 권한 관리
- **이메일 인증** 시스템
- API 키 안전 관리

### ✅ AI 기능 통합
- **Upstage Solar-Pro2** 실시간 연동
- **WebFlux** 기반 비동기 통신
- 구매 패턴 분석 및 추천
- 녹색 소비 리포트 자동 생성
- 환경 기여도 계산 알고리즘

### ✅ 사용자 경험 최적화
- **반응형 디자인**: 모든 디바이스 완벽 대응
- **직관적인 UI**: Tailwind CSS 기반 깔끔한 디자인
- **실시간 피드백**: 로딩 상태, 성공/에러 메시지
- **Context API**: 전역 상태 관리 (인증, 장바구니)
- **React Router 6**: 부드러운 페이지 전환

### ✅ 개발 인프라
- **Swagger/OpenAPI 3.0**: 자동화된 API 문서
- **Docker Compose**: 원클릭 로컬 환경 구축
- **GitHub Actions**: CI/CD 파이프라인
- **JUnit 5 & Mockito**: 백엔드 테스트
- **환경별 설정 분리**: dev, prod, local

### ✅ 데이터 시각화
- 환경 기여도 차트 (Chart.js / Recharts)
- 월별 트렌드 그래프
- 사용자 랭킹 시스템
- 크레딧 거래 내역 타임라인

---

## 🛠️ 구현 방식

### 🎨 Frontend

**기술 스택**: React 18 + TypeScript + Vite + Tailwind CSS

**주요 구현**:
- **React 18**과 **TypeScript**로 타입 안정성 확보
- **Vite**로 빠른 개발 환경 및 HMR(Hot Module Replacement)
- **Tailwind CSS**로 유틸리티 우선 스타일링 및 일관된 디자인 시스템
- **React Router 6**로 SPA 라우팅 및 중첩 라우트 구현
- **Axios**로 RESTful API 비동기 통신 및 인터셉터 설정
- **Context API**로 전역 상태 관리 (AuthContext, CartContext)
- **React Icons**로 일관된 아이콘 사용
- **반응형 디자인**으로 모바일 퍼스트 UI 구현

**주요 컴포넌트**:
- Layout 시스템 (TopNav, BottomNav, MainLayout)
- 페이지 컴포넌트 (Home, Products, Cart, MyPage, Activities)
- 재사용 가능한 UI 컴포넌트 (Button, Card, Modal)
- Protected Route로 인증 라우팅 처리

### ⚙️ Backend

**기술 스택**: Java 17 + Spring Boot 3.2.1 + Spring Data JPA

**주요 구현**:
- **Spring Boot 3.2.1** 기반 RESTful API 서버
- **Spring Data JPA**로 ORM 및 Repository 패턴
- **Spring Security 6.x** + **JWT**로 인증/인가
- **WebFlux**로 Upstage AI와 비동기 통신
- **Swagger/OpenAPI 3.0**으로 API 자동 문서화
- **JUnit 5 & Mockito**로 단위/통합 테스트
- **Gradle 8.14**로 의존성 및 빌드 관리
- **Global Exception Handler**로 통합 예외 처리

**계층 구조**:
- Controller: REST API 엔드포인트 정의
- Service: 비즈니스 로직 구현
- Repository: 데이터 액세스 레이어
- Entity: JPA 엔티티 및 연관관계 매핑
- DTO: 데이터 전송 객체로 레이어 간 통신

### 🗄️ Database

**기술 스택**: PostgreSQL 15 + Redis 7.x

**주요 구현**:
- **PostgreSQL 15**: 관계형 데이터 저장 (사용자, 상품, 주문 등)
- **JPA Entity** 설계 및 연관관계 매핑 (OneToMany, ManyToOne)
- 인덱스 최적화로 검색 성능 향상
- 트랜잭션 관리 및 격리 수준 설정
- **Redis 7.x**: 세션 캐시 및 임시 데이터 저장

**데이터 모델**:
- 9개 주요 테이블 (User, Product, Order, Cart, Activity, Credit 등)
- 외래키 제약조건 및 cascade 설정
- Enum 타입으로 상태 관리 (OrderStatus, ParticipationStatus 등)

### 🤖 AI Integration

**기술 스택**: Upstage Solar-Pro2

**주요 구현**:
- **Upstage Solar-Pro2** REST API 연동
- **WebClient (WebFlux)**로 비동기 호출
- 사용자 구매 이력 기반 프롬프트 생성
- AI 응답 파싱 및 구조화
- 월별 리포트 자동 생성 스케줄링
- 에러 핸들링 및 폴백 메커니즘
- 타임아웃 및 재시도 정책

### 🚀 DevOps & Deployment

**기술 스택**: Docker + Docker Compose + GitHub Actions

**주요 구현**:
- **Docker**: 애플리케이션 컨테이너화
- **Docker Compose**: PostgreSQL, Redis, App 통합 실행
- **GitHub Actions**: CI/CD 파이프라인
- 환경별 설정 파일 분리 (application-{profile}.yml)
- AWS EC2/ECS 등 클라우드 배포 지원
- 자동 빌드 및 배포 스크립트

---

## 🛠 기술 스택

### Frontend
- **Framework**: React 18
- **Language**: TypeScript 5.0
- **Build Tool**: Vite 5.0
- **Styling**: Tailwind CSS 3.0
- **Routing**: React Router 6
- **HTTP Client**: Axios
- **State Management**: Context API
- **Icons**: React Icons

### Backend
- **Framework**: Spring Boot 3.2.1
- **Language**: Java 17
- **Build Tool**: Gradle 8.14
- **ORM**: Spring Data JPA (Hibernate)
- **Security**: Spring Security 6.x + JWT
- **Documentation**: Swagger/OpenAPI 3.0
- **Testing**: JUnit 5, Mockito

### Database & Cache
- **Database**: PostgreSQL 15
- **Cache**: Redis 7.x

### AI & Integration
- **AI Model**: Upstage Solar-Pro2
- **Communication**: WebFlux (비동기)

### DevOps
- **Containerization**: Docker, Docker Compose
- **CI/CD**: GitHub Actions
- **Version Control**: Git, GitHub

---

## 🚀 시작하기

### 사전 요구사항

```bash
# Backend
- Java 17+
- Gradle 8.x
- Docker & Docker Compose

# Frontend
- Node.js 20.x+
- npm or yarn
```

### 설치 및 실행

#### 1️⃣ 저장소 클론
```bash
# Backend
git clone https://github.com/RE-BUY/RE-BUY-BE.git
cd RE-BUY-BE

# Frontend
git clone https://github.com/RE-BUY/RE-BUY-FE.git
cd RE-BUY-FE
```

#### 2️⃣ 데이터베이스 실행 (Backend)
```bash
# Docker Compose로 PostgreSQL + Redis 실행
docker-compose up -d
```

#### 3️⃣ Upstage API 키 설정 (Backend)
```bash
# application-local.yml 생성
cp src/main/resources/application-local.yml.example \
   src/main/resources/application-local.yml

# 파일 편집하여 실제 API 키 입력
upstage:
  api:
    key: your-actual-api-key
```

#### 4️⃣ Backend 실행
```bash
./gradlew bootRun
# 또는
./gradlew build && java -jar build/libs/ReBuy-1.0.0.jar
```

#### 5️⃣ Frontend 실행
```bash
# 의존성 설치
npm install
# 또는 yarn instaladsfl

# 개발 서버 실행
npm run dev
# 또는 yarn dev
```

#### 6️⃣ 접속
- **Frontend**: http://localhost:5173
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html

---

## 📚 API 문서

### Swagger UI

### 주요 API 엔드포인트

#### 🔐 인증 (Authentication)
```
POST   /api/v1/auth/register          # 회원가입
POST   /api/v1/auth/login             # 로그인
POST   /api/v1/auth/logout            # 로그아웃
GET    /api/v1/auth/verify-email      # 이메일 인증
POST   /api/v1/auth/password-reset    # 비밀번호 재설정
```

#### 🛍️ 상품 (Products)
```
GET    /api/v1/products               # 상품 목록 조회
GET    /api/v1/products/{id}          # 상품 상세 조회
GET    /api/v1/products/category/{category}  # 카테고리별 조회
GET    /api/v1/products/search        # 상품 검색
```

#### 🛒 주문 & 장바구니 (Orders & Cart)
```
GET    /api/v1/cart                   # 장바구니 조회
POST   /api/v1/cart/items             # 장바구니 추가
PUT    /api/v1/cart/items/{id}        # 수량 변경
DELETE /api/v1/cart/items/{id}        # 상품 제거

POST   /api/v1/orders                 # 주문 생성
GET    /api/v1/orders                 # 주문 목록 조회
GET    /api/v1/orders/{id}            # 주문 상세 조회
```

#### 🌱 환경 활동 (Activities)
```
GET    /api/v1/activities             # 활동 목록 조회
GET    /api/v1/activities/{id}        # 활동 상세 조회
POST   /api/v1/activities/{id}/apply  # 활동 신청
POST   /api/v1/activities/participations/{id}/verify  # 인증샷 제출
GET    /api/v1/activities/my-participations  # 내 참여 이력
```

#### 🤖 AI 추천 (AI Recommendations)
```
GET    /api/v1/ai/recommendations     # AI 상품 추천
GET    /api/v1/ai/report/current      # 이번 달 리포트
GET    /api/v1/ai/report/monthly      # 특정 월 리포트
```

#### 💰 크레딧 (Credits)
```
GET    /api/v1/credits/balance        # 크레딧 잔액 조회
GET    /api/v1/credits/transactions   # 거래 내역 조회
```

#### 👤 마이페이지 (My Page)
```
GET    /api/v1/mypage/profile         # 프로필 조회
GET    /api/v1/mypage/orders          # 주문 내역
GET    /api/v1/mypage/eco-stats       # 환경 기여 통계
```

---

## 🚀 향후 개선 및 발전 방안

### 1. 소셜 기능 강화 👥
- 친환경 소비 챌린지 및 사용자 간 경쟁 요소
- 환경 기여도 SNS 공유 기능
- 커뮤니티 게시판 및 환경 팁 공유
- 친구 추천 및 그룹 구매 기능
- 사용자 간 메시징 시스템

### 2. AI 기능 고도화 🤖
- 더 정교한 협업 필터링 알고리즘
- 계절별, 날씨별 맞춤 추천
- 가격 변동 예측 및 최적 구매 시점 알림
- NLP 기반 상품 리뷰 분석
- 음성 인식 검색 기능

### 3. 아키텍처 확장성 개선 🏗️
- MSA(Microservices Architecture) 전환
- Kubernetes 기반 오케스트레이션
- WebSocket/SSE 기반 실시간 알림

### 4. 데이터 분석 강화 📊
- Google Analytics, Mixpanel 연동
- A/B 테스트 프레임워크
- 사용자 행동 분석 대시보드
- 환경 영향 계산 알고리즘 고도화
- 빅데이터 기반 트렌드 예측

### 5. 비즈니스 확장 💼
- **B2B 서비스** 개발 (기업 고객용)
- 제휴 판매자 입점 플랫폼
- **모바일 앱** 개발 (React Native/Flutter)
- 탄소중립 인증서 발급
- 블록체인 기반 환경 기여 NFT
- 정기 구독 서비스 모델

### 6. UX/UI 개선 🎨
- 다국어 지원 (i18n) - 영어, 중국어, 일본어
- PWA 전환으로 오프라인 모드 지원
- 애니메이션 및 마이크로 인터랙션 추가

### 7. 결제 및 물류 💳
- 다양한 결제 수단 (카카오페이, 네이버페이, 토스페이)
- 실시간 배송 추적
- 친환경 포장 옵션 선택

### 8. 보안 강화 🔒
- OAuth 2.0 소셜 로그인 (구글, 카카오, 네이버)
- 2FA (이중 인증) 도입
- 암호화 통신 강화 (HTTPS, TLS 1.3)
- 정기적인 보안 감사 및 취약점 스캔

---

## 👥 팀원

| 이름  | 역할 | 담당 기능 |
|-----|------|---------|
| 이정민 | Backend Developer | 인증, 상품, 주문, 결제, AI 추천 시스템, Upstage 연동 |
| 김서준 | Backend Developer | 사용자 관리, 환경 활동, 크레딧 시스템 |
| 최예윤 | Frontend Developer | 카테고리 및 상품 상세 페이지, 환경 활동 페이지 |
| 우윤수 | Frontend Developer | 홈 화면, 회원가입/로그인, 마이페이지, 구매내역, 포인트 조회 |

---

<div align="center">

**Made with 💚 by RE:BUY Team**

[⬆ 맨 위로 돌아가기](#rebuy-)

</div>
