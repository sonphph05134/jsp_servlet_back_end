<%@ include file="/views/common/common.jsp"%>
<header>
	<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="<c:url value="/" />">WEBSITE MOBILE SHOP</a>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class='nav-item <c:if test="${param.current == 'Home'}">active</c:if>'>
					<a class="nav-link" href="<c:url value="/" />">Home</a>
				</li>
				<li class='nav-item <c:if test="${param.current == 'Categories'}">active</c:if>'>
					<a class="nav-link" href="<c:url value="/list-categories" />">Categories</a>
				</li>
				<li class='nav-item <c:if test="${param.current == 'Products'}">active</c:if>'>
					<a class="nav-link" href="<c:url value="/list-products" />">Products</a>
				</li>
				<li class='nav-item <c:if test="${param.current == 'Accounts'}">active</c:if>'>
					<a class="nav-link" href="<c:url value="/list-accounts" />">Accounts</a>
				</li>
			</ul>
			<div class="my-2 my-lg-0">
				<p>${sessionScope.account.username} | <a href="<c:url value="/logout" />">Logout</a></p>
			</div>
		</div>
	</nav>
</header>