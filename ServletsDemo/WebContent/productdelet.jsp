<%@page import="com.bv.handler.ProductDBHandler"%>
<%
	String p = request.getParameter("Id");
	int Id = Integer.parseInt(p);
	ProductDBHandler.deleteProducts(Id);
	response.sendRedirect("productlist.jsp");
%>