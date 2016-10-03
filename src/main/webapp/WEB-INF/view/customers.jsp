<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Management</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp"%>
	
	<h2 align="center">Customer Management</h2>
	<c:if test="${not empty customersEmptyMsg}">
		<security:authorize access="!hasRole('NEWCUST')" >
			<div>
				<font color="Red" size="5">
					<span>${customersEmptyMsg}</span>
				</font>
			</div>
		</security:authorize>
	</c:if>
	<table border="1" align="center">
		<tr><th>Id</th><th>Customer Name</th><th>Action</th></tr>
		<c:if test="${not empty customerList}">
			<c:forEach var="customer" items="${customerList}">
				<tr>
				<form:form action="customeractions" method="POST" commandName="customeractions${customer.customerID}">
					<td><input type="text" name="customerID" id="customerID" value="${customer.customerID}" readonly="readonly"/></td>
					<td><input type="text" name="customerName" value="${customer.customerName}"/></td>
					<td colspan="2">
					<security:authorize access="hasRole('EDITCUST')" >
						<input type="submit" name="action" value="Edit"/>
					</security:authorize>
					<security:authorize access="hasRole('DELCUST')" >
						<input type="submit" name="action" value="Delete" />
					</security:authorize>
					</td>
				</form:form>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<security:authorize access="hasRole('NEWCUST')">
				<form:form action="customeractions" method="POST" commandName="customeractions">
					<td><input type="text" name="customerID" /></td>
					<td><input type="text" name="customerName" /></td>
					<td><input type="submit" name="action" value="Add" /></td>
				</form:form>
			</security:authorize>
		</tr>
	</table>
</body>
</html>