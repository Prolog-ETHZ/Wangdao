package com.wdtourism.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JdbcUtils {

	private String url;
	private String username;
	private String passwd;

	public JdbcUtils() {

		url = "jdbc:mysql://127.0.0.1:3306/semantic_web?useUnicode=true&characterEncoding=utf-8";
		username = "root";
		// passwd="16123653";
		passwd = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<String[]> getMatchTable(){
		Connection conn =this.getConnection();
		do {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (conn == null);
		List<String[]> table = new ArrayList<String[]>();
		String sql = "select * from triple_match";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String[] pair = new String[2];
				pair[0] = rs.getString("conception");
				pair[1] = rs.getString("word");
				table.add(pair);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}



	private void insertAnotherAnother(String ch, String en) {
		// TODO Auto-generated method stub
		JdbcUtils jdbc = new JdbcUtils();
		Connection conn = jdbc.getConnection();
		do {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (conn == null);
		String sql = "insert into triple_match(Conception,word) values('" + ch + "','" + en + "') ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(String ch, String en) {
		JdbcUtils jdbc = new JdbcUtils();
		Connection conn = jdbc.getConnection();
		do {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (conn == null);
		String sql = "insert into ch_en(Ch,En) values('" + ch + "','" + en + "') ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertAnother(String front, String end) {
		JdbcUtils jdbc = new JdbcUtils();
		Connection conn = jdbc.getConnection();
		do {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (conn == null);
		String sql = "insert into front_end(end,front) values('" + front + "','" + end + "') ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String FrontToEnd(String front){
		
		JdbcUtils jdbc = new JdbcUtils();
		Connection conn = jdbc.getConnection();
		do {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (conn == null);

		
			String sql = "select end from front_end where front='" +front + "'";

			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					String s = rs.getString("end");
				   return s ;
				}
			} catch (SQLException ex) {
				// err_msg = ex.getMessage();
				ex.printStackTrace();//
			}
			return null;
		
	
	}
	
	public void map(ArrayList<String> list) {
		JdbcUtils jdbc = new JdbcUtils();
		Connection conn = jdbc.getConnection();
		do {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (conn == null);

		for (int i = 0; i < list.size(); i++) {
			String sql = "select En from ch_en where ch='" + list.get(i) + "'";

			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.execute();
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					String s = rs.getString("En");
					list.remove(i);
					list.add(i, s);
				}
			} catch (SQLException ex) {
				// err_msg = ex.getMessage();
				ex.printStackTrace();
			}

		}
	}

}
