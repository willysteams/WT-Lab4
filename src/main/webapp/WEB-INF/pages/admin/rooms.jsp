<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="table.label.id" var="id"/>
<fmt:message bundle="${naming}" key="table.label.rooms" var="rooms"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomNumber"/>
<fmt:message bundle="${naming}" key="table.label.occupied" var="occupied"/>
<fmt:message bundle="${naming}" key="button.label.addRoom" var="addRoom"/>
<fmt:message bundle="${naming}" key="button.label.deoccupy" var="deoccupy"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/dataStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/tableStyle.css">
    <title>${rooms}</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/header/mainHeader.jsp"/>
<div class="container">
    <div class="card">
        <table>
            <tr>
                <th>${roomNumber}</th>
                <th>${occupied}</th>
                <th></th>
            </tr>
            <jsp:useBean id="roomList" scope="request" type="java.util.List"/>
            <c:forEach items="${roomList}" var="room">
                <tr>
                    <td>
                            ${room.roomNumber}
                    </td>
                    <td>
                            ${room.occupied}
                    </td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/controller?command=deoccupyRoom"
                              method="post">
                            <input type="hidden" id="roomId" name="roomId" value="${room.id}">
                            <input type="submit" value="${deoccupy}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="addPanel">
        <button class="addButton"
                onclick="document.getElementById('addRoom').style.display='block'">${addRoom}
        </button>
    </div>

    <jsp:include page="/WEB-INF/fragments/room/addRoom.jsp"/>
</body>
</html>
