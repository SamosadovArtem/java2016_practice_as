<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inquirer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="wrapper">
    <div class="header">
        <h1>
            Опрос завершен!
        </h1>
    </div>
    <div class="result_block">
        <h2>Результат: 10 баллов</h2>
    </div>
    <div class="button_links_block">
        <form action="">
            <input class="link" class="result_page_button" type="button" name="" value="Еще раз" onclick="location.href='./inquirer.html'">
            <input class="link" class="result_page_button" type="button" name="" value="Выйти" onclick="location.href='./login_page.html'">
        </form>
    </div>
</div>
</body>
</html>