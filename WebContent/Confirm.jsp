<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="MAC_Facility.data.RepairerDAO"%> 
<%  
String uid = request.getParameter("id");
String uname =session.getAttribute("login_username").toString();

int count = RepairerDAO.repairerResCheck(Integer.parseInt(uid));
if(count==1){
	out.print("Facility Greater Than The Current Day");
}else{
	int stat=RepairerDAO.confirmRequest(uname,Integer.parseInt(uid));
}
  
%>