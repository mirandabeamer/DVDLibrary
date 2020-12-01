<%-- 
    Document   : dvdDetails
    Created on : Jun 21, 2020, 2:53:44 PM
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
        <title>Dvd Details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <h1>
                <c:out value="${dvd.title}"/>
            </h1>
            <hr/>
            <table class = "table table-borderless" style="width:30%">
		<tbody>
			<tr>
				<th scope="row">Release Year: </th>
                                <td>
                                    <c:out value="${dvd.date}"/>
                                </td>
			</tr>
			<tr>
				<th scope="row">Director: </th>
                                <td>
                                    <c:out value="${dvd.director}"/>
                                </td>
			</tr>
			<tr>
				<th scope="row">Rating: </th>
                                <td>
                                    <c:out value="${dvd.rating}"/>
                                </td>
			</tr>
			<tr>
				<th scope="row">Notes: </th>
				<td>
                                    <c:out value="${dvd.notes}"/>
                                </td>
			</tr>
		</tbody>
            </table>
		<a href="${pageContext.request.contextPath}/" class="btn btn-default">Back</a>
	</div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
