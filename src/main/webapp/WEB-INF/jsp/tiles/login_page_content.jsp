<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div class="header">
    <h1>
        <tiles:getAsString name="header_message"/>
    </h1>
</div>
<div class="content">
    <div class="login_block">
        <form action="login" method="post">
            <label for="login">Имя пользователя</label>
            <input type="text" id="login" name="login" />
            <input class="link" type="submit">
        </form>
        <div class="error_block">
            ${status}
        </div>
    </div>
</div>