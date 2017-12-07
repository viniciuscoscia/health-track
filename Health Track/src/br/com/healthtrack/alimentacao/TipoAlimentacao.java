package br.com.healthtrack.alimentacao;

/**
 * 
 * Define o tipo de refeição que foi feita, automaticamente atribuído conforme
 * a construção do objeto Alimentacao
 * 
 * @author Vinicius Nunes Coscia
 * 
 * @version 1.0
 * 
 *
 */

public class TipoAlimentacao {
	private byte codAlim;
	private String descAlim;
	
	public byte getCodAlim() {
		return codAlim;
	}
	public void setCodAlim(byte codAlim) {
		this.codAlim = codAlim;
	}
	public String getDescAlim() {
		return descAlim;
	}
	public void setDescAlim(String descAlim) {
		this.descAlim = descAlim;
	}
	
}
