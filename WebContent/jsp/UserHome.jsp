<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>UserHome</title>
</head>
<!-- <body> -->
<%  
Cookie cookie = null;
Cookie[] cookies = null;

// Get an array of Cookies associated with the this domain
cookies = request.getCookies();

if( cookies != null ) {
   out.println("<h2> Found Cookies Name and Value</h2>");
   
   for (int i = 0; i < cookies.length; i++) {
      cookie = cookies[i];
      out.print("Name : " + cookie.getName( ) + ",  ");
      out.print("Value: " + cookie.getValue( )+" <br/>");
   }
} else {
   out.println("<h2>No cookies founds</h2>");
} 
%>  

<div class="topnav">
<a class="active" href="#home">Home</a>
  <a href="FriendRequest.jsp">Friend Request</a>
  <input type="text" placeholder="Search..">
</div>


</body>
<body>





</body>
</html>