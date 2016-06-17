<%@page import="model.ModelNew"%>
<%@page import="bean.New"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
<div class="leftpanel">
	<%@include file="/templates/public/inc/left_bar.jsp" %>  
</div>
<div class="rightpanel">
	<div class="rightbody">
		<%
			New newx = (New)request.getAttribute("objNew");
		%>
		<h1 class="title"><%=newx.getName()%></h1>
		<div class="items-new">
			<div class="new-detail">
				<p><%=newx.getDetail_text()%></p>
			</div>
		</div>
		
		<h2 class="title" style="margin-top:30px;color:#BBB">Tin tức liên quan</h2>
		<div class="items-new">
			<ul>
			<%
				for(New newy : new ModelNew().getList(String.valueOf(newx.getId_cat()))){
					%>
						<li>
							<h2>
								<a href="<%=request.getContextPath()%>/chi-tiet?cid=<%=newy.getId_news()%>" title="<%=newy.getName()%>"><%=newy.getName()%></a>
							</h2>
							<div class="item">
								<a href="<%=request.getContextPath()%>/chi-tiet?cid=<%=newy.getId_news()%>" title="<%=newy.getName()%>"><img src="<%=request.getContextPath()%>/files/<%=newy.getPicture()%>" alt="<%=newy.getName()%>"></a>
								<p><%=newy.getPreview_text()%></p>
								<div class="clr"></div>
							</div>
						</li>	
					<%
				}
			%>
			</ul>
		</div>
	</div>
</div>
<div class="clr"></div>
<%@include file="/templates/public/inc/footer.jsp" %>  