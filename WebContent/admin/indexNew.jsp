<%@page import="java.io.File"%>
<%@page import="bean.New"%>
<%@page import="bean.User"%>
<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath()%>/admin/addNew?type=load" class="button">
			<span>Thêm Tin tức <img src="<%=request.getContextPath()%>/templates/admin/images/plus-small.gif" alt="ThÃªm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách tin tức</span></h2>
		<%
			ArrayList<New> alNew = new ArrayList<New>();
			alNew = (ArrayList<New>)request.getAttribute("alNew");
		%>
		<%
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("add1")){
					out.print("<p>Thêm mới tin tức thành công</p>");
				}else if(msg.equals("add0")){
					out.print("<p>Thêm mới tin tức thất bại.Vui lòng thử lại</p");
				}
			}
		
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("edit1")){
					out.print("<p>Cập nhật tin tức thành công</p>");
				}else if(msg.equals("edit0")){
					out.print("<p>Cập nhật tin tức thất bại.Vui lòng thử lại</p");
				}
			}
		
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("del1")){
					out.print("<p>Xóa tin tức thành công</p>");
				}else if(msg.equals("del0")){
					out.print("<p>Xóa tin tức thất bại.Vui lòng thử lại</p>");
				}
			}
		%>
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:5%; text-align: center;">ID</th>
						<th style="width:40%">Tên</th>
						<th style="width:15%">Danh Mục</th>
						<th style="width:5%">Hình ảnh</th>
						<th style="width:20%">Chức năng</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(New newx : alNew){
						%>
							<tr>
								<td class="align-center"><%=newx.getId_news()%></td>
								<td><a href=""><%=newx.getName()%></a></td>
								<td><%=newx.getName_cat()%></td>
								<td><img src="<%=request.getContextPath()%>/files/<%=newx.getPicture()%>" alt ="" width="100px" height="100px"></td>
								<td align="center">
									<a href="<%=request.getContextPath()%>/admin/editNew?cid=<%=newx.getId_news()%>">Sữa<img src="<%=request.getContextPath()%>/templates/admin/images/pencil.gif" alt="edit" /></a>
									<a onClick = "return confirm('Bạn có thực sự xóa không ?')" href="<%=request.getContextPath()%>/admin/deleteNew?cid=<%=newx.getId_news()%>">Xóa<img src="<%=request.getContextPath()%>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>
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