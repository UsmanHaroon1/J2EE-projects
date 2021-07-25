package com.users.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.users.dao.UserDao;
import com.users.model.User;


@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDAOObj;
	
	public void init() {
		userDAOObj = new UserDao();
	}
    
    public UserServlet() {
        super();        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String action = request.getServletPath();		
		
		try {
			switch (action) {
			case "/new": {
				showNewForm(request, response);
				break;
			}
			case "/insert": {
				insertUser(request, response);
				break;	
			}
			case "/update": {
				updateUser(request, response);
				break;
			}
			case "/delete": {
				deleteUser(request, response);
				break;
			}
			case "/edit":{
				showEditForm(request,response);
				break;
			}
			default:
				listUser(request,response);
				break;
			}
		} catch (ServletException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDAOObj.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("user-form.jsp");
		rd.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)	throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Ex user id:" + id);
		User existingUser = userDAOObj.selectUserByID(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		System.out.println(existingUser.toString());
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{		
		userDAOObj.insertUser(new User(request.getParameter("name"),request.getParameter("email"),request.getParameter("city")));
		response.sendRedirect("list");
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		userDAOObj.updateUser(new User(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),request.getParameter("email"),request.getParameter("city")));
		response.sendRedirect("list");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		userDAOObj.deleteUser(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("list");
	}
}
