<%@page import="bean.New"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
				<div class="leftpanel">
					<%@include file="/templates/public/inc/left_bar.jsp" %>  
				</div>
				<div class="rightpanel">
					<div class="rightbody">
						<%
							Category catx = new Category();
							catx = (Category)request.getAttribute("objCat");
						%>
						<h1 class="title">Tin tức >> <%=catx.getName()%></h1>
						<div class="items-new">
							<ul>
							<%
								ArrayList<New> listNews = (ArrayList<New>)request.getAttribute("listNews");
								for(New newx : listNews){
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
								<a href="">Trang 1</a> | 
								<a href="" class="active">Trang 2</a> |
								<a href="">Trang 3</a> |
								<a href="">Trang 4</a>
							</div>
						</div>
					</div>
				</div>
				<div class="clr"></div>
<%@include file="/templates/public/inc/footer.jsp" %>  	