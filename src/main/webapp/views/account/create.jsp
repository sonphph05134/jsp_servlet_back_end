<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Create New Account" scope="page"></c:set>
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
					<strong>Opps!</strong> ${errMessage}
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

			<form action="<c:url value="/create-account" />" method="post" >

				<div class="row">
					<div class="col-md">
						<div class="form-group <c:if test="${not empty errUsername}">has-danger</c:if>">
							<label for="username">Username:</label>
							<input type="text" class="form-control" name="username" id="username" placeholder="Username">
							<c:if test="${not empty errUsername}">
								<div class="form-control-feedback">${errUsername}</div>
							</c:if>
						</div>

						<div class="form-group <c:if test="${not empty errPassword}">has-danger</c:if>">
							<label for="password">Password:</label>
							<input type="password" class="form-control" name="password" id="password" placeholder="Password">
							<c:if test="${not empty errPassword}">
								<div class="form-control-feedback">${errPassword}</div>
							</c:if>
						</div>

						<div class="form-group <c:if test="${not empty errType}">has-danger</c:if>">
							<label for="type">Type:</label>
							<input type="text" class="form-control" name="type" id="type" placeholder="Type">
							<c:if test="${not empty errType}">
								<div class="form-control-feedback">${errType}</div>
							</c:if>
						</div>

					</div>

					<div class="col-md">
						<div class="form-group <c:if test="${not empty errFullName}">has-danger</c:if>">
							<label>FullName:</label>
							<input type="text" class="form-control" name="fullName" id="fullName" placeholder="FullName">
							<c:if test="${not empty errFullName}">
								<div class="form-control-feedback">${errFullName}</div>
							</c:if>
						</div>
						
						
						<div class="form-group <c:if test="${not empty errEmail}">has-danger</c:if>">
							<label>Email:</label>
							<input type="text" class="form-control" name="email" id="email" placeholder="Email">
							<c:if test="${not empty errEmail}">
								<div class="form-control-feedback">${errEmail}</div>
							</c:if>
						</div>
						
						<div class="form-group <c:if test="${not empty errAddress}">has-danger</c:if>">
							<label>Address:</label>
							<input type="text" class="form-control" name="address" id="address" placeholder="Address">
							<c:if test="${not empty errAddress}">
								<div class="form-control-feedback">${errAddress}</div>
							</c:if>
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

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>