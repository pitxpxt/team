package BoardTable;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/boardtable")
public class boardController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		/*
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		if(uid==null)
		{
			session.setAttribute("url", request.getRequestURL().toString());
			response.sendRedirect("/JavaWeb/users");
			return;
		}*/
		
		String View = new boardService(request,response).sub();
		
		if(View!=null)
		{
			getServletContext().getRequestDispatcher(View).forward(request, response);
		}
	}

}
