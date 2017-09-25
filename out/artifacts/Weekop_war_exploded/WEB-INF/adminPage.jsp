<%--
  Created by IntelliJ IDEA.
  User: Paweł
  Date: 2017-08-28
  Time: 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Weekop</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="fragment/navbarAdmin.jspf"/>
<div class="container-fluid">
    <div class="row">
        <main class="col-sm-9 ml-sm-auto col-md-10 pt-3">
            <h1>DashBoard</h1>
            <section class="row text-center placeholders">
                <div class="col-6 col-sm-3 placeholder">
                    <a href="${pageContext.request.contextPath}/userAdmin">
                    <img src="/resources/icon/user-group-icon.png" width="200" height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
                    <h4>Users</h4>
                    <div class="text-muted">Edycja danych</div>
                    </a>
                </div>
                <div class="col-6 col-sm-3 placeholder">
                    <a href="${pageContext.request.contextPath}/discoveryAdmin" >
                    <img src="/resources/icon/74-Surveillance-512.png" width="200" height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
                    <h4>Discovery</h4>
                    <span class="text-muted">Edycja danych</span>
                    </a>
                </div>
                <div class="col-6 col-sm-3 placeholder">
                    <a href="${pageContext.request.contextPath}/voteAdmin">
                    <img src="/resources/icon/307-256.png" width="200" height="200" class="img-fluid rounded-circle" alt="Generic placeholder thumbnail">
                    <h4>Vote</h4>
                    <span class="text-muted">Edycja danych</span>
                    </a>
                </div>
            </section>
        </main>
    </div>
</div>
<jsp:include page="fragment/footer.jspf"/>
<script src="http://code.jquery.com/jquery-1.11.2.min.js" ></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js" ></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</body>
</html>
