package br.com.healthtrack.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * A classe <i>Usuario</i> se referencia aos usu�rio e armazena seus dados, s�o eles: E-mail, nome, sobrenome, 
 * Data de Nascimento, Senha, altura e sexo.
 * 
 * @see retornaDados
 * 
 * @author Vinicius Coscia
 * 
 * @version 1.0
 * 
 */

public class Usuario {
	
	private String email;
	private String nome;
	private String sobrenome;
	private LocalDate dtNasc;
	private String senha;
	private float altura;
	private boolean isMale;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * 
	 * Construtor que obriga o preenchimento de todos os atributos da classe Usu�rio.
	 * 
	 * @param email Email do usu�rio
	 * @param nome Nome do usu�rio
	 * @param sobrenome do usu�rio
	 * @param dtNasc dtNasc do usu�rio
	 * @param senha senha do usu�rio
	 * @param altura altura do usu�rio
	 * @param sexo sexo do usu�rio
	 */
	
	public Usuario(String email, String nome, String sobrenome, LocalDate dtNasc, String senha, float altura, boolean isMale) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dtNasc = dtNasc;
		this.senha = senha;
		this.altura = altura;
		this.isMale = isMale;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public LocalDate getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(LocalDate dtNasc) {
		this.dtNasc = dtNasc;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public boolean isMale() {
		return isMale;
	}
	public boolean getIsMale() {
		return isMale;
	}
	public void setIsMale(boolean isMale) {
		this.isMale = isMale;
	}
	
	/**
	 * Imprime os dados do usu�rio 
	 */
	public void retornaDados() {
		System.out.println("Nome completo: " + this.getNome() + " " + this.getSobrenome());
		System.out.println("E-mail: " + this.getEmail());
		System.out.println("Data de Nascimento: " + this.getDtNasc().format(formatter));
		System.out.println("Altura: " + this.getAltura());
		System.out.println("Sexo masculino: " + this.isMale);
	}
	
}