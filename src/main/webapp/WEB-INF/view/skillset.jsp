<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Skill Set Management</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp"%>
	<h2 align="center">SkillSet Management</h2>
		
	<c:if test="${not empty skillsEmptyMsg}">
		<security:authorize access="!hasRole('NEWSKL')">
			<div>
				<font color="Red" size="5">
					<span>${skillsEmptyMsg}</span>
				</font>
			</div>
		</security:authorize>
	</c:if>
	<table border="1" align="center">
		<tr><th>Id</th><th>Description</th><th>Skill Group</th><th>Action</th></tr>
		<c:if test="${not empty skillSetList}">
			<c:forEach var="skillSet" items="${skillSetList}">
				<tr>
				<form:form action="skillsetactions" method="POST" commandName="skillsetactions_${skillSet.skillID}">
					<td><input type="text" name="skillID" id="skillID" value="${skillSet.skillID}" readonly="readonly"/></td>
					<td><input type="text" name="description" value="${skillSet.description}"/></td>
					<td>
						<select name="groupInfo"> 
							<c:forEach items="${skillGroups}" var="skillGroup">
								<c:set var="isSeleted" value=""/>
								<c:if test="${skillGroup eq skillSet.groupInfo}">
									<c:set var="isSeleted" value="selected"/>
								</c:if>
								<option value="${skillGroup}" ${isSeleted}>${skillGroup}</option> 
							</c:forEach>
						</select>
					</td>
					<td colspan="2">
					<security:authorize access="hasRole('EDITSKL')" >
						<input type="submit" name="action" value="Edit"/>
					</security:authorize>
					<security:authorize access="hasRole('DELSKL')" >
						<input type="submit" name="action" value="Delete" />
					</security:authorize>
					</td>
				</form:form>
				</tr>
			</c:forEach>
		</c:if>
		<tr>
			<security:authorize access="hasRole('NEWSKL')">
				<form:form action="skillsetactions" method="POST" commandName="skillsetactions">
					<td><input type="text" name="skillID" /></td>
					<td><input type="text" name="description" /></td>
					<td>
						<select name="groupInfo"> 
							<c:forEach items="${skillGroups}" var="skillGroup">
								<option value="${skillGroup}">${skillGroup}</option> 
							</c:forEach>
						</select>
					</td>
					<td><input type="submit" name="action" value="Add" /></td>
				</form:form>
			</security:authorize>
		</tr>
	</table>
</body>
</html>