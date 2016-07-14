<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <h1><tiles:getAsString name="header_message"/></h1>
</div>
<div class="content">
    <div class="question_number_field">
        <h2 class="question_number">Вопрос № ${questionNubmer}</h2>
    </div>
    <div class="question_field">
        <p>
            <b>${questionTitle}</b>
        </p>
    </div>
    <div class="control_field">
        <form id="answer_selector" action="test" method="post">
            <input type="hidden" name="questionParameterNumber" value="${questionParameterNumber}">
            <input type="hidden" name="currentUserAnswerId" value="${currentUserAnswerId}">
            <div class="radio_block">
                <c:forEach items="${answers}" var="answer">
                    <c:if test="${currentUserAnswerId == answer.id}">
                        <input type="radio" checked name="userAnswerInput" value="${answer.id}">${answer.title}<Br>
                    </c:if>
                    <c:if test="${currentUserAnswerId != answer.id}">
                        <input type="radio" name="userAnswerInput" value="${answer.id}">${answer.title}<Br>
                    </c:if>
                </c:forEach>
            </div>
            <div class="submit_block">
                <c:if test="${currentUserAnswerId != 0}">
                    <input disabled class="link" type="submit" value="Принять">
                </c:if>
                <c:if test="${currentUserAnswerId == 0}">
                    <input class="link" type="submit" value="Принять">
                </c:if>
            </div>
        </form>
    </div>
</div>
<div class="footer">
    <form action="">
        <c:if test="${param.question > 1}">
            <c:set var="disableBack" value=""></c:set>
        </c:if>
        <c:if test="${param.question == 1}">
            <c:set var="disableBack" value="disabled"></c:set>
        </c:if>
        <input ${disableBack} class="link" type="button" name="back" value="Назад" onclick="location.href='../inquirer/test?question=${previousQuestionNumber}'">
        <c:if test="${param.question == questionsAmount}">
            <c:set var="buttonText" value="Конец"/>
            <c:set var="destination" value="../inquirer/results"/>
        </c:if>
        <c:if test="${param.question < questionsAmount}">
            <c:set var="buttonText" value="Вперед"/>
            <c:set var="destination" value="../inquirer/test?question=${nextQuestionNumber}"/>
        </c:if>
        <input class="link" type="button" name="forward" value="${buttonText}" onclick="location.href='${destination}'">
    </form>
</div>
