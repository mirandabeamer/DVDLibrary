<%-- 
    Document   : add
    Created on : Jun 21, 2020, 2:03:26 PM
    Author     : mirandabeamer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Dvd</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">     
    </head>
    <body>
        	<div class="container" id="create-page">
		<h1>Create Dvd</h1>
		<hr/>
		<form role="form" id="add-form" method="POST" action="createDvd">
			<div class = "form-group row">
				<label for = "dvd-title" class="col-sm-2 col-form-label">
					Dvd Title: 
				</label>
				<div class = "col-sm-6">
				<input type = "text" class="form-control" name="title" placeholder= "Dvd Title" required/>
				</div>
			</div>
			<div class = "form-group row">
				<label for = "release-year" class="col-sm-2 col-form-label">
					Release Year:
				</label>
				<div class="col-sm-6">
				<input type = "text" name="date" class="form-control" placeholder= "Release Year" required/>
				</div>
			</div>
				<div class = "form-group row">
				<label for = "director" class="col-sm-2 col-form-label">
					Director:
				</label>
				<div class="col-sm-6">
				<input type = "text" name="director" class="form-control" placeholder= "Director" required/>
				</div>
			</div>
			<div class="form-group row">
				<label for = "rating" class = "col-sm-2 col-form-label">Rating: </label>
				<div class= "col-sm-2">
				<select class="browser-default custom-select" name="rating">
		  			<option class="g">G</option>
		  			<option class="pg">PG</option>
		  			<option class="pg13">PG-13</option>
		  			<option class="r">R</option>
				</select>
				</div>
			</div>
			<div class="form-group row">
				<label for = "notes" class="col-sm-2 col-form-label" name="notes">
					Notes:  
				</label>
				<div class="col-sm-6">
				<input type = "textarea" class="form-control" name="notes" placeholder= "Notes" required/>
				</div>	
			</div>
			<div class = "row">
                            <a href="${pageContext.request.contextPath}/" class="btn btn-default">Cancel   </a>
                            <input type ="submit" class="btn btn-default" value="Create Dvd"/>
			</div>
		</form>
	</div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
