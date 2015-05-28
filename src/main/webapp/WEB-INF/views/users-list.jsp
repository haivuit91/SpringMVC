<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AsianTech - HaiVu</title>

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-theme.min.css" />"
	rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-1.9.1.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.confirm.js" />"></script>

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="users-list">Spring MVC</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" placeholder="Email" class="form-control" />
				</div>
				<div class="form-group">
					<input type="password" placeholder="Password" class="form-control" />
				</div>
				<button type="submit" class="btn btn-success">Sign in</button>
			</form>
		</div>
	</div>
	</nav>
	<div class="container" style="margin-top: 100px">
		<div class="row">
			<!-- 			<div class="btn-group" role="group"> -->
			<!-- 				<button type="button" class="btn btn-default dropdown-toggle" -->
			<!-- 					data-toggle="dropdown" aria-expanded="false"> -->
			<!-- 					Show <span class="caret"></span> -->
			<!-- 				</button> -->
			<!-- 				<ul class="dropdown-menu" role="menu"> -->
			<%-- 					<li><a href="${pageContext.request.contextPath}/user/show-user/5">5</a></li> --%>
			<%-- 					<li><a href="${pageContext.request.contextPath}/user/show-user/10">10</a></li> --%>
			<%-- 					<li><a href="${pageContext.request.contextPath}/user/show-user/15">15</a></li> --%>
			<!-- 				</ul> -->
			<!-- 			</div> -->
			<a type="button" class="btn btn-primary"
				href="${pageContext.request.contextPath}/user/add-user?page=${currentPage}">Add
				new User</a>
			<table id="table_user" class="table table-bordered"
				style="margin-top: 20px">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Password</th>
						<th>Full name</th>
						<!-- 						<th>Date of birth</th> -->
						<th>Email</th>
						<th>Active</th>
						<th style="width: 145px"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listUser" items="${listUser}">
						<tr>
							<td>${listUser.userId}</td>
							<td>${listUser.userName}</td>
							<td>${listUser.pwd}</td>
							<td>${listUser.fullName}</td>
							<%-- 							<td>${listUser.dateOfBirth}</td> --%>
							<td>${listUser.email}</td>
							<td>${listUser.active}</td>
							<td><a class="btn btn-success" role="button"
								href="${pageContext.request.contextPath}/user/edit-user?page=${currentPage}&userId=${listUser.userId}">Edit</a>
								<button class="btn btn-danger delConfirm"
									data-id="${listUser.userId}"
									data-text="Are you sure you want to delete user ${listUser.userName}?">Delete</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<nav>
			<ul class="pagination">
				<c:choose>
					<c:when test="${currentPage != 1}">
						<li><a
							href="${pageContext.request.contextPath}/user/users-list?page=${currentPage - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="" aria-label="Next"><span
								aria-hidden="true">&laquo;</span></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1" end="${pageSize}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<li class="active"><a href="">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a
								href="${pageContext.request.contextPath}/user/users-list?page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${currentPage lt pageSize}">
						<li><a
							href="${pageContext.request.contextPath}/user/users-list?page=${currentPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="" aria-label="Next"><span
								aria-hidden="true">&raquo;</span></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			</nav>
		</div>
	</div>

	<script type="text/javascript">
		$(".delConfirm").confirm({
			confirm : function(button) {
				var userId = $(button).data("id");
				$.ajax({
					type : 'DELETE',
					url : '${pageContext.request.contextPath}/user/del-user/' + userId,
					success : function(response) {
						alert(response);
						location.reload();
					},
					error : function(e) {
						alert('Error: ' + e);
					}
				});
			},
			cancel : function(button) {
				return false;
			},
			confirmButton : "Yes",
			cancelButton : "No",
			post : true
		});
	</script>

	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/run_prettify.js" />"></script>
</body>
</html>