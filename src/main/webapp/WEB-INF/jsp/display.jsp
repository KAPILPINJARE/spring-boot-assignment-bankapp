<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body>
	<h1>Bank Account details</h1>
	
	<hr>
	<br><br>
		<table border ="1">
			<thead>
				<tr>
					<th>BankAccount Id</th>
					<th>Customer Name</th>
					<th>BankAccount type</th>
					<th>BankAccount balance</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>${account.accountId}</td> <!-- this statement is internally calls the getter method -->
						<td>${account.getAcccoutHolderName()}</td>
						<td>${account.accountType}</td>
						<td>${account.accountBalance}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/BankAppSpringMVC/index.html">Home</a>
</body>
</html>