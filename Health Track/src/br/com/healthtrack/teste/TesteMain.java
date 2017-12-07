package br.com.healthtrack.teste;

import br.com.healthtrack.peso.PesoDAO;

public class TesteMain {

	public static void main(String[] args) {
		System.out.println(PesoDAO.select("vinicius.coscia@live.com").get(0).getPeso());
		
	}

}
