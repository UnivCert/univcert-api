# 🚀 Library for UnivCert API Service

### 2025.07.01 서비스종료


### 🐣 초보자를 위한 UnivCert의 라이브러리 사용법은 [해당 사이트](https://github.com/in-seo/univcert)에 기재되어 있습니다.

> 전체 구성도
> 

![image](https://github.com/UnivCert/univcert-api/assets/94730032/5c5e9a10-bb53-4f2f-bfc6-af8d48557469)


> 실 적용 사례
> 

![Slide_16_9_-_13](https://user-images.githubusercontent.com/94730032/218089897-941512eb-1eef-4f27-99ea-5fe13ccd37bd.svg)

![Slide_16_9_-_14](https://user-images.githubusercontent.com/94730032/218089900-9b5ff24f-07c0-4978-9395-9e1242fb1d13.svg)

---

💡 API 설명

> Request
> 

`key`: 부여받은 API_KEY

`univName` : 대학교명

`email` : 이용자 이메일

`univ_check` : 대학 재학 여부(도메인) 체킹 [boolean] 

   (true라면 해당 대학 재학 여부, false라면 메일 소유 인증만)

`code` : 전송된 인증번호 (4자리)

---

> Response
> 

`success` : API 통신 성공 여부

`message` : success가 false 시에 전달되는 오류 메시지

`certified_email` : 인증된 이메일

`certified_date` : 해당 메일이 인증된 일자

`data` : jsonArray List 출력값

---

```
[POST] univcert.com/api/v1/certify 요청 
이용자 메일 인증 시작 !

{
  “key” : “부여받은 API KEY”,
  "email” : “abc@mail.hongik.ac.kr”,
  “univName” : “홍익대학교”,
  “univ_check” : true  
	(true라면 해당 대학 재학 여부, false라면 메일 소유 인증만)
}
```

```
[POST] univcert.com/certify 응답

response (메일 인증번호 4자리 전송 성공)
{
  “success” : true
}

response (실패) 
{
  "status" : 400,
  "success" : false,
  "message" : 하단 메시지 표 참고
}
```

- 에러 메시지 (이유)
    1. 잘못된 파라미터 입력
    2. 잘못된 이메일 형식 (남은 시도 횟수 출력 MAX = 3)
    3. 일일 시도 가능 횟수 초과한 이메일
    4. (univ_check = true 시에) 대학 도메인과 불일치 
    5. 이미 인증된 이메일
    6. 존재하지 않는 API_KEY.

---

```
[POST] univcert.com/certifycode 요청
이용자 메일에 발송된 인증코드를 전달받아 요청하기

{
  “key” : “부여받은 API KEY”
  “univName” : “홍익대학교”,
  “email” : "**abc@mail.hongik.ac.kr**”,
  “code” : 3816
}
```

```
[POST] univcert.com/certifycode 응답

response (인증번호 일치시)
{
  “success” : true,
  “univName” : “홍익대학교”,
  “certified_email” : “abc@mail.hongik.ac.kr”,
  “certified_date” : “2023-01-03T09:30:22”
}

response (실패)
{
  "status" : 400
  “success” : false,
  “message” : 하단 메시지 참고
}
```

- 에러 메시지
    1. 잘못된 파라미터 입력
    2. 잘못된 이메일 형식
    3. 인증 요청 이력이 없는 이메일.
    4. 인증번호 미일치
    5. 존재하지 않는 API_KEY.

---

```
[POST] univcert.com/status 요청
인증된 이메일인지 체킹 기능

request
{
  “key” : “부여받은 API KEY”,
  "email” : “insi2000@mail.hongik.ac.kr” 
}
```

```
[POST] univcert.com/status 응답
response
{
  “success” : true,
  “certified_date” : “2023-01-03T09:30:22”
}

response (실패)
{
  “success” : false,
  “message” : 하단 메시지 참고
}
```

- 에러 메시지
    1. 잘못된 파라미터 입력
    2. 잘못된 이메일 형식
    3. 인증 요청 이력이 없는 이메일입니다.
    4. 인증코드를 입력해야 되는 단계입니다.
    5. 존재하지 않는 API_KEY.

---

```
[Post] univcert.com/certifiedlist 요청
인증된 유저 리스트 출력

request
{
  “key” : “부여받은 API KEY”
}
```

```
[Post] univcert.com/certifiedlist 응답

response
{
  "data": [
    {
      "email": "insi2000@mail.hongik.ac.kr",
      "univName": "홍익대학교",
      "certified_date" : "2023-01-26T04:52:04.179837"
      "count": 1,
      "certified": true
    },
		{
			...
		}
  ],
  "success": true
}

response (실패)
{
  "status" : 400
  “success” : false,
  “message” : 하단 메시지 참고
}
```

- 에러 메시지
    1. 잘못된 파라미터 입력
    2. 잘못된 이메일 형식
    3. 존재하지 않는 API_KEY.

---

```
[POST] univcert.com/check 요청
인증 가능한 대학명인지 체킹

request
{
  "univName" : "ㅇㅇ대학교"
}
```

```
[Post] univcert.com/check 응답

response
{
  "success": true
}

response (실패)
{
  "status" : 400
  “success” : false,
  “message” : 하단 메시지 참고
}
```

- 에러 이유
    1. 서버에 존재하지 않는 대학명 ( 22년 기준 입학생 상위 120개 대학)
    2. 대학명 형식 오류 (~~대학교)

---

```
5/20 추가 !
[Post] univcert.com/clear 요청
현재 인증 된 유저목록 초기화

request
{
  “key” : “부여받은 API KEY”
}
```

```
[Post] univcert.com/clear 응답
response
{
  "success": true
}
```

**상태코드 반환 표**

| CODE | DESCRIPTION |
| --- | --- |
| 200(Success) | 성공. |
| 400(Bad Request) | 잘못된 요청 (요청 값 형식, 타입 오류, 존재하지 않는 이메일 …) |
| 500(Server error) | 서버 측 에러.  최대한 노력해보겠습니다.. |
