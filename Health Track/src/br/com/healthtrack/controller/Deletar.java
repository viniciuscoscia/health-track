package br.com.healthtrack.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.healthtrack.alimentacao.AlimentacaoDAO;
import br.com.healthtrack.ativFisica.AtivFisicaDAO;
import br.com.healthtrack.peso.PesoDAO;
import br.com.healthtrack.pressaoArterial.PressaoArterialDAO;
import br.com.healthtrack.usuario.Usuario;

/**
 * Servlet implementation class Deletar
 */
@WebServlet("/Deletar")
public class Deletar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		String acao = request.getParameter("acao");
		String tipo = request.getParameter("tipo");
		Short codigo = Short.valueOf(request.getParameter("codigo"));
		
		request.setAttribute("tipo", tipo);
		request.setAttribute("codigo", codigo);
		
		switch(acao) {
		case "listar":
			apagar(user, tipo, codigo, request, response);
			break;
		default:
			request.getRequestDispatcher("confirmaapagar.jsp").forward(request, response);
			break;
		}
	}

	private void apagar(Usuario user, String tipo, Short codigo, HttpServletRequest request, HttpServletResponse response) {
		switch(tipo) {
		case "Alimentacao":
			AlimentacaoDAO.delete(codigo, user.getEmail());
			break;
		case "AtividadeFisica":
			AtivFisicaDAO.delete(codigo, user.getEmail());
			break;
		case "Peso":
			PesoDAO.delete(codigo, user.getEmail());
			break;
		case "PressaoArterial":
			PressaoArterialDAO.delete(codigo, user.getEmail());
		}
		
		try {
			request.setAttribute("acao", "listar");
			request.getRequestDispatcher(tipo).forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
