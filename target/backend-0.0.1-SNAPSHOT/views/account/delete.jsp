<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Delete Account" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Accounts" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">${pageTitle} <small>Please confirm delete this account?</small></h1>

			<form action="<c:url value="/delete-account" />" method="post">
				<input type="hidden" name="id" value="${account.id}" />
				<div class="row">
					<div class="col-md">
						<div class="form-group">
							<label for="username">Username:</label>
							<input type="text" class="form-control" name="username" id="username" placeholder="Account username" value="${account.username}" readonly="readonly">
						</div>
					</div>
					<div class="col-md">
						<div class="form-group">
							<label for="type">Type:</label>
							<input type="text" class="form-control" name="type" id="type" placeholder="Account type" value="${account.type}" readonly="readonly">
						</div>
					</div>
				</div>

				<div>
					<button type="submit" class="btn btn-danger">Delete</button>
					<a href="<c:url value="/list-accounts" />" class="btn btn-warning">Back</a>
				</div>

			</form>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>