<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="header">
    <h1><tiles:getAsString name="header_message"/></h1>
</div>
<div class="content">
    <div class="question_number_field">
        <h2 class="question_number">Вопрос № 1</h2>
    </div>
    <div class="question_field">
        <p>
            <b>Как по вашему мнению расшифровывается аббревиатура &quot;ОС&quot;?</b>
        </p>
    </div>
    <div class="control_field">
        <form id="answer_selector" action="handler.php">
            <div class="radio_block">
                <input type="radio" name="answer" value="a1">Офицерский состав<Br>
                <input type="radio" name="answer" value="a2">Операционная система<Br>
                <input type="radio" name="answer" value="a3">Обсидиановое святилище
            </div>
            <div class="submit_block">
                <input class="link" type="submit">
            </div>
        </form>
    </div>
</div>
<div class="footer">
    <form action="">
        <input class="link" type="button" name="back" value="Назад">
        <input class="link" type="button" name="back" value="Вперед" onclick="location.href='./results_page.html'">
    </form>
</div>
