<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Management</title>
</head>
<body >
	<h2 align="center">User Management</h2>
	<table border="1" align="center">
		<tr><th>UserId</th><th>User Name</th><th>Email</th><th>Account Status</th><th>Is Billable</th><th>User Belongs Group</th><th>Action</th></tr>
		<c:forEach items="${usersList}" var="user">
			<tr>
				<form:form action="useractions" method="POST" commandName="useractions_${user.userId}">
					<td><input type="text" name="userId" value="${user.userId}" readonly="readonly"/></td>
					<td><input type="text" name="userName" value="${user.userName}" readonly="readonly"/></td>
					<td><input type="email" name="emailId" value="${user.emailId}"/></td>
					<security:authorize access="hasRole('UNLOCK')">
						<td>
							<select name="accountStatus">
								<option value="0" <c:if test="${user.accountStatus eq '0'}">selected</c:if>>InActive</option>
								<option value="1" <c:if test="${user.accountStatus eq '1'}">selected</c:if>>Active</option>
							</select>
						</td>
						<td>
							<select name="currentlyBilled">
								<option value="0" <c:if test="${user.currentlyBilled eq '0'}">selected</c:if>>UnBillable</option>
								<option value="1" <c:if test="${user.currentlyBilled eq '1'}">selected</c:if>>Billable</option>
							</select>
						</td>
					</security:authorize>
					<td><select name="groupId"> 
							<c:forEach items="${userGroupList}" var="userGroup">
								<c:set var="isSeleted" value=""/>
								<c:if test="${ not empty user.userGroup}">
									<c:if test="${userGroup.groupId eq user.userGroup.groupId}">
										<c:set var="isSeleted" value="selected"/>
									</c:if>
								</c:if>
								<option value="${userGroup.groupId}" ${isSeleted}>${userGroup.groupName}</option> 
							</c:forEach>
						</select>
					</td>
					<td colspan="2">
						<security:authorize access="hasRole('EDITUSER')">
							<input type="submit" name="action" value="Edit"/>
						</security:authorize>
						<security:authorize access="hasRole('DELUSER')">
							<input type="submit" name="action" value="Delete" />
						</security:authorize>
					</td>
				</form:form>
			</tr>
		</c:forEach>
	</table>
	<div align="center">
		<a  href="<c:url value="chagpwd"></c:url>">Change Password</a>
	</div>
	<br/>
	
</body>
</html>