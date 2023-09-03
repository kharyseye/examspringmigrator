package sn.diti4.webstore.ihm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sn.diti4.webstore.business.CatalogBrowser;
import sn.diti4.webstore.business.User;
import sn.diti4.webstore.dao.DAOContext;
import sn.diti4.webstore.dao.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns="/login", loadOnStartup=1)
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = -4319076288258537575L;


	@Override
	public void init() throws ServletException {
		DAOContext.init( this.getServletContext() );
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute( "login", "" );
		request.setAttribute( "password", "" );
		request.setAttribute( "errorMessage", "" );
		request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter( "txtLogin" );
		String password = request.getParameter( "txtPassword" );
		
		request.setAttribute( "login", login );
		request.setAttribute( "password", password );
		
		User connectedUser = UserDAO.isValidLogin( login, password );
		if ( connectedUser != null ) {
			
			HttpSession session = request.getSession( true );
			session.setAttribute( "connectedUser", connectedUser );
			session.setAttribute( "catalogBrowser", new CatalogBrowser() );
			request.getRequestDispatcher( "/viewArticle.jsp" ).forward( request, response );
		
		} else {
		
			request.setAttribute( "errorMessage", "Bad identity" );			
			request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
			
		}
		
	}

}
