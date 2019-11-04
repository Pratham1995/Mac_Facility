<html>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="MAC_Facility.model.Repairer"  %> 
<%@page import="MAC_Facility.data.RepairerDAO"%> 
<%@page import="java.util.*" %>
  
<%  
String fcType = request.getParameter("fctype");
String fcName = request.getParameter("fcname");
String stDate = request.getParameter("stdate");
String stTime = request.getParameter("sttime");

System.out.println("stDate:::"+stDate+"  "+"stTime:::"+stTime);

List<Repairer> list=RepairerDAO.viewRepairer(fcType,fcName,stDate,stTime);  
request.setAttribute("list",list);
%> 
	<table border="1">  
	<tr>
	<th>Id</th>
	<th>Facility Type</th>
	<th>Facility Name</th>
	<th>Start Date</th>  
	<th>Start Time</th>
	<th>Floor</th>
	<th>Wing</th>
	<th>Confirm</th>
	<th>Cancel</th>
	</tr>  
	
	<c:forEach items="${list}" var="u">  
	<tr>
	<td>${u.getId()}</td>
	<td>${u.getFacility_type()}</td>
	<td>${u.getFacility_name()}</td>
	<td>${u.getStartDate()}</td>
	<td>${u.getStartTime()}</td>
	<td>${u.getFloor()}</td>
	<td>${u.getWing()}</td>
	<td><a href="Confirm.jsp?id=${u.getId()}">Confirm</a></td>
	<td><a href="cancelReq.jsp?id=${u.getId()}">Cancel</a></td>
	</tr>  
	</c:forEach>
	</table>
		<a href="RequestReservation.jsp">Back</a> 
	</body>
</html>
