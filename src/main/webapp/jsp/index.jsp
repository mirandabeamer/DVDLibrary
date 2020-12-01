<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">    

    </head>
    <body>
	<div class = "container" id = "main-page">  
            <h1>DVD Library</h1>
            <hr/>
            <br/>
            
            <div class="row col-md-12">
            <div class="col-md-2 btn border">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/displayCreateDvdPage">Create DVD</a>

            </div>
            <div class="col-md-10 form-row">
		<form class ="form-inline" role="form" id="search-form">
                    <div class="form-group col-md-4">
                        <label label for="search-term">Search Term: </label>
			<input class="form-control" type = "text" id="search-term" name="search-term" placeholder= "Search Term" required/>
                    </div>
                    <div class="form-group col-md-5">
                        <label for="search-category">Search Category: </label>
                            <select class="browser-default custom-select form-control" id="search-category" name ="search-category" required>
                                    <option value = "" disable selected >Search Category</option>
                                    <option value = "title" name="title" id="search-title">Title</option>
                                    <option value = "date" name="date" id="search-date">Release Date</option>
                                    <option value = "director" name="director" id="search-director">Director</option>
                                    <option value = "rating" name="rating" id="search-rating">Rating</option>
                            </select>
                    </div>
			<input type="button" class ="form-group btn border btn-default" id="search-button" value="Search"/>
		</form>
              </div>
            </div>
	<hr/>
	<div><h4 id="errorMessages"></h4></div>

            <table class="table table-striped" id="dvd-table">
                <tr>
                  <th scope="col">Title</th>
                  <th scope="col">Release Date</th>
                  <th scope="col">Director</th>
                  <th scope="col">Rating</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
                <tbody id="contentRows">
                <c:forEach var="currentDvd" items="${dvdList}">
                    <tr>
                        <td>
                            <a href="displayDvdDetails?dvdId=${currentDvd.dvdId}">
                                <c:out value="${currentDvd.title}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${currentDvd.date}"/>
                        </td>
                        <td>
                            <c:out value="${currentDvd.director}"/>
                        </td>
                        <td>
                            <c:out value="${currentDvd.rating}"/>
                        </td>
                        <td>
                            <a href = "displayEditDvdForm?dvdId=${currentDvd.dvdId}">EDIT </a>
                        </td>
                        <td>|</td>
                        <td>
                            <a href="deleteDvd?dvdId=${currentDvd.dvdId}" onclick="deleteDvd(${currentDvd.dvdId})">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        <a href="${pageContext.request.contextPath}/displayLibrary" style="display:none" id="undo-search">Undo Search</a>
	</div>



        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdLibrary.js"></script>


    </body>
</html>

