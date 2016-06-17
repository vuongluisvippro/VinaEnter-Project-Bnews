<%@page import="bean.User"%>
<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<div class="bottom-spacing">
	  <!-- Button -->
	  <div class="float-left">
		  <a href="<%=request.getContextPath()%>/admin/addUser" class="button">
			<span>Thêm User <img src="<%=request.getContextPath()%>/templates/admin/images/plus-small.gif" alt="ThÃªm tin"></span>
		  </a>
	  </div>
	  <div class="clear"></div>
</div>

<div class="grid_12">
	<!-- Example table -->
	<div class="module">
		<h2><span>Danh sách người dùng</span></h2>
		<%
			ArrayList<User> alUser = new ArrayList<User>();
			alUser = (ArrayList<User>)request.getAttribute("alUser");
		%>
		<%
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("add1")){
					out.print("<p>Thêm thông tin người dùng thành công</p>");
				}else if(msg.equals("add0")){
					out.print("<p>Thêm thông tin thất bại.Vui lòng thử lại</p");
				}
			}
		
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("edit1")){
					out.print("<p>Cập nhật thông tin người dùng thành công</p>");
				}else if(msg.equals("edit0")){
					out.print("<p>Cập nhật thông tin thất bại.Vui lòng thử lại</p");
				}
			}
		
			if(request.getParameter("msg")!=null){
				String msg = request.getParameter("msg");
				if(msg.equals("del1")){
					out.print("<p>Xóa thông tin người dùng thành công</p>");
				}else if(msg.equals("del0")){
					out.print("<p>Xóa thông tin thất bại.Vui lòng thử lại</p>");
				}
			}
		%>
		<div class="module-table-body">
			<form action="">
			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th style="width:4%; text-align: center;">ID</th>
						<th>Username</th>
						<th style="width:40%">Fullname</th>
						<th style="width:20%">Thao tác</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(User user : alUser){
						%>
							<tr>
								<td class="align-center"><%=user.getId_user()%></td>
								<td><a href="<%=request.getContextPath()%>/admin/editUser?cid=<%=user.getId_user()%>"><%=user.getUsername()%></a></td>
								<td><%=user.getFullname()%></td>
								<td align="center">
									<a href="<%=request.getContextPath()%>/admin/editUser?cid=<%=user.getId_user()%>">Sữa<img src="<%=request.getContextPath()%>/templates/admin/images/pencil.gif" alt="edit" /></a>
								<%
									User objUserx = (User)session.getAttribute("objUser");
									if("admin".equals(objUserx.getUsername())){
										%>
											<a onClick = "return confirm('Bạn có thực sự xóa không ?')" href="<%=request.getContextPath()%>/admin/deleteUser?cid=<%=user.getId_user()%>">Xóa<img src="<%=request.getContextPath()%>/templates/admin/images/bin.gif" width="16" height="16" alt="delete" /></a>	
										<%
									}
								%>
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