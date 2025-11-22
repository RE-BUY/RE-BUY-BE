# RE:BUY Backend

친환경 제품 구매로 적립한 크레딧을 지역 환경 활동 참여에 사용하고, 활동 보상으로 다시 제품을 할인 구매하는 선순환 녹색 소비 플랫폼

## 기술 스택

- **Framework:** Spring Boot 3.2.1
- **Language:** Java 17
- **Build Tool:** Gradle
- **Database:** PostgreSQL
- **Cache:** Redis
- **Security:** Spring Security + JWT
- **Documentation:** Swagger/OpenAPI
- **AI:** Upstage Solar-Pro2

## 프로젝트 설정

### 1. 프로젝트 클론
```bash
git clone https://github.com/RE-BUY/RE-BUY-BE.git
cd RE-BUY-BE
```

### 2. PostgreSQL 실행 (Docker)
```bash
docker run --name rebuy-postgres \
  -e POSTGRES_DB=rebuy \
  -e POSTGRES_USER=rebuy_user \
  -e POSTGRES_PASSWORD=rebuy_password \
  -p 5432:5432 \
  -d postgres:15
```

### 3. Upstage API 키 설정 (AI 추천 시스템)

#### 방법 1: application-local.yml 생성 (추천)
```bash
# 템플릿 복사
cp src/main/resources/application-local.yml.example \
   src/main/resources/application-local.yml

# 편집해서 실제 API 키 입력
vi src/main/resources/application-local.yml
```

```yaml
# application-local.yml
upstage:
  api:
    key: your-actual-upstage-api-key
```

⚠️ **주의**: `application-local.yml`은 Git에 커밋되지 않습니다 (.gitignore에 포함됨)

#### 방법 2: 환경 변수 사용
```bash
export UPSTAGE_API_KEY="your-actual-upstage-api-key"
```

### 4. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 4. 애플리케이션 실행
```bash
./gradlew bootRun
```

### 5. 접속 확인
- 서버: http://localhost:8080
- API 문서: http://localhost:8080/swagger-ui.html

### 6. 주요 기능
- 사용자 인증 및 회원가입 
- 친환경 제품 카탈로그 
- 환경 영향 시각화 (LCI 데이터 기반)
- 크레딧 적립 및 사용 
- 지역 환경 활동 관리 
- **AI 기반 맞춤 제품 추천** (Upstage Solar-Pro2)
- **월별 녹색 소비 리포트 생성**

### 7. AI 추천 시스템 사용법

#### 로그인 및 토큰 받기
```bash
TOKEN=$(curl -s -X POST 'http://localhost:8080/api/v1/auth/login' \
  -H 'Content-Type: application/json' \
  -d '{"username":"your-username","password":"your-password"}' \
  | jq -r '.accessToken')
```

#### AI 상품 추천
```bash
curl -X GET 'http://localhost:8080/api/v1/ai/recommendations?limit=5' \
  -H "Authorization: Bearer $TOKEN" | jq
```

#### 월별 녹색 소비 리포트
```bash
curl -X GET 'http://localhost:8080/api/v1/ai/report/current' \
  -H "Authorization: Bearer $TOKEN" | jq
```

### 8. 프로젝트 구조
```bash
src/main/java/com/rebuy/
├── controller/     # REST API 컨트롤러
├── service/        # 비즈니스 로직
├── repository/     # 데이터 액세스
├── entity/         # JPA 엔티티
├── dto/           # 데이터 전송 객체
├── config/        # 설정 파일
├── util/          # 유틸리티 클래스
└── exception/     # 예외 처리
```

### 9. 환경 설정
- application.yml 
  - 주요 설정값들이 src/main/resources/application.yml에 정의되어 있습니다.
- Docker 명령어
- ```bash
    # PostgreSQL 컨테이너 시작
    docker start rebuy-postgres

    # PostgreSQL 컨테이너 중지
    docker stop rebuy-postgres

    # 컨테이너 상태 확인
    docker ps
    ```
