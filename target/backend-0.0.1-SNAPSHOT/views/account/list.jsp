<%@ include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="List Accounts" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Accounts" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">${pageTitle}</h1>

			<div class="navs-area">
				<p><a href="<c:url value="/create-account" />" class="btn btn-info"><i class="fa fa-plus-square" aria-hidden="true"></i> Create new account</a></p>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>Type</th>
						<th>Email</th>
						<th>FullName</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="account" items="${listAccounts}">
						<tr>
							<td>${account.id}</td>
							<td><strong>${account.username}</strong></td>
							<td><strong>${account.password}</strong></td>
							<td><strong>${account.type}</strong></td>
							<td><strong>${account.email}</strong></td>
							<td><strong>${account.fullName}</strong></td>
							<td><strong>${account.address}</strong></td>
							<td>
								<a href="<c:url value="/edit-account?id=${account.id}" />" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square" aria-hidden="true"></i></a>
								<a href="<c:url value="/delete-account?id=${account.id}" />" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="navs-area">
				<p><a href="<c:url value="/create-account" />" class="btn btn-info"><i class="fa fa-plus-square" aria-hidden="true"></i> Create new account</a></p>
			</div>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>