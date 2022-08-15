<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Travels</title>
</head>
<body>
<h1>Dashboard</h1>
<a href="/expenses/add"> Add a Expense</a>
<table>
	<thead>
		<tr>
			<th> Expense </th>
			<th> Vendor </th>
			<th> Amount </th>
			<th colspan="2"> Actions </th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="eachExpense" items="${expenseList }">
			<tr>
				<td> <a href="/expenses/${eachExpense.id }" >${eachExpense.expenseName }</a></td>
				<td> ${eachExpense.vendor }</td>
				<td> ${eachExpense.amount }</td>
				<td> <a href="/expenses/edit/${eachExpense.id }" >Edit</a></td>
				<td> 
					<form action="/expenses/${eachExpense.id }" method="post">
						<input type="hidden" name="_method" value="delete" />
						<button type="submit"> Delete</button>
					</form>
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
</table>
</body>
</html>