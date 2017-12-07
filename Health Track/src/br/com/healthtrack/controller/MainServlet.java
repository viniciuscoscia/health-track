package br.com.healthtrack.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtrack.alimentacao.Alimentacao;
import br.com.healthtrack.alimentacao.AlimentacaoDAO;
import br.com.healthtrack.ativFisica.AtivFisica;
import br.com.healthtrack.ativFisica.AtivFisicaDAO;
import br.com.healthtrack.peso.Peso;
import br.com.healthtrack.peso.PesoDAO;
import br.com.healthtrack.pressaoArterial.PressaoArterial;
import br.com.healthtrack.pressaoArterial.PressaoArterialDAO;
import br.com.healthtrack.usuario.Usuario;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		
		//Peso
		Peso peso = PesoDAO.selectLast(user.getEmail());
		request.setAttribute("peso", peso);
		
		//Pressão
		PressaoArterial pressao = PressaoArterialDAO.selectLast(user.getEmail());
		request.setAttribute("pressao", pressao);
		
		//Idade
		int idade = Period.between(user.getDtNasc(), LocalDate.now()).getYears();
		request.setAttribute("idade", idade);
		
		//Atividade Física
		AtivFisica atividade = AtivFisicaDAO.selectLast(user.getEmail());
		request.setAttribute("atividade", atividade);
		
		//Alimentacao
		Alimentacao alimentacao = AlimentacaoDAO.selectLast(user.getEmail());
		request.setAttribute("alimentacao", alimentacao);
		
		request.getRequestDispatcher("main.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
