package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Dto;


public class Dao {
	
	//field
	DataSource datasource;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	//constructor
	public Dao() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			Context init = new InitialContext();
			datasource = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}
		catch(Exception e) {
		e.printStackTrace();
		}
	}
	

	//method
	public void join(Dto dto) {

		
		sql = "INSERT INTO member VALUES (?,?,?,?,?,?)";

		try {
			
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getRrd());
			
			
			
			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {

			System.out.println("DB 연결에 실패하였습니다.");
			e.printStackTrace();
		}finally{
			
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}

	}

	
	public String login(String id, String pw) {
		String username=null;
		sql = "select id from member where id=? and pw=?";

	
		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			
			rs.next();
		
			
			username = (String)(rs.getString(1));
			System.out.println("  id: "+username);
			
			
		} 
		catch (Exception e) {
			System.out.println("연결에 실패하였습니다.");
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}

		return username;
	}
	
	public ArrayList<Dto> list() {
		
		ArrayList<Dto> list = new ArrayList<>();
		sql = "select * from member";

		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();

			int i=0;
			while (rs.next()) {
				Dto dto = new Dto();
				dto.setId(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setAddress(rs.getString(5));
				dto.setRrd(rs.getString(6));
				list.add(dto);
				i++;
			}
			
			
		
			
		} 
		catch (Exception e) {
			System.out.println("연결에 실패하였습니다.");
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}

		return list;
	}
	
	
	
	public void delete(String name) {
		sql = "delete from member where name=?";
		
	
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			
			
		} 
		catch (Exception e) {
			System.out.println("연결에 실패하였습니다.");
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}

	}
	
	
	
		public Dto info(String name) {
		Dto dto = new Dto();
		sql = "select * from member where name=?";

		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,name);
			
			rs = pstmt.executeQuery();

			System.out.println("while진입전이다");
			if (rs.next()) {
				System.out.println("while진입했다.");
				dto.setId(rs.getString(1));
				dto.setPw(rs.getString(2));
				dto.setEmail(rs.getString(3));
				dto.setName(rs.getString(4));
				dto.setAddress(rs.getString(5));
				dto.setRrd(rs.getString(6));
			}
		
			
		
			
		} 
		catch (Exception e) {
			System.out.println("연결에 실패하였습니다.");
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
		}

		return dto;
	}
	
	
	
	

}
