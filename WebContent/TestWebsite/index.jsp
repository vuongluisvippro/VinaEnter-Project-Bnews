<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Chèn dữ liệu vào trang jsp - servlet</title>
	</head>
	<body>
		<%!
			Random rand = new Random();
		%>
		<p>Số ngẫu nhiên: <%=rand.nextInt(100)%></p>
	</body>
</html>