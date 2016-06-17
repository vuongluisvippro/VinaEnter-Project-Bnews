<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">
	<div class="module">
		 <h2><span>Đăng nhập</span></h2>
		 <div class="modul()e-body">
		 <%
		 	if("0".equals(request.getParameter("msg"))){
		 		out.print("<p style=\"color:red\">Sai username hoặc password!</p>");
		 	}
		 %>
			<form action="<%=request.getContextPath()%>/admin/login" method="post" id="frmuser">
				<p>
					<label>Username</label>
					<input type="text" name="username" value="" class="input-medium" />
				</p>
				<p>
					<label>Password</label>
					<input type="password" name="password" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="submit" type="submit" value="Đăng nhập" /> 
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->
	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 