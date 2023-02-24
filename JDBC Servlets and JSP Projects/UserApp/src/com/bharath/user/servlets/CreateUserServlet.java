package com.bharath.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet(urlPatterns = "/addServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public void init(ServletConfig config) {
		try {
			ServletContext context = config.getServletContext();

			System.out.println("init()");

			Enumeration<String> parameterNames = context.getInitParameterNames();

			while (parameterNames.hasMoreElements()) {
				String eachName = parameterNames.nextElement();
				System.out.println("Context Param Name: " + eachName);
				System.out.println("Context Param Value: " + context.getInitParameter(eachName));
			}

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(context.getInitParameter("dbUrl"),
					context.getInitParameter("dbUser"), context.getInitParameter("dbPassword"));
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("doPost()");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into user values('" + firstName + "','" + lastName + "','"
					+ email + "','" + password + "')");
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.print("<H1>USER CREATED</H1");
			} else {
				out.print("<H1>Error Creating the User</H1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		try {
			System.out.println("destroy()");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
