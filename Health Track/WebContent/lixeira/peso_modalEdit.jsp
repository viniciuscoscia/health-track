<div id="modal-peso-edit" class="modal fade" role="dialog">
	<div class="modal-dialog modal-sm">
	<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Editar Peso</h4>
			</div>
			<div class="modal-body">
                <form class="container-fluid form-vertical" id="form-edit-peso">
                    <div class="form-group col-md-12">
                        <label>Data:</label>
                        <input type="text" name="data" class="form-control data" placeholder="DD/MM/AAAA" required>
                    </div>
                    <br>
                    <div class="form-group col-md-12">
                        <label>Peso (Em kilos):</label>
                        <input type="number" name="peso" step="0.01" min="5" class="form-control" required>
                    </div>
                    <button type="submit" class="btn-danger btn-lg col-md-offset-6 col-md-6">Registrar</button>
                </form>
			</div>
		</div>
	</div>
</div>