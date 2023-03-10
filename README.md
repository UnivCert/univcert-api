# ๐ย Library for UnivCert API Service

### ๐ฃ ์ด๋ณด์๋ฅผ ์ํ UnivCert์ ๋ผ์ด๋ธ๋ฌ๋ฆฌ ์ฌ์ฉ๋ฒ์ [ํด๋น ์ฌ์ดํธ](https://github.com/in-seo/univcert)์ ๊ธฐ์ฌ๋์ด ์์ต๋๋ค.

> ์ ์ฒด ๊ตฌ์ฑ๋
> 

![Group_224_(1)](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/856d10b3-c413-4600-9f51-29ca51664db8/Group_250.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230211%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230211T023712Z&X-Amz-Expires=86400&X-Amz-Signature=9df9ef4875d1bcb8a2217f16eef2643954037be7d8ce492f0cea8a26443bc59d&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Group%2520250.png%22&x-id=GetObject)

> ์ค ์ ์ฉ ์ฌ๋ก
> 

![Slide_16_9_-_13](https://user-images.githubusercontent.com/94730032/218089897-941512eb-1eef-4f27-99ea-5fe13ccd37bd.svg)

![Slide_16_9_-_14](https://user-images.githubusercontent.com/94730032/218089900-9b5ff24f-07c0-4978-9395-9e1242fb1d13.svg)

---

๐ก API ์ค๋ช

> Request
> 

`key`: ๋ถ์ฌ๋ฐ์ API_KEY

`univName` : ๋ํ๊ต๋ช

`email` : ์ด์ฉ์ ์ด๋ฉ์ผ

`univ_check` : ๋ํ ์ฌํ ์ฌ๋ถ(๋๋ฉ์ธ) ์ฒดํน [boolean] 

   (true๋ผ๋ฉด ํด๋น ๋ํ ์ฌํ ์ฌ๋ถ, false๋ผ๋ฉด ๋ฉ์ผ ์์  ์ธ์ฆ๋ง)

`code` : ์ ์ก๋ ์ธ์ฆ๋ฒํธ (4์๋ฆฌ)

---

> Response
> 

`success` : API ํต์  ์ฑ๊ณต ์ฌ๋ถ

`message` : success๊ฐ false ์์ ์ ๋ฌ๋๋ ์ค๋ฅ ๋ฉ์์ง

`certified_email` : ์ธ์ฆ๋ ์ด๋ฉ์ผ

`certified_date` : ํด๋น ๋ฉ์ผ์ด ์ธ์ฆ๋ ์ผ์

`data` : jsonArray List ์ถ๋ ฅ๊ฐ

---

```
[POST] univcert.com/api/v1/certify ์์ฒญ 
์ด์ฉ์ ๋ฉ์ผ ์ธ์ฆ ์์ !

{
  โkeyโ : โ๋ถ์ฌ๋ฐ์ API KEYโ,
  "emailโ : โabc@mail.hongik.ac.krโ,
  โunivNameโ : โํ์ต๋ํ๊ตโ,
  โuniv_checkโ : true  
	(true๋ผ๋ฉด ํด๋น ๋ํ ์ฌํ ์ฌ๋ถ, false๋ผ๋ฉด ๋ฉ์ผ ์์  ์ธ์ฆ๋ง)
}
```

```
[POST] univcert.com/certify ์๋ต

response (๋ฉ์ผ ์ธ์ฆ๋ฒํธ 4์๋ฆฌ ์ ์ก ์ฑ๊ณต)
{
  โsuccessโ : true
}

response (์คํจ) 
{
  "status" : 400,
  "success" : false,
  "message" : ํ๋จ ๋ฉ์์ง ํ ์ฐธ๊ณ 
}
```

- ์๋ฌ ๋ฉ์์ง (์ด์ )
    1. ์๋ชป๋ ํ๋ผ๋ฏธํฐ ์๋ ฅ
    2. ์๋ชป๋ ์ด๋ฉ์ผ ํ์ (๋จ์ ์๋ ํ์ ์ถ๋ ฅ MAX = 3)
    3. ์ผ์ผ ์๋ ๊ฐ๋ฅ ํ์ ์ด๊ณผํ ์ด๋ฉ์ผ
    4. (univ_check = true ์์) ๋ํ ๋๋ฉ์ธ๊ณผ ๋ถ์ผ์น 
    5. ์ด๋ฏธ ์ธ์ฆ๋ ์ด๋ฉ์ผ
    6. ์กด์ฌํ์ง ์๋ API_KEY.

---

```
[POST] univcert.com/certifycode ์์ฒญ
์ด์ฉ์ ๋ฉ์ผ์ ๋ฐ์ก๋ ์ธ์ฆ์ฝ๋๋ฅผ ์ ๋ฌ๋ฐ์ ์์ฒญํ๊ธฐ

{
  โkeyโ : โ๋ถ์ฌ๋ฐ์ API KEYโ
  โunivNameโ : โํ์ต๋ํ๊ตโ,
  โemailโ : "**abc@mail.hongik.ac.kr**โ,
  โcodeโ : 3816
}
```

```
[POST] univcert.com/certifycode ์๋ต

response (์ธ์ฆ๋ฒํธ ์ผ์น์)
{
  โsuccessโ : true,
  โunivNameโ : โํ์ต๋ํ๊ตโ,
  โcertified_emailโ : โabc@mail.hongik.ac.krโ,
  โcertified_dateโ : โ2023-01-03T09:30:22โ
}

response (์คํจ)
{
  "status" : 400
  โsuccessโ : false,
  โmessageโ : ํ๋จ ๋ฉ์์ง ์ฐธ๊ณ 
}
```

- ์๋ฌ ๋ฉ์์ง
    1. ์๋ชป๋ ํ๋ผ๋ฏธํฐ ์๋ ฅ
    2. ์๋ชป๋ ์ด๋ฉ์ผ ํ์
    3. ์ธ์ฆ ์์ฒญ ์ด๋ ฅ์ด ์๋ ์ด๋ฉ์ผ.
    4. ์ธ์ฆ๋ฒํธ ๋ฏธ์ผ์น
    5. ์กด์ฌํ์ง ์๋ API_KEY.

---

```
[POST] univcert.com/status ์์ฒญ
์ธ์ฆ๋ ์ด๋ฉ์ผ์ธ์ง ์ฒดํน ๊ธฐ๋ฅ

request
{
  โkeyโ : โ๋ถ์ฌ๋ฐ์ API KEYโ,
  "emailโ : โinsi2000@mail.hongik.ac.krโ 
}
```

```
[POST] univcert.com/status ์๋ต
response
{
  โsuccessโ : true,
  โcertified_dateโ : โ2023-01-03T09:30:22โ
}

response (์คํจ)
{
  โsuccessโ : false,
  โmessageโ : ํ๋จ ๋ฉ์์ง ์ฐธ๊ณ 
}
```

- ์๋ฌ ๋ฉ์์ง
    1. ์๋ชป๋ ํ๋ผ๋ฏธํฐ ์๋ ฅ
    2. ์๋ชป๋ ์ด๋ฉ์ผ ํ์
    3. ์ธ์ฆ ์์ฒญ ์ด๋ ฅ์ด ์๋ ์ด๋ฉ์ผ์๋๋ค.
    4. ์ธ์ฆ์ฝ๋๋ฅผ ์๋ ฅํด์ผ ๋๋ ๋จ๊ณ์๋๋ค.
    5. ์กด์ฌํ์ง ์๋ API_KEY.

---

```
[Post] univcert.com/certifiedlist ์์ฒญ
์ธ์ฆ๋ ์ ์  ๋ฆฌ์คํธ ์ถ๋ ฅ

request
{
  โkeyโ : โ๋ถ์ฌ๋ฐ์ API KEYโ
}
```

```
[Post] univcert.com/certifiedlist ์๋ต

response
{
  "data": [
    {
      "email": "insi2000@mail.hongik.ac.kr",
      "univName": "ํ์ต๋ํ๊ต",
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

response (์คํจ)
{
  "status" : 400
  โsuccessโ : false,
  โmessageโ : ํ๋จ ๋ฉ์์ง ์ฐธ๊ณ 
}
```

- ์๋ฌ ๋ฉ์์ง
    1. ์๋ชป๋ ํ๋ผ๋ฏธํฐ ์๋ ฅ
    2. ์๋ชป๋ ์ด๋ฉ์ผ ํ์
    3. ์กด์ฌํ์ง ์๋ API_KEY.

---

```
[POST] univcert.com/check ์์ฒญ
์ธ์ฆ ๊ฐ๋ฅํ ๋ํ๋ช์ธ์ง ์ฒดํน

request
{
  "univName" : "ใใ๋ํ๊ต"
}
```

```
[Post] univcert.com/check ์๋ต

response
{
  "success": true
}

response (์คํจ)
{
  "status" : 400
  โsuccessโ : false,
  โmessageโ : ํ๋จ ๋ฉ์์ง ์ฐธ๊ณ 
}
```

- ์๋ฌ ์ด์ 
    1. ์๋ฒ์ ์กด์ฌํ์ง ์๋ ๋ํ๋ช ( 22๋ ๊ธฐ์ค ์ํ์ ์์ 120๊ฐ ๋ํ)
    2. ๋ํ๋ช ํ์ ์ค๋ฅ (~~๋ํ๊ต)

---

**์ํ์ฝ๋ ๋ฐํ ํ**

| CODE | DESCRIPTION |
| --- | --- |
| 200(Success) | ์ฑ๊ณต. |
| 400(Bad Request) | ์๋ชป๋ ์์ฒญ (์์ฒญ ๊ฐ ํ์, ํ์ ์ค๋ฅ, ์กด์ฌํ์ง ์๋ ์ด๋ฉ์ผ โฆ) |
| 500(Server error) | ์๋ฒ ์ธก ์๋ฌ.  ์ต๋ํ ๋ธ๋ ฅํด๋ณด๊ฒ ์ต๋๋ค.. |
