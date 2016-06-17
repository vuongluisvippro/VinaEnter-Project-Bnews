<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		<%
			
		%>
		 <h2><span>Thêm danh mục</span></h2>
		 <p style="color:red; font-style:bold">
		 <%
		 if(request.getAttribute("error") != null){
				String msg = (String)request.getAttribute("error");
				out.print(msg);
		 }
		 if(request.getAttribute("messageBudle")!=null){
			 boolean result = (Boolean)request.getAttribute("messageBudle");
			 if(result){
				 out.print("Tên danh mục không chứa ký tự đặc biệt!");
			 }
		 }	 
		 %>
		 </p>
		 <div class="modul()e-body">
			<form action="<%=request.getContextPath()%>/admin/addCat" method="post" id="frmcat">
				<p>
					<label>Tên danh mục</label>
					<input type="text" name="ten" value="" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
				</fieldset>
			</form>
			<script type="text/javascript">
	        	$(document).ready(function(){
	        		$('#frmcat').validate({
	        			rules:{
	        					ten:{
	        						required:true,
	        						minlength:2,
	        						maxlength:12
	        					}
	        				},
	        			messages:{
	        					ten:{
	        						required:"<p style = 'color:red'>Tên danh mục không bỏ trống!</p>",
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