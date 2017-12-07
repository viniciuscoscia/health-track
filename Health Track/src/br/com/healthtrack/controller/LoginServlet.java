package br.com.healthtrack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthtrack.usuario.UsuarioDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns= {"/Login", ""})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		if(UsuarioDAO.validarUsuario(email, senha)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", UsuarioDAO.select(email));
			request.getRequestDispatcher("Main").forward(request, response);
		} else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
