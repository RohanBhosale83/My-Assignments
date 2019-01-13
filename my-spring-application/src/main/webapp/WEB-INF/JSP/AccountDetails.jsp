<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action ="updateAcc">
	<table>
			
		<tr>
			<th><a href="sortAccountById">Account Number</a></th>
			<th><a href="sortByName">Holder Name</a></th>
			<th>Account Balance</th>
			<th><a href="sortAccountByIsSalried"> Salary </a></th>
			<th>Over Draft Limit</th>
			<th>Type Of Account</th>
		</tr>
		<jstl:if test="${account!=null}">
		
			<tr>
				<td><input type="text" name="accountNumber" value ="${account.bankAccount.accountNumber}" readonly></td>
				<td><input type="text" name="newName" value ="${account.bankAccount.accountHolderName}"></td>
				<td><input type="text" name="accountBalance" value ="${account.bankAccount.accountBalance}" readonly></td>
				<td>
					<select name="newSalaried">
						<option selected>${account.salary==true?"Yes":"No"}</option>		<!-- value in database will be seen selected -->
						<option>${account.salary==true?"No":"Yes"}</option>				<!--  opposite to value in database will be shown as a option -->
					</select>
				</td>
				<td>${"N/A"}</td>
				<td><input type="text" name="accountType" value ="Saving" readonly></td>
			</tr>
				<input type = "submit" value = "update">	
		</jstl:if>
	
	
		
		<jstl:if test="${accounts!=null}">
			<jstl:forEach var="account" items="${accounts}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td>${account.salary==true?"Yes":"No"}</td>
					<td>${"N/A"}</td>
					<td><input type="text" name ="typeOfAccount" value ='${"Savings"}' readonly></td>
				</tr>
			</jstl:forEach>
		</jstl:if>
	</table>
	<div>
		<jsp:include page="homeLink.html"></jsp:include>
	</div> 
</form>	
</body>
</html>







