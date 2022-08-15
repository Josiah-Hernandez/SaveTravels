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
<title> Edit Page </title>
</head>
<body>
	<h1> Edit Your Expense </h1>
	<form:form action="/expenses/edit/${expense.id }" method="post" modelAttribute="expense">
	<input type="hidden" name="_method" value="put" />
	<div>
		<form:label path="expenseName"> Expense </form:label>
		<form:input type="text" path="expenseName" />
		<form:errors style="color: red" path="expenseName"></form:errors>
	</div>
		<div>
		<form:label path="vendor"> Vendor </form:label>
		<form:input type="text" path="vendor" />
		<form:errors style="color: red" path="vendor"></form:errors>
	</div>
		<div>
		<form:label path="amount"> Amount </form:label>
		<form:input type="double" path="amount" />
		<form:errors style="color: red" path="amount"></form:errors>
	</div>
	<button type="submit"> Add </button>
	</form:form>
	
	
</body>
</html>