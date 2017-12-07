package br.com.healthtrack.ativFisica;

/**
 * 
 * Classe especifica o tipo de atividade física feita pelo usuário. Objeto gerado junto com o objeto AtivFisica
 * 
 * @author Vinicius Nunes Coscia
 * 
 */

public class TipoAtivFisica {
	private byte codAtivFsc;
	private String tipoAtivFsc;
	
	public byte getCodAtivFsc() {
		return codAtivFsc;
	}
	public void setCodAtivFsc(byte codAtivFsc) {
		this.codAtivFsc = codAtivFsc;
	}
	public String getTipoAtivFsc() {
		return tipoAtivFsc;
	}
	public void setTipoAtivFsc(String tipoAtivFsc) {
		this.tipoAtivFsc = tipoAtivFsc;
	}
	
}
