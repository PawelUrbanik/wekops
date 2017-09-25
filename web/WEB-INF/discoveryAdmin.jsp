<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Discovery admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragment/navbarAdmin.jspf"/>
<div class="container">
    <br><br>
    <h2>Discovery Table</h2>
    <p>Discovery List:</p>
    <table class="table">
        <thead>
        <tr>
            <th>DiscoveryId Id</th>
            <th>Name</th>
            <th>URL</th>
            <th>Date</th>
            <th>Added by</th>
        </tr>
        </thead>
        <c:if test="${not empty requestScope.discoveriesId}">
            <c:forEach var="discoveryId" items="${requestScope.discoveriesId}">
                <tbody>
                <tr>
                    <td><c:out value="${discoveryId.id}"/></td>
                    <td><c:out value="${discoveryId.name}"/></td>
                    <td><c:out value="${discoveryId.url}"/></td>
                    <td><fmt:formatDate value="${discoveryId.timestamp}" pattern="dd/MM/YYYY"/></td>
                    <td><c:out value="${discoveryId.user.username}"/></td>
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
