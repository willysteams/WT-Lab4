<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="mainHeader.label.makeOrder" var="makeOrder"/>
<fmt:message bundle="${naming}" key="mainHeader.label.welcome" var="welcome"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomNumber"/>
<fmt:message bundle="${naming}" key="table.label.occupy" var="occupy"/>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${makeOrder}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/makeOrderStyle.css">
</head>

<body>
<header>
    <jsp:include page="../fragments/header/mainHeader.jsp"/>
    <h1>${welcome}</h1>
</header>
<div class="makeOrder">
    <form action="${pageContext.servletContext.contextPath}/controller?command=makeOrder" method="post">
        <table>
            <tr>
                <th>${roomNumber}</th>
                <th></th>
            </tr>
            <jsp:useBean id="roomList" scope="request" type="java.util.List"/>
            <c:forEach items="${roomList}" var="room">
                <tr>
                    <td>
                            ${room.roomNumber}
                    </td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/controller?command=makeOrder"
                              method="post">
                            <input type="hidden" id="roomId" name="roomId" value="${room.id}">
                            <input type="submit" value="${occupy}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>