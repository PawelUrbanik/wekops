<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Paweł
  Date: 2017-08-30
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragment/navbarAdmin.jspf"/>

    <div class="container">
        <br><br>
        <h2>Users Table</h2>
        <p>Users List:</p>
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Email</th>
                <th>Is active</th>
            </tr>
            </thead>
            <c:if test="${not empty requestScope.users}">
            <c:forEach var="user" items="${requestScope.users}">
            <tbody>
            <c:if test="${user.active eq true}">
                <tr class="success">
            </c:if>
                <c:if test="${user.active ne true}">
                    <tr class="danger">
                </c:if>

                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>
                    <c:if test="${user.active eq true}">
                        <td>Aktywny</td>
                    </c:if>
                    <c:if test="${user.active ne true}">
                        <td>Nieaktywny</td>
                    </c:if>
                </td>
            </tr>
            </tbody>
            </c:forEach>
            </c:if>
        </table>
    </div>


<jsp:include page="fragment/footer.jspf"/>
<script src="http://code.jquery.com/jquery-1.11.2.min.js" ></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
