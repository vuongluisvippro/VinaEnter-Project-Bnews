<%@page import="bean.User"%>
<%@page import="bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
	<%
		User user = new User();
		user = (User)request.getAttribute("user");
	%>
		 <div class="modul()e-body">
		 	<%
		 		if(request.getAttribute("error")!=null){
		 			String error = (String)request.getAttribute("error");
		 			%>
		 			<p style="color:red; font-style:bold"><strong><%=error%></strong></p>	
		 			<%
		 		}
		 	%>
			<form action="<%=request.getContextPath()%>/admin/editUser?cid=<%=user.getId_user()%>" method="post">
				<p>
					<label>Username</label>
					<input type="text" name="username" value="<%=user.getUsername()%>" class="input-medium" disabled/>
				</p>
				<p>
					<label>Password</label>
					<input type="text" name="password" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname</label>
					<input type="text" name="fullname" value="<%=user.getFullname()%>" class="input-medium" />
				</p>
				<%
				if(request.getAttribute("messageBudle")!=null){
					 boolean result = (Boolean)request.getAttribute("messageBudle");
					 if(result){
						 out.print("<p style=\"color:red\">Họ và tên không chứa ký tự đặc biệt!</p>");
					 }
				}
				%>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sữa" /> 
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 