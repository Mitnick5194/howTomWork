public class PrimitiveServlet implements javax.servlet.Servlet {

	public void init(javax.servlet.ServletConfig config) throws javax.servlet.ServletException {
		System.out.println("init");
	}

	public void service(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response) throws javax.servlet.ServletException,
			java.io.IOException {
		System.out.println("from service");
		java.io.PrintWriter out = response.getWriter();
		out.println("Hello. Roses are red.");
		out.print("Violets are blue.");
	}

	public void destroy() {
		System.out.println("destroy");
	}

	public String getServletInfo() {
		return null;
	}

	public javax.servlet.ServletConfig getServletConfig() {
		return null;
	}

}
