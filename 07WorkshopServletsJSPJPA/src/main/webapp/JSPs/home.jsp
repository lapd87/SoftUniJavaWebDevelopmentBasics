<%@ page import="static metubeV2.constants.Constants.*" %>
<%@ page import="metubeV2.domain.models.view.TubesUserHomeViewModel" %>
<%@ page import="metubeV2.domain.models.view.UserHomeViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Constants" class="metubeV2.constants.Constants"/>
<html>
<head>
    <c:import url="${Constants.head}"/>
</head>
<body>
<% UserHomeViewModel uhvm = (UserHomeViewModel) request.getAttribute(USER_ENTITIES); %>
<% List<TubesUserHomeViewModel> tubes = uhvm.getTubes(); %>
<div class="container-fluid">
    <header>
        <c:import url="${Constants.navbar}"/>
    </header>
    <main>
        <hr class="my-2"/>

        <div class="jumbotron">
            <h4 class="h1 display-3">Welcome, <%=uhvm.getUsername()%>
            </h4>
        </div>

        <hr class="my-4">

        <div class="container">
            <div class="row m-2 justify-content-center">
                <% for (int i = 0; i < tubes.size(); i++) {%>
                <% if (i % 3 == 0 && i != 0) {%>
            </div>
            <div class="row m-2 justify-content-center">
                <%}%>

                <div class="col col-md-4 justify-content-center">

                    <div class="row m-2 justify-content-center">
                        <img src="<%=YOUTUBE_THUMB_LINK_START + tubes.get(i).getYouTubeId() + YOUTUBE_THUMB_LINK_END%>"
                             alt="tube thumbnail">
                    </div>

                    <div class="row justify-content-center">
                        <h5 class="text-center"><%=tubes.get(i).getTitle()%>
                        </h5>
                    </div>

                    <div class="row justify-content-center">
                        <p class="text-center font-italic"><%=tubes.get(i).getAuthor()%>
                        </p>
                    </div>

                </div>
                <%}%>
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
