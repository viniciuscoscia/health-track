<div id="modal-ativfisica-add" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nova Atividade F�sica</h4>
            </div>
            <div class="modal-body">
                <form class="container-fluid form-vertical" id="form-add-ativfisica" method="post" action="AtividadeFisica?acao=insert">
                    <div class="form-group col-md-4">
                        <label>Data:</label>
                        <input type="text" name="data" class="form-control data" placeholder="DD/MM/AAAA" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>N�mero de calorias:</label>
                        <input type="number" name="cal" step="0.01" class="form-control" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Hora:</label>
                        <input type="text" name="hora" class="form-control" placeholder="hh:mm" required>
                    </div>
                    <div class="form-group col-md-12">
                        <label>Tipo:</label>
                        <div class="radio" id="sexo">
                            <div class="radio" id="tipo_ativfisica">
                                <label class="radio-inline"><input type="radio" name="addativfisica" value="0" checked>Caminhada</label>
                                <label class="radio-inline"><input type="radio" name="addativfisica" value="1">Pedalada</label>
                                <label class="radio-inline"><input type="radio" name="addativfisica" value="2">Corrida</label>
                                <label class="radio-inline"><input type="radio" name="addativfisica" value="3">Muscula��o</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label>Descri��o:</label>
                        <input type="text" name="descricao" class="form-control">
                    </div>
                    <button type="submit" class="btn-danger btn-lg col-md-offset-9 col-md-3" id="salvaratvfis">Salvar</button>
                </form>
            </div>
        </div>
    </div>
</div>