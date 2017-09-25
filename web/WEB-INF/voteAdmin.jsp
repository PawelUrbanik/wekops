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
    <title>Vote admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragment/navbarAdmin.jspf"></jsp:include>

<div class="container">
    <br><br>
    <h2>Vote Table</h2>
    <p>Vote List:</p>
    <table class="table">
        <thead>
        <tr>
            <th>Vote Id</th>
            <th>User Id</th>
            <th>Discovery Id</th>
            <th>Type</th>
            <th>Date</th>
        </tr>
        </thead>
        <c:if test="${not empty requestScope.votes}">
            <c:forEach var="vote" items="${requestScope.votes}">
                <tbody>
                <c:if test="${vote.voteType eq 'VOTE_UP'}">
                <tr class="success">
                    </c:if>
                    <c:if test="${vote.voteType eq 'VOTE_DOWN'}">
                <tr class="danger">
                    </c:if>
                    <td><c:out value="${vote.id}"/></td>
                    <td><c:out value="${vote.discoveryId}"/></td>
                    <td><c:out value="${vote.userId}"/></td>
                    <td><c:out value="${vote.date}"/></td>
                    <td><c:out value="${vote.voteType}"/></td>
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
