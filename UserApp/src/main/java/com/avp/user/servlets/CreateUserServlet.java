package com.avp.user.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet(urlPatterns="/addServlet" , initParams = {@WebInitParam(name="dbUrl", value="jdbc:mysql://localhost/mydb"), 
		@WebInitParam(name="dbUser", value="root"), @WebInitParam(name="dbPassword", value="test1234")})
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(config.getInitParameter("dbUrl"), config.getInitParameter("dbUser"), 
					config.getInitParameter("dbPassword")); //("jdbc:mysql://localhost/mydb", "root", "test1234");
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {

			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into user values('" + firstName + "', '" + lastName + "','"
					+ email + "', '" + password + "')");
			PrintWriter out = response.getWriter();
			if (result > 0)
				out.println("<H1>User Created.</H1>");
			else
				out.println("<H1>Error Creating User.</H1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
