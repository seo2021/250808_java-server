<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>멋사 Servlet 테스트</title>
    <!-- OG Tag -->
    <meta property="og:title" content="멋사 Servlet 테스트">
    <meta property="og:description" content="Gemini 2.0 flash로 구현한 챗봇">
    <!-- Web Font -->
    <style>
        /* PFStardustS 라는 이름으로 외부 링크의 폰트를 받아오기 */
        @font-face {
            font-family: 'PFStardustS';
            src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2506-1@1.0/PFStardustS.woff2') format('woff2');
            font-weight: 400;
            font-style: normal;
        }
        /* body 안에 있는 폰트를 아래 지정한 폰트로 바꾸기 */
        body, button, input {
            font-family: 'PFStardustS';
            font-size: large;
        }
        button {
            background-color: #6bb4f3;
            border: none;
            padding: 10px;
            border-radius: 4px;
        }
        input {
            background-color: #eee;
            border: none;
            padding: 10px;
            border-radius: 4px;
            width: 300px;
        }
        .placeholder {
            color: #999;
            font-style: italic;
        }
    </style>
</head>
<body>
<h2>AI 채팅을 합시다</h2>
<h3></h3>
<p>
    질문: <%= request.getAttribute("question") != null ? request.getAttribute("question") : "<span class='placeholder'>아직 질문이 없습니다.</span>" %>
</p>
<p>
    답변: <%= request.getAttribute("answer") != null ? request.getAttribute("answer") : "<span class='placeholder'>아직 답변이 없습니다.</span>" %>
</p>
<form method="POST" action="">
    <input name="question" placeholder="질문을 입력하세요.">
    <button>질문하기</button>
</form>
</body>
</html>