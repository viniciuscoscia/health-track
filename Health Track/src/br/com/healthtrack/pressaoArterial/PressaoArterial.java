package br.com.healthtrack.pressaoArterial;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * 
 * Classe Pressao Arterial cria os registros de pressão arterial do usuário
 * 
 * @author Vinicius Nunes Coscia
 *
 * @version 1.0
 * 
 */

public class PressaoArterial {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String ds_email;
	private short cd_medicao;
	private short pressSistolica;
	private short pressDiastolica;
	private String condicao;
	private LocalDate data;
	
	public String getDs_email() {
		return ds_email;
	}

	public short getCd_medicao() {
		return cd_medicao;
	}
	
	public PressaoArterial(String ds_email, short pressSistolica, short pressDiastolica, LocalDate data) {
		this.ds_email = ds_email;
		this.pressSistolica = pressSistolica;
		this.pressDiastolica = pressDiastolica;
		this.data = data;
		calcularCondicao();
	}
	
	public PressaoArterial(String ds_email, short cd_medicao, short pressSistolica, short pressDiastolica, LocalDate data) {
		this(ds_email, pressSistolica, pressDiastolica, data);
		this.cd_medicao = cd_medicao;
		calcularCondicao();
	}
	
	public PressaoArterial() {
		
	}
	
	private void calcularCondicao() {
		if(this.pressSistolica >= 140 || this.pressDiastolica >= 90 ) {
			this.condicao = "Elevada";
		} else if (this.pressSistolica >= 120 || this.pressDiastolica >= 80) {
			this.condicao =  "Normal";
		} else if (this.pressSistolica < 100 || this.pressDiastolica < 60) {
			this.condicao =  "Abaixo do normal";
		} else {
			this.condicao = "Error";
		}
	}

	public short getPressSistolica() {
		return pressSistolica;
	}
	public void setPressSistolica(short pressSistolica) {
		this.pressSistolica = pressSistolica;
		calcularCondicao();
	}
	public short getPressDiastolica() {
		return pressDiastolica;
	}
	public void setPressDiastolica(short pressDiastolica) {
		this.pressDiastolica = pressDiastolica;
		calcularCondicao();
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PressaoArterial [ds_email=" + ds_email + ", cd_medicao=" + cd_medicao + ", pressSistolica="
				+ pressSistolica + ", pressDiastolica=" + pressDiastolica + ", data=" + data.format(formatter) + "]";
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public void setDs_email(String ds_email) {
		this.ds_email = ds_email;
	}

	public void setCd_medicao(short cd_medicao) {
		this.cd_medicao = cd_medicao;
	}
	
}
