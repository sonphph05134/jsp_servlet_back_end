<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Create New Category" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Categories" />
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

			<form action="<c:url value="/create-category" />" method="post">

				<div class="row">
					<div class="col-md">

						<div class="form-group <c:if test="${not empty errName}">has-danger</c:if>">
							<label for="name">Name:</label>
							<input type="text" class="form-control" name="name" id="name" placeholder="Category name">
							<c:if test="${not empty errName}">
								<div class="form-control-feedback">${errName}</div>
							</c:if>
						</div>
					</div>

					<div class="col-md">
						<div class="form-group <c:if test="${not empty errFatherId}">has-danger</c:if>">
							<label for="fatherId">Father Category:</label>
							<select class="form-control" name="fatherId" id="fatherId">
								<option value="">--------------------------------</option>
								<c:forEach var="father" items="${listCategories}">
									<option value="${father.id}">${father.name}</option>
								</c:forEach>
							</select>
							<c:if test="${not empty errFatherId}">
								<div class="form-control-feedback">${errFatherId}</div>
							</c:if>
						</div>
					</div>
				</div>

				<div>
					<button type="submit" class="btn btn-primary">Submit</button>
					<button type="reset" class="btn btn-warning">Reset</button>
					<a href="<c:url value="/list-categories" />" class="btn btn-danger">Back</a>
				</div>

			</form>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>