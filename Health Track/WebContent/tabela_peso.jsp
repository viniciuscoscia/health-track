<%@ include file="_modulos/inicio.jsp" %>
<head>
	<title>Meu Peso - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="tabela-peso">
	<div class="container-fluid" id="dashboard">
		<%@ include file="_modulos/header.jsp" %>
		<section class="container-fluid" id="section-dados">
			<div style="background-color: white; margin: 15px 0px 15px 0px;" class="container-fluid">
				<h1 class="col-md-12 text-center">Meu peso</h1>
				<table class="table-striped table-responsive table-bordered" id="prin-peso">
					<thead>
						<tr>
							<th>Data</th>
							<th>Peso</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${peso}" var="p">
							<tr>
								<td><javatime:format value="${p.data }" pattern="dd/MM/yyyy"></javatime:format></td>
								<td>${p.peso } kg</td>
								<td> 
									<c:url value="Peso" var="link">
										<c:param name="acao" value="editar"/>
										<c:param name="codigo" value="${p.cd_peso }"/>
									</c:url>
									<a href="${link }" ><img src="_images/editar.png" class="img-circle"></a>
								</td>
								<td id="img">
									<c:url value="Deletar" var="link">
										<c:param name="acao" value=""></c:param>
										<c:param name="tipo" value="Peso"/>
										<c:param name="codigo" value="${p.cd_peso }"/>
									</c:url>
									<a href="${link }"><img src="_images/remover.png" class="img-circle"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- Trigger the modal with a button -->
				<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#modal-peso-add" id="botaomodal">+</button>
				<!-- Modal -->
				<%@ include file="_modulos/peso_modalAdd.jsp" %>
			</div>
		</section>
	</div>
	<%@ include file="_modulos/foot.jsp" %>
</body>
</html>