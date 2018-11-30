<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Delete Category" scope="page"></c:set>
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
			<h1 class="page-title">${pageTitle} <small>Please confirm delete this category?</small></h1>

			<form action="<c:url value="/delete-category" />" method="post">
				<input type="hidden" name="id" value="${category.id}" />
				<input type="hidden" name="sortNo" value="${category.sortNo}" />
				<div class="row">
					<div class="col-md">
						<div class="form-group">
							<label for="name">Name:</label>
							<input type="text" class="form-control" name="name" id="name" placeholder="Category name" value="${category.name}" readonly="readonly">
						</div>
					</div>
					<div class="col-md">
						<div class="form-group">
							<label for="fatherId">Father Category:</label>
							<input type="text" class="form-control" name="fatherId" id="fatherId" placeholder="Fahter ID" value="${category.category.name}" readonly="readonly">
						</div>
					</div>
				</div>

				<div>
					<button type="submit" class="btn btn-danger">Delete</button>
					<a href="<c:url value="/list-categories" />" class="btn btn-warning">Back</a>
				</div>

			</form>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>