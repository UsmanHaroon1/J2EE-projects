package com.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.users.model.User;

public class UserDao {
	
	private String jdbcURL="jdbc:mysql://localhost:3306/usersdb";
	private String jdbcName = "root";
	private String jdbcPass = "admin";
	
	private static final String INSERT_USER = "INSERT INTO users" + " (name,email,city) VALUES" + " (?,?,?);";
	private static final String UPDATE_USER = "UPDATE users set name = ?,email=?,city=? where id=?;";
	private static final String DELETE_USER = "DELETE from users where id=?;";
	
	private static final String SELECT_USER_BY_ID = "select * from users where id=?;";
	private static final String SELECT_ALL_USERS = "select * from users;";
		
	protected Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcName, jdbcPass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void insertUser (User user) throws SQLException
	{
		boolean isDone;
		try(Connection con = getConnection();
				PreparedStatement preparedstatement = con.prepareStatement(INSERT_USER);)
		{
			preparedstatement.setString(1,user.getName());
			preparedstatement.setString(2,user.getEmail());
			preparedstatement.setString(3,user.getCity());
			System.out.println(preparedstatement);
			isDone = preparedstatement.executeUpdate() > 0;
			System.out.println(isDone);
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public boolean updateUser(User user) throws SQLException
	{
		boolean rowUpdated = false;
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_USER);)
		{
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCity());
			ps.setInt(4, user.getId());
			
			System.out.println(user.toString());
			
			int output = ps.executeUpdate();
			int resu = ps.getUpdateCount();
			System.out.println("Execute update return:" + String.valueOf(output) +" "+ String.valueOf(resu));
			rowUpdated = output > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return rowUpdated;
	}
	
	public boolean deleteUser(int id) throws SQLException
	{
		boolean rowDeleted = false;
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_USER);)
		{		
			ps.setInt(1,id);
			int output = ps.executeUpdate();
			System.out.println("Execute delete return:" + String.valueOf(output));
			rowDeleted = output > 0;
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return rowDeleted;
	}
	
	public User selectUserByID(int id)
	{
		User userObj = null;
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_USER_BY_ID);)
		{
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				userObj = new User(id, rs.getString("name"),rs.getString("email"),rs.getString("city"));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return userObj;
	}
	
	public List<User> selectAllUsers()
	{
		List<User> users = new ArrayList<User>();
		
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_ALL_USERS);)
		{
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{			
				users.add(new User(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("city")));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
