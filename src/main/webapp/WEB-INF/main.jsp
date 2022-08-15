<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expenses</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<h1>Expenses Table</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Expense</th>
					<th>Vendor</th>
					<th>Amount</th>
					<th colspan="2">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="eachExpense" items="${expenseList }">
					<tr>
						<td><a href="/${eachExpense.id }">${eachExpense.expenseName }</a></td>
						<td>${eachExpense.vendor }</td>
						<td>${eachExpense.amount }</td>
						<td><a class="btn btn-success"
							href="/expenses/edit/${eachExpense.id }">Edit</a></td>
						<td>
							<form action="/expenses/${eachExpense.id }" method="post">
								<input type="hidden" name="_method" value="delete" />
								<button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<h1>Add a Expense</h1>


		<form:form action="/" method="post" modelAttribute="expense" class="form">
			<div>
				<form:label path="expenseName"> Expense </form:label>
				<form:input type="text" path="expenseName" class="form-control"/>
				<form:errors style="color: red" path="expenseName"></form:errors>
			</div>
			<div>
				<form:label path="vendor"> Vendor </form:label>
				<form:input type="text" path="vendor" class="form-control"/>
				<form:errors style="color: red" path="vendor"></form:errors>
			</div>
			<div>
				<form:label path="amount"> Amount </form:label>
				<form:input type="double" path="amount" class="form-control"/>
				<form:errors style="color: red" path="amount"></form:errors>
			</div>
			<button type="submit" class="btn btn-primary">Add</button>
		</form:form>

	</div>

</body>
</html>