package com.bv.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bv.dao.LoginDao;
import com.bv.model.Products;

public class ProductDBHandler {
				public static int addProducts(Products product) {
					int status=0;
					String sql="insert into products(productname,manufacturername,price) values(?,?,?)";
					Connection connection=LoginDao.getConnection();
					try {
						PreparedStatement ps=connection.prepareStatement(sql);
						ps.setString(1, product.getProductname());
						ps.setString(2, product.getManufacturername());
						ps.setDouble(3, product.getPrice());
						status=ps.executeUpdate();
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return status;
				}
				
				public static int updateProducts(Products product) {
					
					
					int status=0;
					String sql="update products set productname=?,manufacturername=?,price=? where id=?";
					Connection connection=LoginDao.getConnection();
					try {
						PreparedStatement ps=connection.prepareStatement(sql);
						ps.setString(1, product.getProductname());
						ps.setString(2, product.getManufacturername());
						ps.setDouble(3, product.getPrice());
						ps.setInt(4, product.getId());
						status=ps.executeUpdate();
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return status;
					
				}
				
	public static int deleteProducts(int product) {
					
					
					int status=0;
					String sql="delete from products where id = ?";
					Connection connection=LoginDao.getConnection();
					try {
						PreparedStatement ps=connection.prepareStatement(sql);
						ps.setInt(1, product);
						status=ps.executeUpdate();
					}catch(SQLException e) {
						e.printStackTrace();
					}
					return status;
					
				}
				
	public static Products getElementById(int Id) {
		
		
		Products u = new Products();
		try {
		
			Connection connection=LoginDao.getConnection();
			PreparedStatement s = connection.prepareStatement(
					"select * from products where id = ?");
			s.setInt(1, Id);
			ResultSet rs = s.executeQuery();
			if(rs.next()) {				
				u.setId(rs.getInt(1));
				u.setProductname(rs.getString(2));
				u.setManufacturername(rs.getString(3));
				u.setPrice(rs.getDouble(4));			
			}
			rs.close();
	//		con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return u;
		
	}
	
	public static List<Products> getAllProducts( ) {
		
		
		List<Products> productList=new ArrayList<>();
		String sql="select * from products";
		
		Connection connection=LoginDao.getConnection();
		try {
			Statement st=connection.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Products product=new Products();
				product.setId(rs.getInt("id"));
				product.setProductname(rs.getString(2));
				product.setManufacturername(rs.getString(3));
				product.setPrice(rs.getDouble(4));
				productList.add(product);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}

		return productList;
		
	}
				
}
