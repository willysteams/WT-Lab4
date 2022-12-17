<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomNumber"/>
<fmt:message bundle="${naming}" key="button.label.add" var="add"/>
<fmt:message bundle="${naming}" key="button.label.cancel" var="cancel"/>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
</head>
<body>
<div id="addRoom" class="modal">

    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=addRoom" method="post">
            <label for="roomNumber"><b>${roomNumber}</b></label>
            <input type="text" id="roomNumber" name="roomNumber"
                   pattern="^([0-9]{3})$" required>
            <div>
                <input class="prcButton" type="submit" value="${add}"/>
            </div>
        </form>
        <div>
            <button class="cancelButton" onclick="document.getElementById('addRoom').style.display='none'">${cancel}
            </button>
        </div>
    </div>
</div>

</body>
</html>