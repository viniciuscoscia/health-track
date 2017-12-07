package br.com.healthtrack.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtrack.ativFisica.AtivFisica;
import br.com.healthtrack.ativFisica.AtivFisicaDAO;
import br.com.healthtrack.usuario.Usuario;

/**
 * Servlet implementation class AtivFisicaController
 */
@WebServlet("/AtividadeFisica")
public class AtivFisicaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtivFisicaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response, user);
			break;
		case "editar":
			short codigo = Short.valueOf(request.getParameter("codigo"));
			AtivFisica atividade = AtivFisicaDAO.selectById(codigo);
			request.setAttribute("atividade", atividade);
			request.getRequestDispatcher("editar_ativfisica.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		String acao = request.getParameter("acao");
		
		String ds_email = user.getEmail();
		byte cd_tipo = Byte.valueOf(request.getParameter("addativfisica"));
		short calorias = Short.valueOf(request.getParameter("cal"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDate data = LocalDate.parse(request.getParameter("data") + " 00:00", formatter);
		LocalDateTime hora = LocalDateTime.parse("01/01/0001 " + request.getParameter("hora"), formatter);
		String descricao = request.getParameter("descricao");
		switch(acao) {
		case "insert":
			AtivFisicaDAO.insert(cd_tipo, ds_email, data, hora, descricao, calorias);
			break;
		case "update":
			short cd_atividadefisica = Short.valueOf(request.getParameter("codigo"));
			AtivFisicaDAO.update(cd_tipo, data, hora, descricao, calorias, cd_atividadefisica);
			break;
		}
		
		listar(request, response, user);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario user) throws ServletException, IOException {
		ArrayList<AtivFisica> atividadeList = AtivFisicaDAO.select(user.getEmail());
		request.setAttribute("atividade", atividadeList);
		request.getRequestDispatcher("tabela_ativfisica.jsp").forward(request, response);
	}

}
