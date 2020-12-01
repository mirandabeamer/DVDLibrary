<%-- 
    Document   : editDvdForm
    Created on : Jun 21, 2020, 3:18:25 PM
    Author     : mirandabeamer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Dvd</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1>
                Edit DVD: "${dvd.title}"
            </h1>
            <hr/>
            <sf:form class="form-horizontal" role="form" modelAttribute="dvd" action="editDvd" method="POST">
                <div class="form-group">
                    <label for="add-title" class="col-sm-2 col-form-label">
                        Title: 
                    </label>
                    <div class="col-sm-6">
                        <sf:input type="text" class="form-control" path="title" placeholder="Title"/>
                        <sf:errors path="title" cssclass="error"></sf:errors>
                    </div>
                </div>  
                <div class="form-group">
                    <label for="add-date" class="col-sm-2 col-form-label">
                        Date: 
                    </label>
                    <div class="col-sm-6">
                        <sf:input type="text" class="form-control" path="date" placeholder="Release Year:"/>
                        <sf:errors path="date" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-director" class="col-sm-2 col-form-label">
                        Director:
                    </label>
                    <div class="col-sm-6">
                        <sf:input type="text" class="form-control" path="director" placeholder="Director"/>
                        <sf:errors path="director" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-rating" class="col-sm-2 col-form-label">
                       Rating: 
                    </label>
                    <div class="col-sm-6">
                    <sf:select class="form-control" path="rating">
                        <sf:option value="G">G</sf:option>
                        <sf:option value="PG">PG</sf:option>
                        <sf:option value="PG13">PG-13</sf:option>
                        <sf:option value="R">R</sf:option>
                    </sf:select>
                        <sf:errors path="rating" cssclass="error"></sf:errors>
                    </div>
                </div>
               <div class="form-group">
                    <label for="add-notes" class="col-sm-2 col-form-label">
                        Director:
                    </label>
                    <div class="col-sm-6">
                        <sf:input type="textarea" class="form-control" path="notes" placeholder="Notes: "/>
                        <sf:errors path="notes" cssclass="error"></sf:errors>
                        <sf:hidden path="dvdId"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-6">
                        <a href="${pageContext.request.contextPath}/" class="btn btn-default">Cancel</a>
                        <input class="btn border" type="submit" value="Edit Dvd"/>
                    </div>
                </div>
            </sf:form>
        </div>
    
<!--    	<div class="container">
		<h1 class="content">Edit DVD: "${dvd.title}"</h1>
		<hr/>
		<form role="form" modelAttribute="dvd" action="editDvd" method="POST" >
			<div class = "form-group row">
				<label for = "dvd-title-to-edit" class="col-sm-2 col-form-label">
					Dvd Title: 
				</label>
				<div class = "col-sm-6">
				<input type = "text" class="form-control" id="dvd-title-to-edit" placeholder= "" required/>
				</div>
			</div>
			<div class = "form-group row">
				<label for = "release-year-to-edit" class="col-sm-2 col-form-label">
					Release Year:
				</label>
				<div class="col-sm-6">
				<input type = "text" id="release-year-to-edit" class="form-control" placeholder= "" required/>
				</div>
			</div>
				<div class = "form-group row">
				<label for = "director-to-edit" class="col-sm-2 col-form-label">
					Director:
				</label>
				<div class="col-sm-6">
				<input type = "textArea" id="director-to-edit" class="form-control" placeholder= "" required/>
				</div>
			</div>
			<div class="form-group row">
				<label for = "rating-to-edit" class = "col-sm-2 col-form-label">Rating: </label>
				<div class= "col-sm-2">
				<select class="browser-default custom-select" id="rating-to-edit">
		  			<option class="g">G</option>
		  			<option class="pg">PG</option>
		  			<option class="pg13">PG-13</option>
		  			<option class="r">R</option>
				</select>
				</div>
			</div>
			<div class="form-group row">
				<label for = "notes-to-edit" class="col-sm-2 col-form-label">
					Notes:  
				</label>
				<div class="col-sm-6">
				<input type = "textArea" class="form-control" id="notes-to-edit" placeholder= "" required/>
				</div>	
			</div>
			<div class = "row">
				<a href="${pageContext.request.contextPath}/" class="btn btn-default">Cancel</a>
				<input class="btn border" type="submit" value="Edit Dvd"/>
			</div>
		</form>
	</div>-->
        
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>    
    </body>
</html>
