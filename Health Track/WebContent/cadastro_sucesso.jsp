<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="pt-br">
<head>
	<title>Cadastrar-se - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body>
	<div class="container-fluid" id="cadastrar">
		<c:if test="${not empty msg }">
			<br><br>
			<div class="alert alert-success">
				${msg}
			</div>
		</c:if>
		<c:if test="${not empty erro}">
			<br><br>
			<div class="alert alert-dager">
				${erro}
			</div>
		</c:if>
		<div class="col-md-12 text-center">
			<a type="button" class="btn btn-danger btn-lg" href="login.jsp">Menu Principal</a>
		</div>
	</div>
<%@ include file="_modulos/foot.jsp" %>
</body>
</html>