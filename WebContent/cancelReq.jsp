<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="MAC_Facility.data.RepairerDAO"%> 
<%  
String uid = request.getParameter("id");
System.out.println("uid::"+uid);

int stat=RepairerDAO.cancelRequest(Integer.parseInt(uid));  
System.out.println("stat:::"+stat);
%>