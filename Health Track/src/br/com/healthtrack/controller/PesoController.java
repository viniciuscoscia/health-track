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

import br.com.healthtrack.peso.Peso;
import br.com.healthtrack.peso.PesoDAO;
import br.com.healthtrack.usuario.Usuario;

/**
 * Servlet implementation class PesoController
 */
@WebServlet("/Peso")
public class PesoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesoController() {
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
			Peso peso = PesoDAO.selectById(codigo);
			request.setAttribute("peso", peso);
			request.getRequestDispatcher("editar_peso.jsp").forward(request, response);
			break;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("user");
		String ds_email = user.getEmail();
		String acao = request.getParameter("acao");
		
		float peso = Float.valueOf(request.getParameter("peso"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDate data = LocalDate.parse(request.getParameter("data") + " 00:00", formatter);
		
		switch(acao) {
		case "insert":
			PesoDAO.insert(ds_email, peso, data);
			break;
		case "update":
			short cd_peso = Short.valueOf(request.getParameter("codigo"));
			PesoDAO.update(data, peso, cd_peso);
			break;
		}
		listar(request, response, user);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response, Usuario user) throws ServletException, IOException {
		ArrayList<Peso> pesoList = PesoDAO.select(user.getEmail());
		request.setAttribute("peso", pesoList);
		request.getRequestDispatcher("tabela_peso.jsp").forward(request, response);
	}

}
