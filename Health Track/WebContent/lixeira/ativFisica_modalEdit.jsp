<div id="modal-ativfisica-edit" class="modal fade" role="dialog">
	<div class="modal-dialog">
	<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Editar Atividade Física</h4>
			</div>
			<div class="modal-body">
				<form class="container-fluid form-vertical" id="form-edit-ativfisica">
					<div class="form-group col-md-4">
						<label>Data:</label>
						<input type="text" name="data" class="form-control data" placeholder="DD-MM-AAAA" required>
					</div>
					<div class="form-group col-md-4">
						<label>Número de calorias:</label>
						<input type="number" name="cal" step="0.01" class="form-control" required>
					</div>
					<div class="form-group col-md-4">
						<label>Hora:</label>
						<input type="text" name="hora" class="form-control" placeholder="hh:mm" required>
					</div>
					<div class="form-group col-md-12">
						<label>Tipo:</label>
						<div class="radio" id="sexo">
							<div class="radio">
								<label class="radio-inline"><input type="radio" name="addativfisica"
																   checked>Caminhada</label>
								<label class="radio-inline"><input type="radio" name="addativfisica">Corrida</label>
								<label class="radio-inline"><input type="radio" name="addativfisica">Pedalada</label>
								<label class="radio-inline"><input type="radio" name="addativfisica">Musculação</label>
							</div>
						</div>
					</div>
					<div class="form-group col-md-12">
						<label>Descrição:</label>
						<input type="text" name="descricao" class="form-control">
					</div>
					<button type="submit" class="btn-danger btn-lg col-md-offset-9 col-md-3" id="salvareditatvfis">Salvar</button>
				</form>
			</div>
		</div>
	</div>
</div>