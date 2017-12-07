<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Editar Peso - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="peso-editar">
	<div class="container-fluid editor" id="div-editar-peso">
		<h3 class="text-center">Editar Peso</h3>
		<br>
		<form class="container-fluid form-vertical" id="form-edit-peso" method="post" action="Peso?acao=update">
			<input type="hidden" value="${peso.cd_peso }" name="codigo" >
			<div class="form-group col-md-12">
				<label>Data:</label>
				<input type="text" name="data" class="form-control data" placeholder="DD/MM/AAAA" value="<javatime:format value="${peso.data }" pattern="dd/MM/yyyy"></javatime:format>" required>
			</div>
				<br>
			<div class="form-group col-md-12">
				<label>Peso (Em kilos):</label>
				<input type="number" name="peso" step="0.01" min="5" class="form-control" value="${peso.peso }" required>
			</div>
			<a href="javascript:window.history.go(-1)"><button type="button" class="btn-danger btn-lg col-md-4">Cancelar</button></a>
			<button type="submit" class="btn-danger btn-lg col-md-offset-4 col-md-4">Salvar</button>
		</form>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>