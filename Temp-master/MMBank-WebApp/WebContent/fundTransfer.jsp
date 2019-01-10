<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="transfer.mm">
	<label>Enter Sender's Account Number</label>
	<input type= "number" name="senderAccountNumber">
	<br>
	<label>Enter Receiver's Account Number</label>
	<input type= "number" name="receiverAccountNumber">
	<br>
	<label>Enter Amount to Transfer</label>
	<input type= "number" name="amountToTransfer">
	<br>
	<input type="submit" value="Submit">
	<input type="Reset" value="Clear">
	
</form>	
<div>
		<jsp:include page="homeLink.html"></jsp:include>
</div>
</body>
</html>