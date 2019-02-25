<%@ page import="static metubeV2.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Constants" class="metubeV2.constants.Constants"/>
<html>
<head>
    <c:import url="${Constants.head}"/>
</head>
<body>
<div class="container-fluid">
    <header>
        <c:import url="${Constants.navbar}"/>
    </header>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome to MeTube&trade;!</p>
            <p class="h3">The simplest, easiest to use, most comfortable Multimedia Application.</p>
            <hr class="my-3">
            <p><a href="<%=LOGIN_URL%>">Login</a> if you have an account or <a href="<%=REGISTER_URL%>">Register</a> now and
                start tubing.</p>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="${Constants.footer}"/>
    </footer>
</div>
</body>
</html>
