<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>	
<html lang="pt-br">
<head>
	<title>Bem Vindo ao Health Track!</title>
	<%@ include file="_modulos/head.jsp" %>
	</head>
<body>
	<div id="main" class="container-fluid">
		<header class="containe-fluid" id="cabecalho">
			<img src="_images/logo.PNG" alt="logo" id="logo_login">
			<h2>Faça seu login</h2>
		</header>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger" id="erro">
				${erro}
			</div>
		</c:if>
		<form class="form-horizontal" id="tudo" method="POST" action="Login">
			<div class="form-group" id="email">
				<div class="input-group col-md-12" id="enteremail">
					<span class="input-group-addon"><i class="fa fa-user-circle fa-fw" aria-hidden="true"></i></span>
					<input type="email" name="email" class="form-control" id="form-email" placeholder="E-mail" required autofocus>
				</div>
				<br>
				<div class="col-md-12 input-group">
					<span class="input-group-addon"><i class="fa fa-lock fa-fw" aria-hidden="true"></i></span>
					<input type="password" name="senha" class="form-control" id="form-pwd" placeholder="Senha">
				</div>
				<div class="col-md-12">
					<a id="esqueceu" href="http://google.com.br" class="pull-right" role="button" target="_blank">Esqueceu a senha?</a>
				</div>
				<br>
				<div>
					<button id="entrar" type="submit" class="btn col-md-4">Entrar</button>
				</div>
				<div>
					<a href="cadastrar.jsp" id="cadastrar" type="button" class="btn col-md-offset-4 col-md-4">Cadastrar-se</a>
				</div>	
			</div>
		</form>
	</div>
<%@ include file="_modulos/foot.jsp" %>
</body>
</html>