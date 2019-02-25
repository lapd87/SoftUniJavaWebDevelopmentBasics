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
        <hr class="my-2"/>
        <div class="text-center mb-3">
            <h1>Login</h1>
        </div>
        <hr class="my-2">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="form-holder text-center">
                    <form class="form-inline" action="<%=LOGIN_URL%>" method="<%=METHOD_POST%>">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="username">Username</label>
                                <div class="controls">
                                    <input type="text" id="username" name="<%=PARAMETER_USER%>"
                                            placeholder="" class="input-xlarge" required>
                                </div>
                            </div>

                            <br/>

                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="password">Password</label>
                                <div class="controls">
                                    <input type="password" id="password" name="<%=PARAMETER_PASS%>"
                                            placeholder="" class="input-xlarge" required>
                                </div>
                            </div>

                            <br/>

                            <div class="control-group">
                                <div class="controls">
                                    <button class="btn btn-info">Login</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <footer>
        <c:import url="${Constants.footer}"/>
    </footer>
</div>
</body>
</html>
