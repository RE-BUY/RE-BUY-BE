#!/bin/bash

echo "=========================================="
echo "RE:BUY Backend API 전체 테스트"
echo "=========================================="
echo ""

# 색상 코드
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 테스트 결과 카운터
TOTAL=0
PASSED=0
FAILED=0

# 토큰 변수
TOKEN=""
USER_ID=""

# 테스트 함수
test_api() {
    local name=$1
    local method=$2
    local url=$3
    local data=$4
    local expected_status=$5

    TOTAL=$((TOTAL + 1))

    echo -n "[$TOTAL] $name ... "

    if [ -z "$data" ]; then
        if [ -z "$TOKEN" ]; then
            RESPONSE=$(curl -s -w "\n%{http_code}" -X $method "$url")
        else
            RESPONSE=$(curl -s -w "\n%{http_code}" -X $method "$url" -H "Authorization: Bearer $TOKEN")
        fi
    else
        if [ -z "$TOKEN" ]; then
            RESPONSE=$(curl -s -w "\n%{http_code}" -X $method "$url" -H "Content-Type: application/json" -d "$data")
        else
            RESPONSE=$(curl -s -w "\n%{http_code}" -X $method "$url" -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" -d "$data")
        fi
    fi

    HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
    BODY=$(echo "$RESPONSE" | sed '$d')

    if [[ "$HTTP_CODE" == "$expected_status" ]] || [[ "$HTTP_CODE" == "200" ]] || [[ "$HTTP_CODE" == "201" ]]; then
        echo -e "${GREEN}✓ PASS${NC} (HTTP $HTTP_CODE)"
        PASSED=$((PASSED + 1))
        return 0
    else
        echo -e "${RED}✗ FAIL${NC} (HTTP $HTTP_CODE)"
        echo "  Response: $BODY" | head -c 100
        FAILED=$((FAILED + 1))
        return 1
    fi
}

# 1. 인증 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "1. 인증 (Authentication) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# 로그인
LOGIN_RESPONSE=$(curl -s -X POST 'http://localhost:8080/api/v1/auth/login' \
  -H 'Content-Type: application/json' \
  -d '{"username":"admin2","password":"Admin123!"}')

TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.accessToken')
echo "[1] POST /api/v1/auth/login"
if [ "$TOKEN" != "null" ] && [ -n "$TOKEN" ]; then
    echo -e "   ${GREEN}✓ PASS${NC} - 로그인 성공"
    echo "   Token: ${TOKEN:0:30}..."
    PASSED=$((PASSED + 1))
else
    echo -e "   ${RED}✗ FAIL${NC} - 로그인 실패"
    FAILED=$((FAILED + 1))
fi
TOTAL=$((TOTAL + 1))

echo ""

# 2. 마이페이지 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "2. 마이페이지 (MyPage) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

MYPAGE_RESPONSE=$(curl -s -X GET 'http://localhost:8080/api/mypage' -H "Authorization: Bearer $TOKEN")
USER_ID=$(echo $MYPAGE_RESPONSE | jq -r '.username')

echo "[2] GET /api/mypage"
if [ "$USER_ID" == "admin2" ]; then
    echo -e "   ${GREEN}✓ PASS${NC} - 마이페이지 조회 성공"
    echo "   Username: $USER_ID"
    PASSED=$((PASSED + 1))
else
    echo -e "   ${RED}✗ FAIL${NC} - 마이페이지 조회 실패"
    FAILED=$((FAILED + 1))
fi
TOTAL=$((TOTAL + 1))

echo ""

# 3. 상품 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "3. 상품 (Products) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

test_api "전체 상품 조회" "GET" "http://localhost:8080/api/v1/products" "" "200"
test_api "상품 상세 조회" "GET" "http://localhost:8080/api/v1/products/11" "" "200"
test_api "카테고리별 조회 (FOOD)" "GET" "http://localhost:8080/api/v1/products?category=FOOD" "" "200"

echo ""

# 4. 장바구니 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "4. 장바구니 (Cart) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

test_api "장바구니 조회" "GET" "http://localhost:8080/api/v1/cart" "" "200"
test_api "장바구니 비우기" "DELETE" "http://localhost:8080/api/v1/cart/clear" "" "200"
test_api "상품 추가" "POST" "http://localhost:8080/api/v1/cart/items" '{"productId":4,"quantity":2}' "201"
test_api "장바구니 재조회" "GET" "http://localhost:8080/api/v1/cart" "" "200"

echo ""

# 5. 주문 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "5. 주문 (Orders) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

test_api "주문 생성" "POST" "http://localhost:8080/api/v1/orders/checkout/6" '{"creditToUse":0,"receiverName":"테스트","address":"서울시 강남구","contactPhone":"010-1234-5678"}' "201"

echo ""

# 6. 환경 활동 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "6. 환경 활동 (Activities) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

test_api "활동 목록 조회" "GET" "http://localhost:8080/api/v1/activities" "" "200"
test_api "활동 상세 조회" "GET" "http://localhost:8080/api/v1/activities/1" "" "200"

echo ""

# 7. AI 추천 API 테스트
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "7. AI 추천 (AI Recommendations) API"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

test_api "AI 상품 추천" "GET" "http://localhost:8080/api/v1/ai/recommendations?limit=5" "" "200"
test_api "월별 리포트" "GET" "http://localhost:8080/api/v1/ai/report/current" "" "200"

echo ""

# 최종 결과
echo "=========================================="
echo "테스트 결과 요약"
echo "=========================================="
echo "총 테스트: $TOTAL"
echo -e "${GREEN}성공: $PASSED${NC}"
echo -e "${RED}실패: $FAILED${NC}"
echo ""

if [ $FAILED -eq 0 ]; then
    echo -e "${GREEN}✓ 모든 테스트 통과!${NC}"
    exit 0
else
    echo -e "${YELLOW}⚠ 일부 테스트 실패${NC}"
    exit 1
fi

