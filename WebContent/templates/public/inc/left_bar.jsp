<%@page import="model.ModelCategory"%>
<%@page import="bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>Danh mục tin</h2>
<ul>
	<%
		for(Category catx : new ModelCategory().getList()){
			%>
				<li><a href="<%=request.getContextPath()%>/danh-muc?cid=<%=catx.getId_cat()%>"><%=catx.getName()%></a></li>
			<%
		}
	%>
</ul>
