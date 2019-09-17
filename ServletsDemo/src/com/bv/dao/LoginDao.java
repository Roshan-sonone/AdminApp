package com.bv.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class LoginDao {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\Roshan\\Connection.properties");
			Properties properties = new Properties();
			properties.load(fis);
			String driverClass = (String) properties.get("driverclass");
			String url = (String) properties.get("url");
			String username = (String) properties.get("username");
			String Password = (String) properties.get("password");

			Class.forName(driverClass);
			System.out.println("Driver Found");
			connection = DriverManager.getConnection(url, username, Password);
			System.out.println("Connection Established");

		} catch (IOException | SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}
}
