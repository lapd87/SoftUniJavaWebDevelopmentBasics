<%@ page import="metubeV2.domain.models.view.TubeDetailsViewModel" %>
<%@ page import="static metubeV2.constants.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Constants" class="metubeV2.constants.Constants"/>
<html>
<head>
    <c:import url="${Constants.head}"/>
</head>
<body>
<% TubeDetailsViewModel tdvm = (TubeDetailsViewModel) request.getAttribute(TUBE_ENTITIES); %>
<div class="container-fluid">
    <header>
        <c:import url="${Constants.navbar}"/>
    </header>
    <main>
        <hr class="my-2">
        <div class="container-fluid">
            <h2 class="text-center"><%=tdvm.getTitle()%>
            </h2>
            <div class="row">
                <div class="col-md-6 my-5">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item"
                                src="<%=YOUTUBE_EMBED_LINK + tdvm.getYouTubeId()%>"
                                allowfullscreen
                                frameborder="0"></iframe>
                    </div>
                </div>
                <div class="col-md-6 my-5">
                    <h1 class="text-center text-info"><%=tdvm.getAuthor()%>
                    </h1>
                    <h3 class="text-center text-info"><%=tdvm.getViews()%> Views</h3>
                    <div class="h5 my-5 text-center"><%=tdvm.getDescription()%>
                    </div>
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
