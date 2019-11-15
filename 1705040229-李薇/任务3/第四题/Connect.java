package demo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


    public class Connect{
	static Connection connection = null;
	static ResultSet rs = null;

	
	static String userMySql="root"; 
	static String passwordMySql="123";
	static String urlMySql = "jdbc:mysql://localhost:3306/gz?user="
			+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=utf-8";
	
	//sqlserver���ݿ�url
	//String urlSqlServer = "jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=InfoDb";
	
	public Connect() {
		try {
			//mysql���ݿ�����������������
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("mysql���ݿ��������سɹ�");
			//DriverManager.getConnection(DriverManager.getConnection(););
			//sqlserver���ݿ�����������������
	        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        //System.out.println("sqlserver���ݿ��������سɹ�");

		}
		catch(java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}


	public static void connect(){
		try{
			//mysql���ݿ�
			connection = DriverManager.getConnection(urlMySql);  
			
			//sqlserver���ݿ�
			//connection = DriverManager.getConnection(urlSqlServer);
			

			if(connection!=null){
	            System.out.println("���ݿ����ӳɹ�");
	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void disconnect(){
		try{
			if(connection != null){
				connection.close();
				connection = null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	public static ResultSet executeQuery(String sql) {
		try {
			System.out.println("executeQuery(). sql = " + sql);
			
			PreparedStatement pstm = connection.prepareStatement(sql);
			// ִ�в�ѯ
			rs = pstm.executeQuery();
		} 
		catch(SQLException ex) { 
			ex.printStackTrace();
		}
		return rs;
	}
	
	//����
	//executeUpdate �ķ���ֵ��һ��������ָʾ��Ӱ��������������¼�������
	//executeUpdate����ִ�� INSERT��UPDATE �� DELETE ���
	//�Լ� SQL DDL�����ݶ������ԣ���䣬���� CREATE TABLE �� DROP TABLE��
	
	//ִ������ɾ�������ķ���
	public static int executeUpdate(String sql) {
		int count = 0;
		connect();
		try {
			Statement stmt = connection.createStatement();
			count = stmt.executeUpdate(sql);
		} 
		catch(SQLException ex) { 
			System.err.println(ex.getMessage());		
		}
		disconnect();
		return count;
	}
	public static void main(String agrs[]){
		Connect m=new Connect();
		m.connect();
	}
}