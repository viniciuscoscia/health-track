<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Editar Pressão - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="peso-editar">
	<div class="container-fluid editor" id="div-editar-peso">
		<h3 class="text-center">Editar Pressão</h3>
		<form class="container-fluid form-vertical" id="form-edit-pressao" method="post" action="PressaoArterial?acao=update">
			<input type="hidden" value="${pressao.cd_medicao }" name="codigo" >
			<div class="col-md-12">
				<div class="form-group col-md-6">
					<label>Pressão Sistólica:</label> 
					<input type="number" name="pressistolica" class="form-control" id="pressistolica" value="${pressao.pressSistolica }" required>
				</div>
				<div class="form-group col-md-6">
					<label>Pressão Diastólica:</label> 
					<input type="number" name="pressdiastolica" id="pressdiastolica" class="form-control" value="${pressao.pressDiastolica }"required>
				</div>
			</div>
			<div class="col-md-12">
				<div class="form-group col-md-12">
					<label>Data:</label> 
					<input type="text" name="data" class="form-control data" value="<javatime:format value="${pressao.data }" pattern="dd/MM/yyyy"></javatime:format>" required>
				</div>
			</div>
			<br>
				<a href="javascript:window.history.go(-1)"><button type="button" class="btn-danger btn-lg col-md-4">Cancelar</button></a>
				<button type="submit" class="btn-danger btn-lg col-md-offset-4 col-md-4">Salvar</button>
		</form>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>