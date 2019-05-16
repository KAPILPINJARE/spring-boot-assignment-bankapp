<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bank Account details</h1>

	<hr>
	<br>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>BankAccount Id</th>
				<th>Customer Name</th>
				<th>BankAccount type</th>
				<th>BankAccount balance</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${accounts.accountId}</td>
				<!-- this statement is internally calls the getter method -->
				<td>${accounts.getAcccoutHolderName()}</td>
				<td>${accounts.accountType}</td>
				<td>${accounts.accountBalance}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>