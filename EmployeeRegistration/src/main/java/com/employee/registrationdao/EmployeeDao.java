package com.employee.registrationdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.employee.registrationmodel.Employee;

public class EmployeeDao {

	public int registerEmployee(Employee employee)
	{
		String INSERT_SQL = "INSERT INTO employee"+
								"(id,firstname,lastname,username,password,contact) VALUES"+
								"(?,?,?,?,?,?)";
		
		int result = 0;		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employees","root","admin");						 
			PreparedStatement stmt = con.prepareStatement(INSERT_SQL);
			stmt.setInt(1, employee.getId());
			stmt.setString(2, employee.getFirstName());
			stmt.setString(3, employee.getLastName());
			stmt.setString(4, employee.getUserName());
			stmt.setString(5, employee.getPassword());
			stmt.setString(6, employee.getContact());
			//stmt.setString(7, employee.getAddress());
			
			System.out.println(stmt);
			result = stmt.executeUpdate();
		} catch (ClassNotFoundException e) {				
			e.printStackTrace();
		} catch (SQLException e) {				
			e.printStackTrace();
		}			
		
		return result;
	}
}
