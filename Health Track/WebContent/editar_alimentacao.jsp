<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Editar Refeição - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="peso-editar">
	<div class="container-fluid editor" id="div-editar-peso">
		<h3 class="text-center">Editar Refeição</h3>
		<form class="container-fluid form-vertical" id="form-edit-alimentacao" method="post" action="Alimentacao?acao=update">
		<br>
			<input type="hidden" value="${alimentacao.cd_alimentacao }" name="codigo" >
			<div class="form-group col-md-4">
				<label>Data:</label>
				<input type="text" name="data" class="form-control data" placeholder="DD/MM/AAAA" value="<javatime:format value="${alimentacao.data }" pattern="dd/MM/yyyy"></javatime:format>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Hora:</label>
				<input type="text" name="hora" class="form-control" placeholder="hh:mm" value="<javatime:format value="${alimentacao.hora }" style="-S"></javatime:format>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Número de calorias:</label>
				<input type="number" name="cal" step="0.01" class="form-control" value="${alimentacao.calorias }" max="9999" required>
			</div>
			<div class="form-group col-md-12">
				<label>Tipo:</label>
				<div class="radio" id="sexo">
					<label class="radio-inline"><input type="radio" name="addaliment" value="0" <c:if test="${alimentacao.tipo.codAlim == 0 }"> checked="checked" </c:if>>Café da Manhã</label>
					<label class="radio-inline"><input type="radio" name="addaliment" value="1" <c:if test="${alimentacao.tipo.codAlim == 1 }"> checked="checked" </c:if>>Almoço</label>
					<label class="radio-inline"><input type="radio" name="addaliment" value="2" <c:if test="${alimentacao.tipo.codAlim == 2 }"> checked="checked" </c:if>>Janta</label>
					<label class="radio-inline"><input type="radio" name="addaliment" value="3" <c:if test="${alimentacao.tipo.codAlim == 3 }"> checked="checked" </c:if>>Lanche Leve</label>
					<label class="radio-inline"><input type="radio" name="addaliment" value="4" <c:if test="${alimentacao.tipo.codAlim == 4 }"> checked="checked" </c:if>>Fruta</label>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label>Descrição:</label>
				<input type="text" name="descricao" class="form-control" name="descricao" value="${alimentacao.descricao }">
			</div>
				<a href="javascript:window.history.go(-1)"><button type="button" class="btn-danger btn-lg col-md-4">Cancelar</button></a>
				<button type="submit" class="btn-danger btn-lg col-md-offset-4 col-md-4">Salvar</button>
		</form>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>