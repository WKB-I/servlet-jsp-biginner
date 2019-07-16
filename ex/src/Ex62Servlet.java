

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex62Servlet
 */
@WebServlet("/Ex62Servlet")
public class Ex62Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//奇数、偶数をだして奇数はリダイレクト、偶数はフォワード
		int rand = (int)(Math.random()*10);
		if(rand % 2 == 1) {
			response.sendRedirect("/ex/redirected.jsp");
		}else {
			RequestDispatcher d = request.getRequestDispatcher("/forwarded.jsp");
			d.forward(request, response);
		}
	}
}
