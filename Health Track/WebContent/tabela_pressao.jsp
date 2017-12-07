<%@ include file="_modulos/inicio.jsp" %>
<head>
<title>Minha Pressão Arterial - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="tabela-pressao">
	<div class="container-fluid" id="dashboard">
		<%@ include file="_modulos/header.jsp" %>
		<section class="container-fluid" id="section-dados">
			<div style="background-color: white; margin: 15px 0px 15px 0px;"
				class="container-fluid">
				<h1 class="col-md-12 text-center">Minha Pressão Arterial</h1>
				<table class="table-striped table-responsive table-bordered"
					id="prin-pressao">
					<thead>
						<tr>
							<th>Data</th>
							<th>Sistólica(mmHg)</th>
							<th>Diastólica(mmHg)</th>
							<th>Condição</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pressao }" var="p">
							<tr>
								<td><javatime:format value="${p.data }" pattern="dd/MM/yyyy"></javatime:format></td>
								<td>${p.pressSistolica }</td>
								<td>${p.pressDiastolica }</td>
								<td>${p.condicao }</td>
								<td> 
									<c:url value="PressaoArterial" var="link">
										<c:param name="acao" value="editar"/>
										<c:param name="codigo" value="${p.cd_medicao }"/>
									</c:url>
									<a href="${link }" ><img src="_images/editar.png" class="img-circle"></a>
								</td>
								<td id="img">
									<c:url value="Deletar" var="link">
										<c:param name="acao" value=""></c:param>
										<c:param name="tipo" value="PressaoArterial"/>
										<c:param name="codigo" value="${p.cd_medicao }"/>
									</c:url>
									<a href="${link }"><img src="_images/remover.png" class="img-circle"></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="button" class="btn btn-danger btn-lg"
					data-toggle="modal" data-target="#modal-pressao-add"
					id="botaomodal">+</button>
			</div>
		</section>
		<!-- Modal -->
		<%@ include file="_modulos/pressao_modalAdd.jsp" %>
	</div>
<%@ include file="_modulos/foot.jsp" %>
</body>
</html>