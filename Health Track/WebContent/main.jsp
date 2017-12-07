<%@ include file="_modulos/inicio.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
    <title>Dashboard - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="body-dashboard">
<fmt:setLocale value="pt_BR" scope="application"/>
<div class="container-fluid" id="dashboard">
    <%@ include file="_modulos/header.jsp" %>
    <section class="container-fluid" id="section-dados">
        <div style="background-color: white; margin: 15px 0px 15px 0px;" class="container-fluid">
            <h1 class="col-md-12 text-center">Olá, ${user.nome } </h1>
            <div id="row2" class="container-fluid col-md-6">
                <h1 class="text-center">Minhas informações</h1>
                <div class="form-group" id="dados">
                    <p class="col-md-6"><img src="_images/domain-age-icon.png"> ${idade} anos</p>
                    <p class="col-md-6"><img src="_images/pressao.png"> ${pressao.pressSistolica}/${pressao.pressDiastolica}mmHg</p>
                    <p class="col-md-12" style="text-align: center;">IMC</p>
                    <p class="col-md-6"><img src="_images/altura.PNG" class="img-circle"> ${user.altura }m</p>
                    <p class="col-md-6"><img src="_images/peso.png"> ${peso.peso }kg</p>
                    <c:set var="imc" scope="session" value="${peso.peso/(user.altura*user.altura) }" ></c:set>
                    <p class="col-md-12">
                    	<img src="_images/imc.png" class="img-circle">
                    	<fmt:formatNumber maxFractionDigits = "2" var="imcv" value = "${imc}" pattern="##.##"/> ${fn:replace(imcv, ",",".")} - Condição: <%@ include file="_modulos/calcularCondicao.jsp" %></p>
                </div>
            </div>
            <div class="container-fluid col-md-6 pull-right" id="table-dashboard">
                <h1 class="col-md-12" style="text-align: center;">Últimos registros</h1>
                <h5>Peso</h5>
                <table class="table-striped table-responsive container-fluid" id="dashboard">
                    <tbody id="tcorpo">
                    <tr>
                        <td rowspan="2"><img src="_images/peso.png" class="img-circle"></td>
                        <td id="ds">Data: <javatime:format value="${peso.data }" pattern="dd/MM/yyyy"></javatime:format></td>
                        <td rowspan="2">
                            <button type="button" class="btn-link btn-sm" data-toggle="modal"
                                    data-target="#modal-peso-add" id="botaomodalmain"><img src="_images/add.png"
                                                                                           class="img-circle"></button>
                        </td>
                    </tr>
                    <tr>
                        <td id="ds">Peso registrado: ${peso.peso }kg</td>
                    </tr>
                    </tbody>
                </table>
                <h5>Pressão Arterial</h5>
                <table class="table-striped table-responsive container-fluid" id="dashboard">
                    <tbody id="tcorpo">
                    <tr>
                        <td rowspan="4"><img src="_images/pressao.png" class="img-circle"></td>
                        <td id="ds">Data: <javatime:format value="${pressao.data }" pattern="dd/MM/yyyy"></javatime:format></td>
                        <td rowspan="4">
                            <button type="button" class="btn-link btn-sm" data-toggle="modal"
                                    data-target="#modal-pressao-add" id="botaomodalmain"><img src="_images/add.png"
                                                                                              class="img-circle">
                            </button></td>
                    </tr>
                    <tr>
                        <td id="ds">Pressão Sistólica: ${pressao.pressSistolica }mmHg</td>
                    </tr>
                    <tr>
                        <td id="ds">Pressão Diastólica: ${pressao.pressDiastolica }mmHg</td>
                    </tr>
                    <tr>
                        <td id="ds">Condição: ${pressao.condicao }</td>
                    </tr>
                    </tbody>
                </table>
                <h5>Atividade Física</h5>
                <table class="table-striped table-responsive container-fluid" id="dashboard">
                    <tbody id="tcorpo">
                    <tr>
                        <td rowspan="4"><img src="_images/ativ_fisica.png" class="img-circle"></td>
                        <td id="ds">Data: <javatime:format value="${atividade.data }" pattern="dd/MM/yyyy"></javatime:format>&emsp;-&emsp;Horário: <javatime:format value="${atividade.hora }" style="-S"></javatime:format></td>
                        <td rowspan="4">
                            <button type="button" class="btn-link btn-sm" data-toggle="modal"
                                    data-target="#modal-ativfisica-add" id="botaomodalmain"><img src="_images/add.png"
                                                                                                 class="img-circle">
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td id="ds">Tipo: ${atividade.tipo.tipoAtivFsc }</td>
                    </tr>
                    <tr>
                        <td id="ds">Cal. Gastas: ${atividade.calorias } calorias</td>
                    </tr>
                    <tr>
                        <td id="ds">Descrição: ${atividade.descricao }</td>
                    </tr>
                    </tbody>
                </table>
                <h5>Alimentação</h5>
                <table class="table-striped table-responsive container-fluid" id="dashboard">
                    <tbody id="tcorpo">
                    <tr>
                        <td rowspan="4"><img src="_images/comida.png" class="img-circle"></td>
                        <td id="ds">Data: <javatime:format value="${alimentacao.data }" pattern="dd/MM/yyyy"></javatime:format>&emsp;-&emsp;Horário: <javatime:format value="${alimentacao.hora }" style="-S"></javatime:format></td>
                        <td rowspan="4">
                            <button type="button" class="btn-link btn-sm" data-toggle="modal"
                                    data-target="#modal-alim-add" id="botaomodalmain"><img src="_images/add.png"
                                                                                           class="img-circle"></button>
                        </td>
                    </tr>
                    <tr>
                        <td id="ds">Tipo: ${alimentacao.tipo.descAlim }</td>
                    </tr>
                    <tr>
                        <td id="ds">Calorias: ${alimentacao.calorias } calorias</td>
                    </tr>
                    <tr>
                        <td id="ds">Descrição: ${alimentacao.descricao }</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</div>
<%@ include file="_modulos/peso_modalAdd.jsp" %>
<%@ include file="_modulos/pressao_modalAdd.jsp" %>
<%@ include file="_modulos/ativFisica_modalAdd.jsp" %>
<%@ include file="_modulos/alimentacao_modalAdd.jsp" %>
<%@ include file="_modulos/foot.jsp" %>
</body>
</html>