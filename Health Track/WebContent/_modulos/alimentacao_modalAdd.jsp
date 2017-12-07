<div id="modal-alim-add" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nova Refeição</h4>
            </div>
            <div class="modal-body">
                <form class="container-fluid form-vertical" id="form-add-alimentacao" method="post" action="Alimentacao?acao=insert">
                    <div class="form-group col-md-4">
                        <label>Data:</label>
                        <input type="text" name="data" class="form-control data" placeholder="DD/MM/AAAA" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Hora:</label>
                        <input type="text" name="hora" class="form-control" placeholder="hh:mm" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label>Número de calorias:</label>
                        <input type="number" name="cal" step="0.01" class="form-control" max="9999" required>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="genero">Tipo:</label>
                        <div class="radio" id="sexo">
                            <label class="radio-inline"><input type="radio" name="addaliment" checked="checked" value="0">Café da Manhã</label>
                            <label class="radio-inline"><input type="radio" name="addaliment" value="1">Almoço</label>
                            <label class="radio-inline"><input type="radio" name="addaliment" value="2">Janta</label>
                            <label class="radio-inline"><input type="radio" name="addaliment" value="3">Lanche Leve</label>
                            <label class="radio-inline"><input type="radio" name="addaliment" value="4">Fruta</label>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label>Descrição:</label>
                        <input type="text" name="descricao" class="form-control">
                    </div>
                    <button type="submit" class="btn-danger btn-lg col-md-offset-9 col-md-3" id="salvaralimentacao">Salvar</button>
                </form>
            </div>
        </div>
    </div>
</div>