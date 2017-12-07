<%@ include file="_modulos/inicio.jsp" %>
<html lang="pt-br">
<head>
	<title>Minhas Atividades Físicas - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="tabela-pressao">
	<div class="container-fluid" id="dashboard">
		<%@ include file="_modulos/header.jsp" %>
		<section class="container-fluid" id="section-dados">
			<div style="background-color: white; margin: 15px 0px 15px 0px;" class="container-fluid">
			<h1 class="col-md-12 text-center">Minhas Atividades Físicas</h1>	
				<table class="table-striped table-responsive table-bordered" id="prin-ativfisica">
					<thead>
						<tr>
							<th>Data</th>
							<th>Hora</th>
							<th>Tipo de Atividade</th>
							<th>Descrição</th>
							<th>Calorias Gastas</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${atividade }" var="a">
							<tr>	
								<td><javatime:format value="${a.data }" pattern="dd/MM/yyyy"></javatime:format></td>
								<td><javatime:format value="${a.hora }" style="-S"></javatime:format></td>
								<td>${a.tipo.tipoAtivFsc }</td>
								<td>${a.descricao }</td>
								<td>${a.calorias } kcal</td>
								<td> 
									<c:url value="AtividadeFisica" var="link">
										<c:param name="acao" value="editar"/>
										<c:param name="codigo" value="${a.cd_atividadefisica }"/>
									</c:url>
									<a href="${link }" ><img src="_images/editar.png" class="img-circle"></a>
								</td>								
								<td id="img">
									<c:url value="Deletar" var="link">
										<c:param name="acao" value=""></c:param>
										<c:param name="tipo" value="AtividadeFisica"/>
										<c:param name="codigo" value="${a.cd_atividadefisica }"/>
									</c:url>
									<a href="${link }"><img src="_images/remover.png" class="img-circle"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- Trigger the modal with a button -->
				<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#modal-ativfisica-add" id="botaomodal">+</button>
			</div>
			<%@ include file="_modulos/ativFisica_modalAdd.jsp" %>
		</section>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>