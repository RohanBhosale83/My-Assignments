<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form action="create.mm">
						<label>Enter your Name</label>
						<input type= "text" name="accHolderName">
						<br>
						<label>Enter account Balance</label>
						<input type= "number" name="accountBalance">
						<br>
						<label>Enter salaried?</label>
						<br>
						<input type="radio" name="rdSalary" value="Yes">Yes
						<input type="radio" name="rdSalary" value="No">No
						<br>
						<input type="submit" value="Submit">
						<input type="Reset" value="Clear">
						
		</form>
		<div>
		<jsp:include page="homeLink.html"></jsp:include>
		</div>
</body>
</html>