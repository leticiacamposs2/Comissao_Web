package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.GenericDAOException;
import dao.VendedorDAO;
import dao.VendedorDAOImpl;
import entidade.Vendedor;

/**
 * Servlet implementation class ListaVendedor
 */
@WebServlet("/ListaVendedor")
public class ListaVendedor extends HttpServlet {
	private static final long serialVersionUID = -5333801519083026822L;
	
	public ListaVendedor() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			VendedorDAO vDao = new VendedorDAOImpl();
			List<Vendedor> lista = vDao.pesquisarPorNome("");
			Gson gson = new Gson();
			String listaJSon = gson.toJson(lista);
			System.out.print(listaJSon);
			response.setContentType("application/json");
			response.getWriter().println(listaJSon);
		} catch (GenericDAOException e) {
			e.printStackTrace();
		}
	}

}
