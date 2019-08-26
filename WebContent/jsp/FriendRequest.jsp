<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
Pending Friend Requests
<%

String[] friendRequest = {"Panku", "Jaggu", "Shubhu"};
for (int i = 0; i < friendRequest.length; i++) {
   out.print("<P>" + friendRequest[i] + "</p>");
}
%>
<%  
Cookie cookie = null;
Cookie[] cookies = null;

// Get an array of Cookies associated with the this domain
cookies = request.getCookies();
cookie = cookies[0];
if( cookies != null ) 
	{
		out.print("<h2> Welcome "+cookie.getValue()+"</h2>");
	}
   
  
%> 
</body>
</html>