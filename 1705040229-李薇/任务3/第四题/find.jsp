<%@page import="demo2.Connect"%>
<%@ page language="java" contentType="text/html; charset=utf-8" import="java.sql.ResultSet" 
import=" java.sql.Statement"
import="java.io.IOException"
import=" java.sql.Connection"
import="java.sql.DriverManager"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查找到符合标准的人员</title>
<%
	Connect m=new Connect();
	m.connect();
 %>
<style type="text/css">
	body{
		width: 100%;
		height: 100%;
	}
	#tou{
		background-image: url(f027159c0ed077fdc8aaf3c2e244c33f7065e8dc82978-t6J6Us_fw658.jpg);
		
		font-family:Consolas, Andale Mono, Lucida Console, Lucida Sans Typewriter, Monaco, Courier New, monospace;
		font-style: italic;
		height: 2cm;
		
	}
	.button1{
		background-image: url(e627cd5d083e97919c98bdb5b11dde26e97f370412dd7a-q9BfxE_fw658.jpg);
		position: relative;
		top:560px;
		color:aliceblue;
		width: 8cm;
		height: 1cm;
		left:390px;
	}
	.button2{
			background-image: url(e627cd5d083e97919c98bdb5b11dde26e97f370412dd7a-q9BfxE_fw658.jpg);
		position: relative;
		color:aliceblue;
		width: 8cm;
		height: 1cm;
		top:560px;
		left:450px;
	}
	table{
	position;
	left:20px;
		border: 2px solid #4D99E0;
		width: 95%;
	}
	tr{
		border: 2px solid #4D99E0;
	}
	td{
		border: 2px solid #4D99E0;
		text-align: center;
	}
	</style>
</head>
<body style="background-color:#E6F8FF ">

<%
   Statement st = null;
   int i=0;
   String[] tno=new String[100];
   String[] name=new String[100];
   int[] age=new int[100];
   int[] people=new int[100];
   int[] bmoney=new int[100];
   %>
<%
		try {
			String sql ="select * from"+" teachers";
			//复制之前的sql代码    每行必须要转换为字符串然后加上换行符
			//						idcard_w是用户传入的数据用于查询用户需要的信息
			String url = "jdbc:mysql://localhost:3306/gz?user="+"root"+"&password="+"123" + "&useUnicode=true&characterEncoding=utf-8";
			Connection connection = DriverManager.getConnection(url);    //通过url连接数据库
            st = connection.createStatement();    //获取可执行sql语句的对象
			ResultSet rs = st.executeQuery(sql);
			//从数据库读取的内容，返回一个结果集。
			System.out.println("获取数据");
			while (rs.next()) {
				tno[i] = rs.getString("tno");
				name[i] = rs.getString("name");
				age[i] = rs.getInt("age");
				people[i]=rs.getInt("people");
				bmoney[i]=rs.getInt("bmoney");
				i=i+1;
				//获取用循环接收数据库的表格信息
			}//遍历结果集
		} catch (Exception e) {
			System.out.printf("获取失败");
			if(st==null)
				System.out.print("st有问题");
			e.printStackTrace();
		}%>
<div id="tou">
		工资管理系统
		<center><div id="zj"><H1>查看教师现有状态</H1></div></center>
	</div>
	<div>
	   <table>
				<tr>
				   <td>教师编号</td>
				<td>教师姓名</td>
				<td>工龄</td>
				<td>赡养人数</td>
				<td>改变后的年工资</td>
				</tr>
				<%int j=0;
				    while(j<i){  %>
         <tr>
            <td><%=tno[j] %></td>
            <td><%=name[j]%></td>
            <td><%=age[j]%></td>
            <td><%=people[j]%></td>
            <td><%=bmoney[j]%></td>
         </tr>
         <%j=j+1; }%>
	   </table>	
	   <a href="audting.jsp" ><input type="submit" value="调整工资" class="button1"></a>
		<a href="choice.html"><input type="submit" value="返回" class="button2"></a>
	</div>
</body>
</html>