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
import model.PostMutterLogic;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//getRequestParameter
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");

		//checkInputParameter
		if(text != null && text.length() != 0) {
			//getTweetlistInRegistApplicationscope
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			//getTweetlistInRegistSessionscope
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//addTweetForTweetlist
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.exucute(mutter, mutterList);

			//RegistTweetlistForApplicationscope
			application.setAttribute("mutterList", mutterList);
		}else {
			//RegistErrormsgForRequestscope
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		//forwordMainScreen
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
