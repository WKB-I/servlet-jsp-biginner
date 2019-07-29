package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CounterServlet
 */
@WebServlet(description = "init()/destroy()を持つサーブレットクラス", urlPatterns = { "/CounterServlet" })
public class CounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			// Regist ByIntegerinstance About BrowseCount ForApplicationscope
			Integer count = 0;
			ServletContext application = config.getServletContext();
			application.setAttribute("count", count);

			System.out.println("init()が実行されました");
		}*/

	public void destroy() {
		System.out.println("destroy()が実行されました");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Increment Browsecount saved for applicationscope
		ServletContext application = this.getServletContext();
		Integer count = (Integer) application.getAttribute("count");
		count++;
		application.setAttribute("count", count);
		//HTMLoutput
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>訪問回数を表示</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>訪問回数" +count + "</p>");
		out.println("<a href=\"/example/CounterServlet\">更新</a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
