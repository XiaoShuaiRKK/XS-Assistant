<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="board" style="line-height: 50px; min-height: 500px; padding: 30px 0;
     background-color: #000000;text-align: center;color: #ffffff;">
    <p style="font-size: 40px; font-weight: bold;">XS-Assistant</p>
    <h1 style="color: #ffffff">
        感谢你的注册
    </h1>
    <h1>Thank you for registering</h1>
    <p class="username" style="font-size: 24px">用户</p>
    <p>${username}</p>
    <p>注册时间(Registration time):${date}</p>
    <p>此消息是系统自动发送，无需回复</p>
</div>
</body>
</html>