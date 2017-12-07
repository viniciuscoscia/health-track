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

import br.com.healthtrack.alimentacao.Alimentacao;
import br.com.healthtrack.alimentacao.AlimentacaoDAO;
import br.com.healthtrack.usuario.Usuario;

/**
 * Servlet implementation class AlimentacaoController
 */
@WebServlet("/Alimentacao")
public class AlimentacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimentacaoController() {
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
			int codigo = Integer.valueOf(request.getParameter("codigo"));
			Alimentacao alimentacao = AlimentacaoDAO.selectById(codigo);
			request.setAttribute("alimentacao", alimentacao);
			request.getRequestDispatcher("editar_alimentacao.jsp").forward(request, response);
		}
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario user)
			throws ServletException, IOException {
		ArrayList<Alimentacao> alimentacaoList = AlimentacaoDAO.select(user.getEmail());
		request.setAttribute("alimentacao", alimentacaoList);
		request.getRequestDispatcher("tabela_alimentacao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String acao = request.getParameter("acao");
		
		LocalDate data = LocalDate.parse(request.getParameter("data") + " 00:00", formatter);
		LocalDateTime hora = LocalDateTime.parse("01/01/0001 " + request.getParameter("hora"), formatter);
		short calorias = Short.valueOf(request.getParameter("cal"));
		String descricao = request.getParameter("descricao");
		String ds_email = user.getEmail();
		int tipo = Integer.valueOf(request.getParameter("addaliment"));
		
		switch(acao) {
		case "insert":
			AlimentacaoDAO.insert((byte)tipo, ds_email, data, hora, calorias, descricao);
			break;
		case "update":
			short cd_alimentacao = Short.valueOf(request.getParameter("codigo"));
			AlimentacaoDAO.update((byte)tipo, data, hora, calorias, descricao, cd_alimentacao);
			break;
		}
		listar(request, response, user);
	}

}
