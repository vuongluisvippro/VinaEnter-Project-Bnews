<%@page import="java.util.ArrayList"%>
<%@page import="bean.New"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="templates/public/inc/header.jsp" %>
<div class="leftpanel">
	<%@include file="templates/public/inc/left_bar.jsp" %>  
</div>
<div class="rightpanel">
	<div class="rightbody">
		<h1 class="title">Tin tức</h1>
		<div class="items-new">
			<ul>
			<%
				for(New newx : (ArrayList<New>)request.getAttribute("listNew")){
					%>
						<li>
							<h2>
								<a href="<%=request.getContextPath()%>/chi-tiet?cid=<%=newx.getId_news()%>" title=""><%=newx.getName()%></a>
							</h2>
							<div class="item">
								<a href="<%=request.getContextPath()%>/chi-tiet?cid=<%=newx.getId_news()%>" title=""><img src="<%=request.getContextPath()%>/files/<%=newx.getPicture()%>" alt="" /></a>
								<p><%=newx.getPreview_text()%></p>
								<div class="clr"></div>
							</div>
						</li>	
					<%
				}
			%>
			</ul>
			
			<div class="paginator">
			<%
				int sotrang = (Integer)request.getAttribute("sotrang");
				int current_page = (Integer)request.getAttribute("current_page");
				for(int i = 1; i <= sotrang; i++){
					if(current_page == i){
						%>
							<a href="<%=request.getContextPath()%>/tin-tuc?page=<%=i%>" class="active">Trang <%=i%></a> |
						<%
					}else{
						%>
							<a href="<%=request.getContextPath()%>/tin-tuc?page=<%=i%>">Trang <%=i%></a> |
						<%	
					}
				}
			%>
			</div>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="templates/public/inc/footer.jsp" %>  		
			
