var attempt = 3; // Variable to count number of attempts.

// Below function Executes on click of login button.
function validate()
{
	var username = document.getElementByName("adminUserame").value;
	var password = document.getElementByName("adminPassword").value;
	if ( username != null  && password !=null)
	{
		//alert ("Login successfully");
		window.location = "AdminLogin.html"; // Redirecting to other page.
		return false;
	}
	else
	{
		attempt --;// Decrementing by one.
		alert("You have left "+attempt+" attempt;");
		// Disabling fields after 3 attempts.
		if( attempt == 0)
		{
		document.getElementByName("adminUsername").disabled = true;
		document.getElementByName("adminPassword").disabled = true;
		document.getElementByName("submit").disabled = true;
		return false;
		}
	}
}