<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Minha Alimentação - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="tabela-alimentacao">
	<div class="container-fluid" id="dashboard">
		<%@ include file="_modulos/header.jsp" %>
		<section class="container-fluid" id="section-dados">
			<div style="background-color: white; margin: 15px 0px 15px 0px;" class="container-fluid">
			<h1 class="col-md-12 text-center">Minha Alimentação</h1>
			<table class="table-striped table-responsive table-bordered" id="prin-alimentacao">
				<thead>
					<tr>
						<th>Data</th>
						<th>Hora</th>
						<th>Tipo</th>
						<th>Descrição</th>
						<th>Calorias</th>
						<th>Editar</th>
						<th>Excluir</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${alimentacao }" var="a">
						<tr>	
							<td><javatime:format value="${a.data }" pattern="dd/MM/yyyy"></javatime:format></td>
							<td><javatime:format value="${a.hora }" style="-S"></javatime:format></td>
							<td>${a.tipo.descAlim }</td>
							<td>${a.descricao }</td>
							<td>${a.calorias } kcal</td>
							<td> 
								<c:url value="Alimentacao" var="link">
									<c:param name="acao" value="editar"/>
									<c:param name="codigo" value="${a.cd_alimentacao }"/>
								</c:url>
								<a href="${link }" ><img src="_images/editar.png" class="img-circle"></a>
							</td>
							<td id="img">
								<c:url value="Deletar" var="link">
									<c:param name="acao" value=""></c:param>
									<c:param name="tipo" value="Alimentacao"/>
									<c:param name="codigo" value="${a.cd_alimentacao }"/>
								</c:url>
								<a href="${link }"><img src="_images/remover.png" class="img-circle"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- Trigger the modal with a button -->
			<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#modal-alim-add" id="botaomodal">+</button>
			<!-- Modal -->
			<%@ include file="_modulos/alimentacao_modalAdd.jsp" %>
		</div>
	</section>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>