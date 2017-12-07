package br.com.healthtrack.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtrack.usuario.Usuario;
import br.com.healthtrack.usuario.UsuarioDAO;

/**
 * Servlet implementation class MeuPerfilController
 */
@WebServlet("/MeuPerfil")
public class MeuPerfilController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MeuPerfilController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch(acao) {
		case "logout":
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("").forward(request, response);
			break;
		default:
			request.getRequestDispatcher("meuperfil.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		
		String acao = request.getParameter("acao");
		
		switch(acao) {
		case "AlterarPerfil":
			String novoEmail = request.getParameter("email");
			String nome = request.getParameter("name");
			String sobrenome = request.getParameter("sobrenome");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDate data = LocalDate.parse(request.getParameter("dataNasc") + " 00:00", formatter);
			float altura = Float.valueOf(request.getParameter("altura"));
			boolean isMale = Boolean.valueOf(request.getParameter("opradio"));
			
			UsuarioDAO.update(user.getEmail(), novoEmail, isMale, nome, sobrenome, data, altura);
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", UsuarioDAO.select(novoEmail));
			break;
		case "AlterarSenha":
			String senhaatual = request.getParameter("senhaatual");
			String novasenha = request.getParameter("novasenha");
			
			UsuarioDAO.alterarSenha(user.getEmail(), senhaatual, novasenha);
			break;
		}

		doGet(request, response);
	}

}
