<%@page import="bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
	<%
		Category cat = new Category();
		cat = (Category)request.getAttribute("cat");
	%>
		 <div class="modul()e-body">
		 <p style="color:red">
		 	<%
		 		if(request.getAttribute("error")!=null){
		 			String error = (String)request.getAttribute("error");
		 			out.print(error);
		 		}
		 		if(request.getAttribute("messageBudle")!=null){
					boolean result = (Boolean)request.getAttribute("messageBudle");
					if(result){
						out.print("Tên danh mục không chứa ký tự đặc biệt!");
					}
			 	}	
		 	%>
		 </p>
			<form action="<%=request.getContextPath()%>/admin/editCat?cid=<%=cat.getId_cat()%>" method="post" id="frmxcat">
				<p>
					<label>Tên danh mục</label>
					<input type="text" name="ten" value="<%=cat.getName()%>" class="input-medium" />
				</p>
				<fieldset>
					<input class="submit-green" name="sua" type="submit" value="Sữa" /> 
				</fieldset>
			</form>
		 </div> <!-- End .module-body -->

	</div>  <!-- End .module -->
	<div style="clear:both;"></div>
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 