<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Delete Product" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Products" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">${pageTitle} <small>Please confirm delete this product?</small></h1>

			<form action="<c:url value="/delete-product" />" method="post">
				<input type="hidden" name="id" value="${product.id}" />
				<div class="row">
					<div class="col-md">
						<div class="form-group">
							<label for="name">Code:</label>
							<input type="text" class="form-control" name="code" id="code" placeholder="Product code" value="${product.code}" readonly="readonly">
						</div>
					</div>
					<div class="col-md">
						<div class="form-group">
							<label for="name">Name:</label>
							<input type="text" class="form-control" name="name" id="name" placeholder="Product code" value="${product.name}" readonly="readonly">
						</div>
					</div>
				</div>

				<div>
					<button type="submit" class="btn btn-danger">Delete</button>
					<a href="<c:url value="/list-products" />" class="btn btn-warning">Back</a>
				</div>

			</form>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>