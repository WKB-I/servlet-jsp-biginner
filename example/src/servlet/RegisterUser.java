package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Toforward
		String forwardPath = null;

		//DicisionServletClassMoveInAction
		//InRequestParameter
		String action = request.getParameter("action");

		//RequestIn"RegistStart"
		if(action == null) {
			//settingForward
			forwardPath = "/WEB-INF/jsp/registerForm.jsp";
		}

		//Request"RegisterDo"forRegisterCheckJsp
		else if(action.equals("done")) {
			//GetRegistSessionScopeUser
			HttpSession session = request.getSession();
			User registerUser = (User) session.getAttribute("registerUser");

			//CallRegisterLogic
			RegisterUserLogic logic = new RegisterUserLogic();
			logic.exucute(registerUser);

			//DeleteUnuseInstanceInSessionScope
			session.removeAttribute("registerUser");

			//Setting AlreadyRegistUser
			forwardPath = "/WEB-INF/jsp/registerDone.jsp";
		}

		//ForwardToSettingforward
		RequestDispatcher dispathcher = request.getRequestDispatcher(forwardPath);
		dispathcher.forward(request, response);
		//response.sendRedirect(forwardPath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GetRequestParamater
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		//SetRegisterUserInfo
		User registerUser = new User(id, name, pass);

		//RegistForSessionscopeInRegisterUser
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		//Forward
		RequestDispatcher dispathcher = request.getRequestDispatcher("/WEB-INF/jsp/registerConfirm.jsp");
		dispathcher.forward(request, response);
	}

}
