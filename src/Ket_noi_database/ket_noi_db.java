/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ket_noi_database;

/**
 *
 * @author Nguyen D
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ket_noi_db {
	String className = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/quan_ly_banking?useUnicode=true&characterEncoding=UTF-8";
	String user = "root";
	String pass ="";
	String table = "user";
	Connection cn;
	
	public Connection getConnection() {
		try {
			Class.forName(className);
			try {
				cn = DriverManager.getConnection(url,user,pass);
				System.out.println("Kết nối rùi nha!!!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
                                System.out.println("thất bạn ùi!");
			}
		} catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block

		}
		
		return cn;
		
	}
//	public static void main(String[] args) {
//		ConnectDB cDb = new ConnectDB();
//		cDb.getConnection();
//	}
	
}