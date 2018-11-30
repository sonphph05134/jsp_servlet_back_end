<%@include file="/views/common/common.jsp"%>
<c:set var="pageTitle" value="Edit Products" scope="page"></c:set>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/views/common/head.jsp"%>
	
	<script type="text/javascript">
		$(document).ready( function() {
			$(document).on('change', '.btn-file :file', function() {
				var input = $(this),
					label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				input.trigger('fileselect', [label]);
			});
	
			$('.btn-file :file').on('fileselect', function(event, label) {
	
				var	input = $(this).parents('.input-group').find(':text'),
					log = label;
	
				if(input.length) {
					input.val(log);
				} else {
					if( log ) alert(log);
				}
			});

			function readURL(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();

					reader.onload = function (e) {
						$('#img-upload').attr('src', e.target.result);
					}

					reader.readAsDataURL(input.files[0]);
				}
			}
	
			$("#image").change(function(){
				readURL(this);
			}); 	
		});
	</script>
</head>
<body>
	<div class="container">
		<jsp:include page="/views/common/header.jsp">
			<jsp:param name="current" value="Products" />
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

			<form action="<c:url value="/edit-product" />" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${product.id}" />
				<input type="hidden" name="image" value="${product.image}" />
				<div class="row">
					<div class="col-md">

						<div class="form-group <c:if test="${not empty errCode}">has-danger</c:if>">
							<label for="code">Code:</label>
							<input type="text" class="form-control" name="code" id="code" placeholder="Code" value="${product.code}">
							<c:if test="${not empty errCode}">
								<div class="form-control-feedback">${errCode}</div>
							</c:if>
						</div>

						<div class="form-group <c:if test="${not empty errName}">has-danger</c:if>">
							<label for="name">Name:</label>
							<input type="text" class="form-control" name="name" id="name" placeholder="Name" value="${product.name}">
							<c:if test="${not empty errName}">
								<div class="form-control-feedback">${errName}</div>
							</c:if>
						</div>
						<div class="form-group <c:if test="${not empty errFatherId}">has-danger</c:if>">
							<label for="fatherId">Category:</label>
							<select class="form-control" name="categoryId" id="categoryId">
								<c:forEach var="category" items="${listCategories}">
									<option value="${category.id}">${category.name}</option>
									<c:forEach var="subCategory" items="${category.subCategories}">
										<option value="${subCategory.id}">- ${subCategory.name}</option>
									</c:forEach>
								</c:forEach>
							</select>
							<c:if test="${not empty errFatherId}">
								<div class="form-control-feedback">${errFatherId}</div>
							</c:if>
						</div>
						<div class="form-group <c:if test="${not empty errUnitPrice}">has-danger</c:if>">
							<label for="unitPrice">Unit Price:</label>
							<input type="text" class="form-control" name="unitPrice" id="unitPrice" placeholder="Unit Price" value="${product.unitPrice}">
							<c:if test="${not empty errUnitPrice}">
								<div class="form-control-feedback">${errUnitPrice}</div>
							</c:if>
						</div>
					</div>	
					<div class="col-md">
						<div class="form-group">
							<label>Upload Image</label>
							<div class="input-group">
								<span class="input-group-btn">
									<span class="btn btn-primary btn-file">
										Browse <input type="file" id="image" name="image">
									</span>
								</span>
								<input type="text" class="form-control" readonly>
							</div>
							<img id='img-upload'/>
						</div>
					</div>
				</div>
			<div>
					<button type="submit" class="btn btn-primary">Submit</button>
					<button type="reset" class="btn btn-warning">Reset</button>
					<a href="<c:url value="/list-products" />" class="btn btn-danger">Back</a>
				</div>
				
			</form>
		</div>

		<jsp:include page="/views/common/footer.jsp">
			<jsp:param name="current" value="Products" />
		</jsp:include>
	</div>
</body>
</html>