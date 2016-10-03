
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Group Management</title>
</head>
<body>
<%@ include file="/WEB-INF/view/header.jsp"%>
<h2 align="center">User Group Management</h2>
	<font color="Red"> <span>${message}</span>
	</font>
	<table border="1" align="center">
		<tr><th>groupId</th><th>Group Name</th><th>Action</th></tr>
		<c:forEach items="${userGroupList}" var="group">
			<tr>
				<form:form action="usergroupactions" method="POST" commandName="usergroupactions_${group.groupId}">
					<td><input type="text" name="groupId" id="groupId" value="${group.groupId}" readonly="readonly"/></td>
					<td><input type="text" name="groupName" value="${group.groupName}"/></td>
					<td colspan="2">
					<security:authorize access="hasRole('EDITUG')" >
						<input type="submit" name="action" value="Edit"/>
					</security:authorize>
					<security:authorize access="hasRole('DELUG')" >
						<input type="submit" name="action" value="Delete" />
					</security:authorize>
					<security:authorize access="hasRole('EDITUG')" >
						<a href="fetchgrouprights?groupId=${group.groupId}">Assign Rights for ${group.groupName} Group</a>
						<%-- <input type="button" onClick="assignRights('${group.groupId}')" value="Assign Rights for ${group.groupName} Group"/> --%>
					</security:authorize>
					</td>
				</form:form>
			</tr>
		</c:forEach>
		<tr>
			<security:authorize access="hasRole('NEWUG')" >
				<form:form action="usergroupactions" method="POST" commandName="usergroupactions">
					<td><input type="text" name="groupId" /></td>
					<td><input type="text" name="groupName" /></td>
					<td><input type="submit" name="action" value="Add" /></td>
				</form:form>
			</security:authorize>
		</tr>
	</table>
	<br/>
	
	<div id="dialog">
		 
	</div>
</body>
	<script type="text/javascript">
		function assignRights(groupId){
			jQuery.ajax({
			    url: "/ProjectAllocationSystem/fetchgrouprights",
			    type: 'GET',
			    data: {
			        "groupId": groupId,
			    },
			    success:function(data){
				    	jQuery("#dialog").html(data);
				    	jQuery("#dialog" ).dialog({	
				    		maxWidth: $(window).width(),maxHeight : 300			    		
			    		});  	
			    }
			});
		 }		
	</script>
	<%@ include file="/WEB-INF/view/footer.jsp"%>
</html>
