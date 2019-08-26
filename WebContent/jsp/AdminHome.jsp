<%@ page contentType="text/html; charset=ISO-8859-1" import="com.services.AdminService ,com.servicesImpl.AdminServiceImpl , com.beans.Admin,
java.util.Map"
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Admin mode</title>
</head>
<body>
 <%! AdminService as=new AdminServiceImpl();
int sysInfo[]=new int[2];
Admin admin=new Admin();
Map<Integer, String> toDisable=null, toDelete=null;

%>
<%
sysInfo=request.getAttribute("sysInfo");
admin=request.getAttribute("Info");
toDisable=as.getToDisable();
toDelete=as.getToDelete();
%>  
<table>
<% 
for(Map.Entry<Integer, String> entry: toDelete.entrySet())
{	
%>
<tr>
<td><button><% entry.getKey(); %></button></td>
<td></td>
</tr>
</table>

<h3>
<%

String[] onlineUsers = {"Panku", "Jaggu", "Shubhu"};
out.print(onlineUsers.length+" users online");
%>
</h3>
<h3>
Delete Users
<form action="locality" method="post">
<% 
out.print("<br>");
String[] users = {"Panku", "Jaggu", "Shubhu"};
 
for (int i = 0; i < users.length; i++) {
	out.print("<input type='button' value='delete' name='deleteUser'>");
   out.print("<P>" + users[i] + "</p>");
}
%>
</form>
<h3>
Disable Users
<%
out.print("<br>");
for (int i = 0; i < users.length; i++) {
	out.print("<input type='button' value='delete' name='disable User'>");
   out.print("<P>" + users[i] + "</p>");
}
%>
</h3>

</body>
</html>