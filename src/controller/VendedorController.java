package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GenericDAOException;
import dao.VendedorDAO;
import dao.VendedorDAOImpl;
import entidade.Vendedor;

@WebServlet("/VendedorController")
public class VendedorController extends HttpServlet {
	private static final long serialVersionUID = 847203936154962226L;
	
	public VendedorController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter()
		.append("Para acessar utilize a pagina de <a href=\"./vendedor.jsp\">vendedor</a>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String msg = null;
		HttpSession session = request.getSession();
		try {
			VendedorDAO vDao = new VendedorDAOImpl();
			if("adicionar".equals(cmd)) {
				Vendedor v = new Vendedor();
				v.setMatricula(Integer.parseInt(request.getParameter("txtMatricula")));
				v.setNome(request.getParameter("txtNome"));
				v.setSalario(Float.parseFloat(request.getParameter("txtSalario")));
				v.setNivelExperiencia(request.getParameter("txtNivelExperiencia"));
				vDao.adicionar( v );
				List<Vendedor> lista = vDao.pesquisarPorNome("");
				session.setAttribute("LISTA", lista);
				msg = "Vendedor foi adicionado com sucesso";
			} else if ("pesquisar".equals(cmd)) {
				List<Vendedor> lista = vDao.pesquisarPorNome(request.getParameter("txtNome"));
				session.setAttribute("LISTA", lista);
				msg = "Foram encontrados " + lista.size() + " vendedores";
			} else if ("remover".equals(cmd)) {
				String matricula = request.getParameter("txtMatricula");
				vDao.remover(Integer.parseInt(matricula));
				msg = "Vendedor com a Matricula " + matricula + " foi removido";
				List<Vendedor> lista = vDao.pesquisarPorNome("");
				session.setAttribute("LISTA", lista);	
			} else if ("editar".equals(cmd)) {
				String matricula = request.getParameter("txtMatricula");
				Vendedor v = vDao.pesquisarPorMatricula(Integer.parseInt(matricula));
				session.setAttribute("VENDEDOR_ATUAL", v);
				msg = "Detalhes do Vendedor com a Matricula " + matricula;
			} else if ("salvar".equals(cmd)) {
				Vendedor v = new Vendedor();
				String matricula = request.getParameter("txtMatricula");
				v.setNome(request.getParameter("txtNome"));
				v.setSalario(Float.parseFloat(request.getParameter("txtSalario")));
				v.setNivelExperiencia(request.getParameter("txtNivelExperiencia"));
				vDao.salvar( Integer.parseInt(matricula), v );
				List<Vendedor> lista = vDao.pesquisarPorNome("");
				session.setAttribute("LISTA", lista);				
				msg = "Vendedor foi atualizado com sucesso";
			} 
		} catch (GenericDAOException | NumberFormatException e) {
			e.printStackTrace();
			msg = "Erro ao acessar este vendedor";
			msg += "\n\n" + e.getMessage() + "\n";
			for (StackTraceElement trace : e.getStackTrace()) { 
				msg += trace.toString();
			}
		}

		session.setAttribute("MENSAGEM", msg);
		response.sendRedirect("./vendedor.jsp");
	}

}