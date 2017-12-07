<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Editar Atividade Física - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="peso-editar">
	<div class="container-fluid editor" id="div-editar-peso">
	<h3 class="text-center">Editar Atividade Física</h3>
	<div class="modal-body">
		<form class="container-fluid form-vertical" id="form-edit-ativfisica" method="post" action="AtividadeFisica?acao=update">
			<input type="hidden" value="${atividade.cd_atividadefisica }" name="codigo" >
			<div class="form-group col-md-4">
				<label>Data:</label>
				<input type="text" name="data" class="form-control data" placeholder="DD-MM-AAAA" value="<javatime:format value="${atividade.data }" pattern="dd/MM/yyyy"></javatime:format>" required>
			</div>
			<div class="form-group col-md-4">
				<label>Número de calorias:</label>
				<input type="number" name="cal" step="0.01" class="form-control" value="${atividade.calorias }" required>
			</div>
			<div class="form-group col-md-4">
				<label>Hora:</label>
				<input type="text" name="hora" class="form-control" placeholder="hh:mm" value="<javatime:format value="${atividade.hora }" style="-S"></javatime:format>" required>
			</div>
			<div class="form-group col-md-12">
				<label>Tipo:</label>
				<div class="radio">
					<label class="radio-inline"><input type="radio" name="addativfisica" value="0" <c:if test="${atividade.tipo.codAtivFsc == 0 }"> checked="checked" </c:if>>Caminhada</label>
					<label class="radio-inline"><input type="radio" name="addativfisica" value="1" <c:if test="${atividade.tipo.codAtivFsc == 1 }"> checked="checked" </c:if>>Corrida</label>
					<label class="radio-inline"><input type="radio" name="addativfisica" value="2" <c:if test="${atividade.tipo.codAtivFsc == 2 }"> checked="checked" </c:if>>Pedalada</label>
					<label class="radio-inline"><input type="radio" name="addativfisica" value="3" <c:if test="${atividade.tipo.codAtivFsc == 3 }"> checked="checked" </c:if>>Musculação</label>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label>Descrição:</label>
				<input type="text" name="descricao" class="form-control" value="${atividade.descricao }" >
			</div>
				<a href="javascript:window.history.go(-1)"><button type="button" class="btn-danger btn-lg col-md-4">Cancelar</button></a>
				<button type="submit" class="btn-danger btn-lg col-md-offset-4 col-md-4">Salvar</button>
			</form>
		</div>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>