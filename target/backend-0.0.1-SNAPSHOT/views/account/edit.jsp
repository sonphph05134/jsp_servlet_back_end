<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Edit Account" scope="page"></c:set>
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
			<h1 class="page-title">${pageTitle}</h1>

			<c:if test="${hasError}">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Opps!</strong> Something wrong.
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

			<form action="<c:url value="/edit-account" />" method="post">
				<input type="hidden" name="id" value="${account.id}" /> 
				<input type="hidden" name="username" value="${account.username}" /> 
				<input type="hidden" name="password" value="${account.password}" /> 
				<input type="hidden" name="type" value="${account.type}" />
				<div class="row">
					<div class="col-md">

						<div
							class="form-group <c:if test="${not empty errUsername}">has-danger</c:if>">
							<label for="name">Username:</label> <input type="text"
								class="form-control" name="username" id="username"
								placeholder="Account username" value="${account.username}">
							<c:if test="${not empty errUsername}">
								<div class="form-control-feedback">${errUsername}</div>
							</c:if>
						</div>


						<div
							class="form-group <c:if test="${not empty errPassword}">has-danger</c:if>">
							<label for="name">Password:</label> <input type="text"
								class="form-control" name="password" id="password"
								placeholder="Account password" value="${account.password}">
							<c:if test="${not empty Password}">
								<div class="form-control-feedback">${errPassword}</div>
							</c:if>
						</div>

						<div
							class="form-group <c:if test="${not empty errType}">has-danger</c:if>">
							<label for="name">Type:</label> <input type="text"
								class="form-control" name="type" id="type"
								placeholder="Account type" value="${account.type}">
							<c:if test="${not empty Type}">
								<div class="form-control-feedback">${errType}</div>
							</c:if>
						</div>
					</div>
					<div class="col-md">

						<div class="col-md">
							<div
								class="form-group <c:if test="${not empty errFullName}">has-danger</c:if>">
								<label for="name">FullName:</label> <input type="text"
									class="form-control" name="fullName" id="fullName"
									placeholder="Account fullName" value="${account.fullName}">
								<c:if test="${not empty FullName}">
									<div class="form-control-feedback">${errFullName}</div>
								</c:if>
							</div>
						</div>
						<div class="col-md">
							<div
								class="form-group <c:if test="${not empty errEmail}">has-danger</c:if>">
								<label for="name">Email:</label> <input type="text"
									class="form-control" name="email" id="email"
									placeholder="Account email" value="${account.email}">
								<c:if test="${not empty Email}">
									<div class="form-control-feedback">${errEmail}</div>
								</c:if>
							</div>
						</div>
						<div class="col-md">
							<div
								class="form-group <c:if test="${not empty errAddress}">has-danger</c:if>">
								<label for="name">Address:</label> <input type="text"
									class="form-control" name="address" id="address"
									placeholder="Account address" value="${account.address}">
								<c:if test="${not empty Address}">
									<div class="form-control-feedback">${errAddress}</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-primary">Submit</button>
					<button type="reset" class="btn btn-warning" value="Reset">Reset</button>
					<a href="<c:url value="/list-accounts" />" class="btn btn-danger">Back</a>
				</div>

			</form>
		</div>

		<jsp:include page="/views/common/footer.jsp">
			<jsp:param name="current" value="Acounts" />
		</jsp:include>
	</div>
</body>
</html>