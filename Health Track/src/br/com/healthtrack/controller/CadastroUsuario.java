package br.com.healthtrack.controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtrack.usuario.UsuarioDAO;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet("/cadastrar")
public class CadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {	
			String nome = request.getParameter("nome");
			String sobrenome = request.getParameter("sobrenome");
			System.out.println(sobrenome);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataNasc = LocalDate.parse(request.getParameter("dataNasc"), formatter);
			float altura = Float.valueOf(request.getParameter("altura"));
			boolean opradio = Boolean.parseBoolean(request.getParameter("opradio"));
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			UsuarioDAO.insert(email, opradio, nome, sobrenome, dataNasc, senha, altura);
			request.setAttribute("msg", "Cadastro efetuado com sucesso!");
			request.getRequestDispatcher("cadastro_sucesso.jsp").forward(request, response);
		} catch (SQLIntegrityConstraintViolationException e) {
			request.setAttribute("erro", "Erro ao reaizar cadastro: Email já cadastrado");
			request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("erro", "Erro ao reaizar cadastro");
			request.getRequestDispatcher("cadastrar.jsp").forward(request, response);
		}
		
	}
	

}
