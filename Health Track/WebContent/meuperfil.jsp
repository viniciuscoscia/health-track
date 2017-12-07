<%@ include file="_modulos/inicio.jsp" %>
<head>
    <title>Meu Perfil - Health Track</title>
	<%@ include file="_modulos/head.jsp" %>
</head>
<body id="tabela-pressao">
<div class="container-fluid" id="dashboard">
    <%@ include file="_modulos/header.jsp" %>
    <section class="container-fluid" id="section-perfil">
        <div style="background-color: white; margin: 15px 0 15px 0;" class="container-fluid">
            <div class="container-fluid">
                <a class="col-md-12" href="MeuPerfil?acao=logout" style="margin-top:10px;">Sair</a>
                <h1 class="col-md-12 text-center">Meu Perfil</h1>
                <img src="_images/user.png" id="fotoperfil" class="col-md-3">
                <h2 class="col-md-6">${user.nome } ${user.sobrenome }</h2>
                <button type="button" class="btn-danger pull-right col-md-3 btn-md" data-toggle="modal"
                        data-target="#modal-alterarperfil" id="altinf">Alterar Informações
                </button>
            </div>
            <section class="container-fluid" id="perfil-dados">
                <div class="form-group">
                    <h3 class="col-md-6">Data de Nascimento:<br><javatime:format value="${user.dtNasc }" pattern="dd/MM/yyyy"></javatime:format></h3>
                    <h3 class="col-md-6">Gênero:
                    <br><c:if test="${user.isMale}">Masculino</c:if>
                    	<c:if test="${!(user.isMale)}">Feminino</c:if> </h3>
                </div>
                <div class="form-group">
                    <h3 class="col-md-6">Email:<br>${user.email }</h3>
                    <h3 class="col-md-6">Altura:<br>${user.altura }m</h3>
                </div>
                <div class="form-group inline-form">
                    <h3 class="col-md-1">Senha:</h3>
                    <button type="button" class="btn-danger col-md-2" id="alterasenha" data-toggle="modal"
                            data-target="#modal-alterarsenha" id="botaomodalalterarsenha">Alterar
                    </button>
                </div>
            </section>
        </div>
        <div id="modal-alterarsenha" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Alterar Senha</h4>
                    </div>
                    <div class="modal-body">
                        <form class="container-fluid form-vertical" id="trocarsenha" name="trocarsenha" method="post" action="MeuPerfil?acao=AlterarSenha">
                            <div class="form-group col-md-12">
                                <label for="senhaatual">Senha Atual:</label>
                                <input type="password" id="senhaatual" name="senhaatual"class="form-control" required>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="novasenha">Nova Senha:</label>
                                <input type="password" id="novasenha" name="novasenha" class="form-control" name="novasenha" required>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="confnovasenha">Confirmar Nova senha:</label>
                                <input type="password" id="confnovasenha" name="confnovasenha" class="form-control" name="confnovasenha" required>
                            </div>
                            <button type="submit" class="btn-danger btn-lg col-md-offset-6 col-md-6" id="salvarnovasenha">Salvar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="modal-alterarperfil" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modificar perfil</h4>
                    </div>
                    <div class="modal-body">
                        <form class="container-fluid form-vertical" id="modificarperfil" method="post" action="MeuPerfil?acao=AlterarPerfil">
                            <div class="form-group col-md-6">
                                <label for="name">Nome:</label>
                                <input type="text" name="name" id="name" class="form-control" value="${user.nome }" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="sobrenome">Sobrenome:</label>
                                <input type="text" name="sobrenome" id="sobrenome" class="form-control" value="${user.sobrenome }" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="dataNasc">Data de Nascimento:</label>
                                <input type="text" id="dataNasc" name="dataNasc" class="form-control data" value="<javatime:format value="${user.dtNasc }" pattern="dd/MM/yyyy"></javatime:format>" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="altura">Altura:</label>
                                <input type="number" id="altura" name="altura" step="0.01" class="form-control" value="${user.altura }" min=0 required>
                            </div>
                            <div class="form-group col-md-12">
                                <label>Gênero:</label>
                                <div class="radio" id="sexo">
                                    <label class="radio-inline"><input type="radio" name="opradio" value="true" <c:if test="${user.isMale == true}"> checked="checked" </c:if>>Masculino</label>
                                    <label class="radio-inline"><input type="radio" name="opradio" value="false" <c:if test="${user.isMale == false}"> checked="checked" </c:if>>Feminino</label>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="email">E-mail:</label>
                                <input type="email" id="email" name="email"	class="form-control" value="${user.email }" required>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="confemail">Confirmar e-mail:</label>
                                <input name="confemail" type="email" id="confemail" name="confemail" class="form-control" value="${user.email }" required>
                            </div>
                            <button type="submit" class="btn-danger btn-lg col-md-offset-9 col-md-3" id="salvarperfil">Salvar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<%@ include file="_modulos/foot.jsp" %>
</body>
</html>