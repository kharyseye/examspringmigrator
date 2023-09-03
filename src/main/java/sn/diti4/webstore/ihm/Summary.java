package sn.diti4.webstore.ihm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet( urlPatterns="/summary") 
public class Summary extends HttpServlet {

	private static final long serialVersionUID = -1204635336479052777L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession( true );
		if ( session.getAttribute( "connectedUser" ) == null ) {
			response.sendRedirect( "login" );
			return;
		}
		
		request.getRequestDispatcher( "/summary.jsp" ).forward( request, response );
	}
	
}
