<%@ page import="metubeV2.domain.models.view.UserProfileViewModel" %>
<%@ page import="static metubeV2.constants.Constants.*" %>
<%@ page import="metubeV2.domain.models.view.TubesUserProfileViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="Constants" class="metubeV2.constants.Constants"/>
<html>
<head>
    <c:import url="${Constants.head}"/>
</head>
<body>
<% UserProfileViewModel upvm = (UserProfileViewModel) request.getAttribute(USER_ENTITIES); %>
<% List<TubesUserProfileViewModel> tubes = upvm.getTubes(); %>
<div class="container-fluid">
    <header>
        <c:import url="${Constants.navbar}"/>
    </header>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%= upvm.getUsername()%>
            </h4>
            <h4 class="text-info text-center">(<%= upvm.getEmail()%>)</h4>
        </div>
        <hr>
        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < tubes.size(); i++) { %>
                    <tr>
                        <td><%= i + 1%>
                        </td>
                        <td><%= tubes.get(i).getTitle()%>
                        </td>
                        <td><%= tubes.get(i).getAuthor()%>
                        </td>
                        <td>
                            <a href="<%=TUBE_DETAILS_URL + URL_SPLITTER + tubes.get(i).getId()%>">Details</a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <hr class=" my-3"/>
    </main>
    <footer>
        <c:import url="${Constants.footer}"/>
    </footer>
</div>
</body>
</html>
