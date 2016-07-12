<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="header">
    <h1><tiles:getAsString name="header_message"/></h1>
</div>
<div class="result_block">
    <h2>Результат: ${result} баллов</h2>
</div>
<div class="button_links_block">
    <form action="">
        <input class="link" class="result_page_button" type="button" name="" value="Еще раз" onclick="location.href='../inquirer/test'">
        <input class="link" class="result_page_button" type="button" name="" value="Выйти" onclick="location.href='../inquirer/login'">
    </form>
</div>
