<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Deletar Registro - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="peso-editar">
	<div class="container-fluid editor" id="div-editar-peso">
	<h3 class="text-center">Deseja mesmo apagar o registro?</h3>
	<br>
	<c:url value="Deletar" var="link">
		<c:param name="acao" value="listar"></c:param>
		<c:param name="tipo" value="${tipo }"/>
		<c:param name="codigo" value="${codigo }"/>
	</c:url>
	<a class="btn-danger btn-lg col-lg-4 text-center" href="javascript:window.history.go(-1)"  >Não</a>
	<a class="btn-danger btn-lg col-lg-4 pull-right text-center" href="${link }">Sim</a>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>