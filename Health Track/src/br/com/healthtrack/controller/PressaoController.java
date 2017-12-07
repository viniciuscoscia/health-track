package br.com.healthtrack.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtrack.pressaoArterial.PressaoArterial;
import br.com.healthtrack.pressaoArterial.PressaoArterialDAO;
import br.com.healthtrack.usuario.Usuario;

/**
 * Servlet implementation class PressaoController
 */
@WebServlet("/PressaoArterial")
public class PressaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PressaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response, user);
			break;
		case "editar":
			short codigo = Short.valueOf(request.getParameter("codigo"));
			PressaoArterial pressao = PressaoArterialDAO.selectById(codigo);
			request.setAttribute("pressao", pressao);
			request.getRequestDispatcher("editar_pressao.jsp").forward(request, response);
			break;
		}
		
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario user) throws ServletException, IOException {
		ArrayList<PressaoArterial> pressaoList = PressaoArterialDAO.select(user.getEmail());
		request.setAttribute("pressao", pressaoList);
		request.getRequestDispatcher("tabela_pressao.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		String acao = request.getParameter("acao");
		
		short pressistolica = Short.valueOf(request.getParameter("pressistolica"));
		short pressdiastolica = Short.valueOf(request.getParameter("pressdiastolica"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(request.getParameter("data"), formatter);
		String email = user.getEmail();
		
		switch(acao) {
		case "insert":
			PressaoArterialDAO.insert(email, pressistolica, pressdiastolica, data);
			break;
		case "update":
			short cd_pressao = Short.valueOf(request.getParameter("codigo"));
			PressaoArterialDAO.update(cd_pressao, pressistolica, pressdiastolica, data);
			break;
		}
		listar(request, response, user);
	}
	
}
