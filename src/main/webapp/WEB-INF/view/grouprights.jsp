<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/multiselect.css"/>"/>
<form class="assignRights-content" id="applyRightsToGroupForm" action="applyRightsToGroup" method="POST">
	<div id="closePopupAssignRights" style="margin-bottom: 20px; ">close</div>
	<div  id="assignRightsTitle" >You are applying Rights for '${groupName}' Group</div>
	<label for="groupName" style="margin-bottom: 10px; ">Group Name<input type="text" id="groupName" name="groupName" value="${groupName}" /></label>
	<select id="applyRightsSelect" multiple="multiple" name="operations">
		<c:forEach var="operationByModule" items="${operationsByGroup}">
			<optgroup label="${operationByModule.key}"> 
				<c:forEach var="operation" items="${operationByModule.value}">
					<c:set var="groupId" value="${groupId}"></c:set>
						<option value="${operation.operationId}" ${operation.isChecked}>${operation.description}</option>
				</c:forEach>
			</optgroup> 
		</c:forEach>
	</select>
	<input type="hidden" name="groupId" value="${groupId}" />
	<!-- <input type="hidden" name="operations" id="operations" value="" /> -->
 	
 	<input type="button" id="applyRights" value="Save" style=" margin-top: 10px; float: right;" /> 
</form>

<script type="text/javascript" src="<c:url value="/scripts/projectAllocation.js" />"></script>