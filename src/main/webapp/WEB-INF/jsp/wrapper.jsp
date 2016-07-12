<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <tiles:insertAttribute name="head" />
    </head>
    <body>
        <div class="wrapper">
            <tiles:insertAttribute name="test_content" />
        </div>
        <div id="page_footer">
            <tiles:insertAttribute name="page_footer" />
        </div>
    </body>
</html>