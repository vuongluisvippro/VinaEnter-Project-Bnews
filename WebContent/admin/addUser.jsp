<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		<%
			if(request.getAttribute("error")!=null){
				String error = (String)request.getAttribute("error");
				%>
					<p style = "color:red; font-style:bold"><%=error%></p>	
				<%
			}
		%>
		 <h2><span>Thêm username</span></h2>
		 <div class="modul()e-body">
			<form action="<%=request.getContextPath()%>/admin/addUser" method="post" id="frmuser">
				<p>
					<label>Username</label>
					<input type="text" name="username" value="" class="input-medium" />
				</p>
				<p>
					<label>Password</label>
					<input type="password" name="password" value="" class="input-medium" />
				</p>
				<p>
					<label>Fullname</label>
					<input type="text" name="fullname" value="" class="input-medium" />
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
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
				</fieldset>
			</form>
			<script type="text/javascript">
	        	$(document).ready(function(){
	        		$('#frmuser').validate({
	        			rules:{
	        					username:{
	        						required:true,
	        						minlength:2,
	        						maxlength:12
	        					},
	        					password:{
	        						required:true,
	        						minlength:2,
	        						maxlength:12
	        					},
	        					fullname:{
	        						required:true,
	        						minlength:2,
	        						maxlength:30
	        					}
	        				},
	        			messages:{
	        					username:{
	        						required:"<p style = 'color:red'>Tên đăng nhập không bỏ trống!</p>"
	        					},
	        					password:{
	        						required:"<p style = 'color:red'>Mật khẩu không bỏ trống!</p>"
	        					},
	        					fullname:{
	        						required:"<p style = 'color:red'>Họ và tên không bỏ trống!</p>"
	        					}
	        				}
	        		});
	        	});
        	</script>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 