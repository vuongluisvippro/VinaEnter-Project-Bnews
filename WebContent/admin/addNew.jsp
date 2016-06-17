<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ModelNew"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<!-- Form elements -->    
<div class="grid_12">

	<div class="module">
		 <h2><span>Thêm tin tức</span></h2>
			
		 <div class="module-body">
			<form action="<%=request.getContextPath()%>/admin/addNew?type=them" method = "post" enctype="multipart/form-data" id="frmnew">
				<p>
					<label>Tên tin</label>
					<input type="text" name="tentin" value="" class="input-medium" />
				</p>
				<p>
					<label>Danh mục tin</label>
					<select  name="danhmuc" class="input-short">
					<%
						ArrayList<Category> alCat = (ArrayList<Category>)request.getAttribute("alCat");
						for(Category cat : alCat){
							%>
								<option value="<%=cat.getId_cat()%>"><%=cat.getName()%></option>	
						   <%
						}
					%>
					</select>
				</p>
				<p>
					<label>Hình ảnh</label>
					<input type="file"  name="hinhanh" value="" />
				</p>
				<p>
					<label>Mô tả</label>
					<textarea name="mota" value="" rows="7" cols="90" class="input-medium"></textarea>
				</p>
				<p>
					<label>Chi tiết</label>
					<textarea  name="chitiet" value="" rows="7" cols="90" class="input-long"></textarea>
				</p>
				<fieldset>
					<input class="submit-green" name="them" type="submit" value="Thêm" /> 
					<input class="submit-gray" name="reset" type="reset" value="Nhập lại" />
				</fieldset>
			</form>
			<script type="text/javascript">
	        	$(document).ready(function(){
	        		$('#frmnew').validate({
	        			rules:{
	        					tentin:{
	        						required:true,
	        						minlength:2,
	        						maxlength:255
	        					}
	        				},
	        			messages:{
	        					tentin:{
	        						required:"<p style = 'color:red'>Tên tin tức không bỏ trống!</p>",
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