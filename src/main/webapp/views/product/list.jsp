<%@ include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="List Products" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Products" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">${pageTitle}</h1>

			<div class="navs-area">
				<p><a href="<c:url value="/create-product" />" class="btn btn-info"><i class="fa fa-plus-square" aria-hidden="true"></i> Create new product</a></p>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Code</th>
						<th>Name</th>
						<th>Image</th>
						<th>Category</th>
						<th>Unit Price</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${listProducts}">
						<tr>
							<td>${product.id}</td>
							<td><strong>${product.code}</strong></td>
							<td><strong>${product.name}</strong></td>
							<td><strong><img src="${product.image}" width="100px"/></strong></td>
							<td><strong>${product.category.name}</strong></td>
							<td><strong>${product.unitPrice}</strong></td>
							<td>
								<a href="<c:url value="/edit-product?id=${product.id}" />" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square" aria-hidden="true"></i></a>
								<a href="<c:url value="/delete-product?id=${product.id}" />" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="navs-area">
				<p><a href="<c:url value="/create-product" />" class="btn btn-info"><i class="fa fa-plus-square" aria-hidden="true"></i> Create new product</a></p>
			</div>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>