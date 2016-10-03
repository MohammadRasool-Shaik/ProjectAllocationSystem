<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Allocation System</title>
<%@ include file="/WEB-INF/view/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
</head>
<body>
	<br/>
	<br/>
	
	<h4><a href="<c:url value="loadinitialData"></c:url>">Load Module and Operations Init Data</a></h4>
	
	
	<c:if test="${empty modules}">
		<div><font color="Red" size="5"> <span>"No rights assigned",  Contact administrator</span></font></div>
	</c:if>
	<c:forEach var="module" items="${modules}">
		<div>
			<a href="${fn:toLowerCase(module.moduleId)}"> ${module.moduleName}</a> 
		</div>
	</c:forEach>
	
	<h3>Raise Request</h3>
	<table>
		<form:form commandName="raiseRequest" method="POST" action="raiseRequest">
			<tr>
				<td> <label>Mail Id </label></td> <td><input type="email" name="emailIds"><!-- <input type="email" name="emailIds"><input type="email" name="emailIds"> --></td> 
			</tr>
			<tr> 
				<td> <label>Subject </label></td> <td><input type="text" name="subject"></td>
			</tr>
			<tr> 
				<td> <label>Message </label></td> <td><textarea name="message" rows="10" cols="100"></textarea> </td>
			</tr>
			<tr>
				<td><input type="submit" value="Raise Request"></td> 
			</tr>
		</form:form>
	</table>
</body>
</html>

