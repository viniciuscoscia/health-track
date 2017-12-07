package br.com.healthtrack.ativFisica;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe que cria objetos para registro das atividades físicas.
 * 
 * @author Vinicius Nunes Coscia
 *
 * @version 1.0
 * 
 */

public class AtivFisica {
	
	private String ds_email;
	private short cd_atividadefisica;
	private short calorias;
	private LocalDate data;
	private LocalDateTime hora;
	private String descricao;
	TipoAtivFisica tipo = new TipoAtivFisica();

	public AtivFisica(String ds_email, short cd_atividadefisica, short calorias, LocalDate data, LocalDateTime hora, String descricao, byte cd_tipo_atividade) {
		this.ds_email = ds_email;
		this.cd_atividadefisica = cd_atividadefisica;
		this.calorias = calorias;
		this.data = data;
		this.hora = hora;
		this.descricao = descricao;
		this.tipo.setCodAtivFsc(cd_tipo_atividade);
		setTipoDeAtivFisica();
	}
	
	
	public AtivFisica(short calorias, LocalDate data, LocalDateTime hora, String descricao, byte cd_tipo_atividade) {
		this.calorias = calorias;
		this.data = data;
		this.hora = hora;
		this.descricao = descricao;
		this.tipo.setCodAtivFsc(cd_tipo_atividade);
		setTipoDeAtivFisica();
	}
	
	public AtivFisica() {
		
	}

	/**
	 * Procedimento que define a String tipoAtivFsc
	 */
	
	public void setTipoDeAtivFisica() {
		if (tipo.getCodAtivFsc() == 0) {
			tipo.setTipoAtivFsc("Caminhada");
		}else if(tipo.getCodAtivFsc() == 1) {
			tipo.setTipoAtivFsc("Pedalada");
		}else if(tipo.getCodAtivFsc() == 2) {
			tipo.setTipoAtivFsc("Corrida");
		}else if(tipo.getCodAtivFsc() == 3) {
			tipo.setTipoAtivFsc("Musculação");
		}else {
			tipo.setTipoAtivFsc("Invalid");
		}
	}
	
	public short getCalorias() {
		return calorias;
	}
	public void setCalorias(short calorias) {
		this.calorias = calorias;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalDateTime getHora() {
		return hora;
	}
	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDs_email() {
		return ds_email;
	}

	public void setDs_email(String ds_email) {
		this.ds_email = ds_email;
	}

	public short getCd_atividadefisica() {
		return cd_atividadefisica;
	}

	public void setCd_atividadefisica(short cd_atividadefisica) {
		this.cd_atividadefisica = cd_atividadefisica;
	}
	

	public TipoAtivFisica getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtivFisica tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "AtivFisica [ds_email=" + ds_email + ", cd_atividadefisica=" + cd_atividadefisica + ", calorias="
				+ calorias + ", data=" + data + ", hora=" + hora + ", descricao=" + descricao + ", tipoAtividade = " + tipo.getTipoAtivFsc() + "]";
	}
	
}
