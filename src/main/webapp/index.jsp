<%@ include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Home Page" />
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/views/common/head.jsp"%>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Home" />
		</jsp:include>

		<div class="content">
			<h1 class="page-title">${pageTitle}</h1>
		</div>

		<jsp:include page="/views/common/footer.jsp"></jsp:include>
	</div>
</body>
</html>