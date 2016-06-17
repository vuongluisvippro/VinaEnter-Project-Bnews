<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath()%>/admin/addCat" class="button">
			<span>Thêm danh mục <img src="<%=request.getContextPath()%>/templates/admin/images/plus-small.gif" alt="ThÃªm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách danh mục</span></h2>
		<%
			ArrayList<Category> alCat = new ArrayList<Category>();
			alCat = (ArrayList<Category>)request.getAttribute("alCat");
		%>
		<%
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("add1")){
					out.print("<p style=\"color:green;margin:5px\">Thêm danh mục thành công</p>");
				}else if(msg.equals("add0")){
					out.print("<p style=\"color:green;margin:5px\">Thêm thất bại.Vui lòng thử lại</p");
				}
			}
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("edit1")){
					out.print("<p style=\"color:green;margin:5px\">Cập nhật thông tin danh mục thành công</p>");
				}else if(msg.equals("edit0")){
					out.print("<p style=\"color:green;margin:5px\">Cập nhật thất bại.Vui lòng thử lại</p");
				}
			}
		
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("del1")){
					out.print("<p>Xóa danh mục thành công</p>");
				}else if(msg.equals("del0")){
					out.print("<p>Xóa thất bại.Vui lòng thử lại</p>");
				}
			}
		%>
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>Tên</th>
						<th style="width:20%">Danh mục</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(Category cat : alCat){
						%>
							<tr>
								<td class="align-center"><%=cat.getId_cat()%></td>
								<td><a href=""><%=cat.getName()%></a></td>
								<td align="center">
									<a href="<%=request.getContextPath()%>/admin/editCat?cid=<%=cat.getId_cat()%>">Sữa<img src="<%=request.getContextPath()%>/templates/admin/images/pencil.gif" alt="edit" /></a>
									<a onClick = "return confirm('Bạn có muốn xóa không ?')" href="<%=request.getContextPath()%>/admin/deleteCat?cid=<%=cat.getId_cat()%>">Xóa<img src="<%=request.getContextPath()%>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
								</td>
							</tr>
						<%
					}
				%>
				</tbody>
			</table>
			</form>
		 </div> <!-- End .module-table-body -->
	</div> <!-- End .module -->
		 <div class="pagination">           
			<div class="numbers">
				<span>Trang:</span> 
				<a href="">1</a> 
				<span>|</span> 
				<a href="">2</a> 
				<span>|</span> 
				<span class="current">3</span> 
				<span>|</span> 
				<a href="">4</a> 
				<span>|</span> 
				<a href="">5</a> 
				<span>|</span> 
				<a href="">6</a> 
				<span>|</span> 
				<a href="">7</a>
				<span>|</span> 
				<a href="">8</a> 
				<span>|</span> 
				<a href="">9</a>
				<span>|</span> 
				<a href="">10</a>   
			</div> 
			<div style="clear: both;"></div> 
		 </div>
	
</div> <!-- End .grid_12 -->
<%@include file="/templates/admin/inc/footer.jsp" %> 