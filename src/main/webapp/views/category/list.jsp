<%@ include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="List Categories" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Categories" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">${pageTitle}</h1>

			<div class="navs-area">
				<p><a href="<c:url value="/create-category" />" class="btn btn-info"><i class="fa fa-plus-square" aria-hidden="true"></i> Create new category</a></p>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="category" items="${listCategories}">
						<tr>
							<td>${category.id}</td>
							<td><strong>${category.name}</strong></td>
							<td>
								<a href="<c:url value="/edit-category?id=${category.id}" />" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square" aria-hidden="true"></i></a>
								<a href="<c:url value="/delete-category?id=${category.id}" />" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></a>
							</td>
						</tr>
						<c:forEach var="subCategory" items="${category.subCategories}">
							<tr>
								<td>${subCategory.id}</td>
								<td>- ${subCategory.name}</td>
								<td>
									<a href="<c:url value="/edit-category?id=${subCategory.id}" />" class="btn btn-warning btn-sm"><i class="fa fa-pencil-square" aria-hidden="true"></i></a>
									<a href="<c:url value="/delete-category?id=${subCategory.id}" />" class="btn btn-danger btn-sm"><i class="fa fa-trash" aria-hidden="true"></i></a>
								</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
			<div class="navs-area">
				<p><a href="<c:url value="/create-category" />" class="btn btn-info"><i class="fa fa-plus-square" aria-hidden="true"></i> Create new category</a></p>
			</div>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>