package com.bv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bv.dao.LoginDao;

/**
 * Servlet implementation class ValidateServlet
 */
@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Connection connection = null;
		PrintWriter out=response.getWriter();
		try {
			connection = LoginDao.getConnection();
			PreparedStatement s = connection.prepareStatement("select username , password from users where username = ? and password = ?");
			s.setString(1, username);
			s.setString(2, password);
			RequestDispatcher rd;
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				HttpSession session=request.getSession();
				
				rd=request.getRequestDispatcher("productlist.jsp");
				rd.forward(request, response);
			}else {
				out.print("Invalid Credentials");
				rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
	}
}
