package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet(description = "つぶやきに関するリクエストを処理するコントローラー", urlPatterns = { "/Main" })
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getTweetlistInAppricationScope
		ServletContext application = this.getServletContext();
		@SuppressWarnings("unchecked")
		List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		//whenNotgetTweetList,CreateNewTweetListInstanceSaveApplicationScope
		if(mutterList == null) {
			mutterList = new ArrayList<>();
			application.setAttribute("mutterList", mutterList);
		}

		//getUserInfoInSessionScopeForCheckLogin
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null) {
			//Redirect whenNotLogin
			response.sendRedirect("/docoTsubu/");
		}else {
			//Forword whenLogin
			RequestDispatcher dispathcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispathcher.forward(request, response);
		}
	}

}
