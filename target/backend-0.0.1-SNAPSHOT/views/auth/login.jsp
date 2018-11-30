<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="/views/common/error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<c:set var="pageTitle" value="Login Page" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/head.jsp"%>
	<link href="<c:url value="/assets/assignment/css/login.css" />" rel="stylesheet">
</head>
<body>
<div class="login-container">
</div>
<div class="login-content">
	<h1>Login to Your Account</h1><br>

	<c:if test="${hasError}">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Opps!</strong> Username or password is incorrect.
		</div>
	</c:if>

	<c:if test="${hasInvalid}">
		<div class="alert alert-warning alert-dismissible fade show" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Opps!</strong> Please correct invalid input.
		</div>
	</c:if>

	<form action="<c:url value="/login" />" method="post">
		<div class="form-group <c:if test="${not empty errUsername}">has-danger</c:if>">
			<label for="username">Username</label>
			<input type="text" class="form-control" id="username" name="username" value="">
			<c:if test="${not empty errUsername}">
				<div class="form-control-feedback">${errUsername}</div>
			</c:if>
		</div>
		<div class="form-group <c:if test="${not empty errPassword}">has-danger</c:if>">
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password" value="">
			<c:if test="${not empty errPassword}">
				<div class="form-control-feedback">${errPassword}</div>
			</c:if>
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary pull-right">Login <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></button>
		</div>
	</form>
</div>
</body>
</html>