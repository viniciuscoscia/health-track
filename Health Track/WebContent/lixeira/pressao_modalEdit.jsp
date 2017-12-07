<div id="modal-pressao-edit" class="modal fade" role="dialog">
	<div class="modal-dialog modal-md">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Editar Pressão</h4>
			</div>
			<div class="modal-body">
				<form class="container-fluid form-vertical" id="form-edit-pressao">
					<div class="col-md-12">
						<div class="form-group col-md-6">
							<label>Pressão Sistólica:</label> <input type="number"
								name="pressistolica" class="form-control"
								placeholder="A maior, em mmHg (Ex: 120)" id="pressistolica"
								required>
						</div>
						<div class="form-group col-md-6">
							<label>Pressão Diastólica:</label> <input type="number"
								name="pressdiastolica" id="pressdiastolica"
								class="form-control" placeholder="A menor, em mmHg (Ex: 80)"
								required>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group col-md-6">
							<label>Data:</label> <input type="text" name="data"
								class="form-control data" placeholder="DD/MM/AAAA" required>
						</div>
						<div class="form-group col-md-6">
							<label>Condição:</label> <input type="text" name="cal"
								class="form-control" id="condicao" required disabled>
						</div>
					</div>
					<br>
					<button type="submit"
						class="btn-danger btn-lg col-md-offset-9 col-md-3"
						id="salvarpressao">Salvar</button>
				</form>
			</div>
		</div>
	</div>
</div>