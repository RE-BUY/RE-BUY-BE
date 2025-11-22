#!/bin/bash
echo "=== 해커톤 데모용 admin2 계정 구매 이력 생성 스크립트 ==="
echo ""
# admin2 로그인
echo "1. admin2 로그인 중..."
TOKEN=$(curl -s -X POST 'http://localhost:8080/api/v1/auth/login' \
  -H 'Content-Type: application/json' \
  -d '{"username":"admin2","password":"Admin123!"}' \
  | jq -r '.accessToken')
if [ -z "$TOKEN" ] || [ "$TOKEN" == "null" ]; then
    echo "[ERROR] 로그인 실패"
    exit 1
fi
echo "[OK] 로그인 성공!"
# 마이페이지에서 userId 가져오기
echo "2. 사용자 ID 조회 중..."
MYPAGE=$(curl -s -X GET 'http://localhost:8080/api/mypage' \
  -H "Authorization: Bearer $TOKEN")
USER_ID=$(echo $MYPAGE | jq -r '.userId')
if [ -z "$USER_ID" ] || [ "$USER_ID" == "null" ]; then
    echo "[ERROR] 사용자 ID를 가져올 수 없습니다."
    echo "Response: $MYPAGE"
    exit 1
fi
echo "[OK] User ID: $USER_ID"
echo ""
# 주문 1
echo "3. 첫 번째 주문 생성 중 (대나무 칫솔 #!/bin/bash
echo "=== 해커톤 데모용 admin2 계정 구?1echo "=== ececho ""
# admin2 로그인
echo "1. admin2 로그인 중..."
TOKEN=$(curl -s -X Pat# admionecho "1. admin2 ?tTOKEN=$(curl -s -X POST 'http://ty  -H 'Content-Type: application/json' \
  -d '{"username":"adm    "p  -d '{"username":"admin2","password":":  | jq -r '.accessToken')
if [ -z "$TOKEN" ] || [ "$/nif [ -z "$TOKEN" ] || [ "[    echo "[ERROR] 로그인 실패"
    exit 1
fier    exit 1
fi
echo "[OK] 로그인 1fi
echo "$(ech# 마이페이지에서 user//echo "2. 사용자 ID 조회 중..."
MYPAG??MYPAGE=$(curl -s -X GET 'http://loc??  -H "Authorization: Bearer $TOKEN")
USER_ID=$(echo $MYPAGE -USER_ID=$(echo $MYPAGE | jq -r '.usi/if [ -z "$USER_ID" ] || [ "$USER_ID" == ho    echo "[ERROR] 사용자 ID를 가져올 수 없?t    echo "Response: $MYPAGE"
    exit 1
fi
echo "[OK] User ID:     exit 1
fi
echo "[OK] Ustifi
echo "
 e  echo ""
# 주문 1
echo "3. ? # 주??echo "3. ",echo "=== 해커톤 데모용 admin2 계정 구?1echo "=== ecechoOR# admin2 로그인
echo "1. admin2 로그인 중..."
TOKEN=$(curl -s? echo "1. admin2 ?OTOKEN=$(curl -s -X Pat# admionec    -d '{"username":"adm    "p  -d '{"username":"admin2","password":":  | jq -r '.accessToken')
if [ -z "$TOKEN" ] || [ "$/ "if [ -z "$TOKEN" ] || [ "$/nif [ -z "$TOKEN" ] || [ "[    echo "[ERROR] 로그인 실패"
  OS    exit 1
fier    exit 1
fi
echo "[OK] 로그인 1fi
echo "$(ech# 마이페이지에서$Tfier    e -fi
echo "[OK]pee aecho "$(ech# 마이페?'MYPAG??MYPAGE=$(curl -s -X GET 'http://loc??  -H "Authorization: Bearer $TOK":USER_ID=$(echo $MYPAGE -USER_ID=$(echo $MYPAGE | jq -r '.usi/if [ -z "$USER_ID"23    exit 1
fi
echo "[OK] User ID:     exit 1
fi
echo "[OK] Ustifi
echo "
 e  echo ""
# 주문 1
echo "3. ? # 주??echo "3. ",echo "=== 해커톤 데모용 admin2 계정 구?1echo "===??fi
echo "?: fi
echo "[OK] Ustifi
echo "
 age /echo "
 e  echo ?  e  e?"# 주문 1epecho "3. ??echo "1. admin2 로그인 중..."
TOKEN=$(curl -s? echo "1. admin2 ?OTOKEN=$(curl -s -X Pat# admionec    -d '{"uselhTOKEN=$(curl -s? echo "1. admin2$Uif [ -z "$TOKEN" ] || [ "$/ "if [ -z "$TOKEN" ] || [ "$/nif [ -z "$TOKEN" ] || [ "[    echo "[ERROR] 로그인 실패"
  OS    exit 1
fier    exit 1
fi
echo "[OK]"q  OS    exit 1
fier    exit 1
fi
echo "[OK] 로그인 1fi
echo "$(ech# 마이페이지에서$Tfier    e -fi
echo "[OK01fier    exit 
 fi
echo "[OK]":e0
echo "$(ech# 마이페?|echo "[OK]pee aecho "$(ech# 마이페?'MYPAG??Mo fi
echo "[OK] User ID:     exit 1
fi
echo "[OK] Ustifi
echo "
 e  echo ""
# 주문 1
echo "3. ? # 주??echo "3. ",echo "=== 해커톤 데모용 admin2 계정 구?1echo "===??fi
echo "?: fi
echo "[OK] Usti??e??fi
echo "[OK] Ustifi
echo "
 ??cho "
 e  echo cu e  e -# 주문 1tpecho "3. osecho "?: fi
echo "[OK] Ustifi
echo "
 age /echo "
 e  echo ?  e  e?"# 주문 1epecho "3. ?t-echo "[OK] Uatecho "
 age /ech ' age  " e  echo ? prTOKEN=$(curl -s? echo "1. admin2 ?OTOKEN=$(curl -s -X Pat# admionec    -Id  OS    exit 1
fier    exit 1
fi
echo "[OK]"q  OS    exit 1
fier    exit 1
fi
echo "[OK] 로그인 1fi
echo "$(ech# 마이페이지에서$Tfier    e -fi
echo "[OK01fier    exit 
 fi
echo "[OK]":e0
echo "$(ech# 마이페?|echo "[OK]peORfier    exit '.fi
echo "[OK]see  fier    exit 1
fi
echo "[??fi
echo "[OK]OReERecho "$(ech# 마이페??cho "[OK01fier    exit 
 fi
echo "[OK]":e0
echo== fi
echo "[OK]":e0
echo==ec==echo "$(ech# ??echo "[OK] User ID:     exit 1
fi
echo "[OK] Ustifi
echo "
 e  echo ""
# 주-sfi
echo "[OK] Ustifi
echo "
 0/epiecho "
 e  echo "A e  eiz# 주문 1reecho "3. )
echo "?: fi
echo "[OK] Usti??e??fi
echo "[OK] Ustifi
echo "
 ??cho "
 e  echo cu e  e INecho "[OK] U'.echo "[OK] Ustifi
ech"[echo "
 ??ch ???O e  echo cu
eecho "[OK] Ustifi
echo "
 age /echo "
 e  echo ?  e  $Techo "
 age /ech"n age ]  e  echo ? L_ age /ech ' age  " e  echo ? prTOKEN=$(curl -s? echo "1. admijqfier    exit 1
fi
echo "[OK]"q  OS    exit 1
fier    exit 1
fi
echo "[OK] 로그인 1fi
echo "$(ech# 마이페이지에?i
echo "[OK]??e??fier    exit 1
fi
echo "[chfi
echo "[OK]ageerecho "$(ech# 마이페?secho "[OK01fier    exit 
 fi
echo "[OK]":e0
echo
E fi
echo "[OK]":e0
echoeoecmiecho "$(ech# e-echo "[OK]see  
\
