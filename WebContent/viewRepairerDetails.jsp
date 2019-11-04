<%@ page language="java" contentType="text/html; charset=ISO-8859-1" session="false"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
a {
  background-color: blue;
  color: white;
  padding: 1em 1.5em;
  text-decoration: none;
  text-transform: uppercase;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Repairer Details</title>
</head>
<body>
<h1>Repairer Details</h1>
<body>

    <form action="/Mac_Facility/RepairerDetailController" method="get">
	  <input type="submit" class="btn btn-primary col-md-3" role="button" value="Views Details">
	</form>
	<br>
	
	<% 
		String uname=request.getParameter("userId"); 
		HttpSession session = request.getSession();

		session.setAttribute("repairId",uname); 
%>
	

<%--    <table border="1" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Facility Type</th> 
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Facility Name</th> 
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Start Date</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Start Time</th>
				<th class="myTableHead" style="padding-right: 10px; text-align: center">Floor</th>
	
				
				
			</tr>
			
			<tr class="myTableRow">
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.facility_type}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.facility_name}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.startDate}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.startTime}" /></td>
			<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.floor}" /></td>
			
			
						
		</c>
 		
 </table> --%>
 
 <br><br>
  <table border="1" class="myTable"> 
  <tr>
    <th>Facility Type</th>
	<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.facility_type}" /></td>
  </tr>
  <tr>
    <th>Facility Name</th>
	<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.facility_name}" /></td>
  </tr>
  <tr>
    <th>Start Date</th>
	<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.startDate}" /></td>
  </tr>
   <tr>
    <th>Start Time</th>
	<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.startTime}" /></td>
  </tr>
   <tr>
    <th>Floor</th>
	<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.floor}" /></td>
  </tr>
  <tr>
    <th>Wing</th>
	<td class="myTableCell" style="padding-right: 10px; text-align: center""><c:out value="${repairerDetail.wing}" /></td>
  </tr>
</table>
 
 
 <br/>
 <br/>
 <br/> 
  
</body>
</html>