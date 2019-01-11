<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="depositAmount.mm">
						<label>Enter Account Number</label>
						<input type= "number" name="accountNumber">
						<br>
						<label>Enter Amount to Deposit</label>
						<input type= "number" name="amountToDeposit">
						<br>
						<input type="submit" value="Submit">
						<input type="Reset" value="Clear">		
		</form>
		<div>
		<jsp:include page="homeLink.html"></jsp:include>
		</div>
</body>
</html>