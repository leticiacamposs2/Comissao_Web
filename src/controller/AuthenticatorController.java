package controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.UserInfo;

@WebServlet("/Authenticator")
public class AuthenticatorController extends HttpServlet {
	private static final long serialVersionUID = 832190057546020811L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}	

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String user = request.getParameter("TXTUSER");
		String pass = request.getParameter("TXTPASS");
		String msg = null;
		HttpSession session = request.getSession();
	try {
		if ("leticia".equals(user) && "123456".equals(pass)) { 
			UserInfo userinfo = new UserInfo();
			userinfo.setProfile("user");
			userinfo.setNome("Leticia Campos");
			userinfo.setLogado(true);
			session.setAttribute("LOGADO", userinfo);
			response.sendRedirect("./principal.jsp");
		} else if ("admin".equals(user) && "123".equals(pass)) { 
			UserInfo userinfo = new UserInfo();
			userinfo.setProfile("admin");
			userinfo.setNome("Administrador");
			userinfo.setLogado(true);
			session.setAttribute("LOGADO", userinfo); 
			response.sendRedirect("./principal.jsp");
		} else { 
			msg = "Usuário ou senha incorretos";
			session.setAttribute("MENSAGEM", msg);
			session.setAttribute("LOGADO", null);
			response.sendRedirect("./login.jsp");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}

}
