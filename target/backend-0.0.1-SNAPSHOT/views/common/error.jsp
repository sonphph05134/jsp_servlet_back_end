<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/views/common/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page isErrorPage="true"%>
<c:set var="pageTitle" value="System Error Page" scope="page" />
<!DOCTYPE html>
<html>
<head>
	<%@include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="error" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">OOOPS! Something wrong.</h1>
			<p><%= exception.toString() %></p>
		</div>

		<jsp:include page="/views/common/footer.jsp">
			<jsp:param name="current" value="error" />
		</jsp:include>
	</div>
</body>
</html>