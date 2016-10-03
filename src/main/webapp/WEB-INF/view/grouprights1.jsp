<%@ include file="/WEB-INF/view/includes.jsp"%>


<link rel="stylesheet" type="text/css" href="<c:url value="/styles/jquery.multiselect.css "/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/prettify.css "/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/style.css "/>"/>


<%-- <h3 align="center">For ${groupId} group, Rights/Operations Management</h3> --%>
<form:form commandName="applyRightsToGroup" action="applyRightsToGroup" method="GET">
	<select name="operationByGroup" multiple="multiple" size="10">
	<c:forEach var="operationByModule" items="${operationsByGroup}">
		<optgroup label="${operationByModule.key}"> 
			<c:forEach var="operation" items="${operationByModule.value}">
				<c:set var="groupId" value="${groupId}"></c:set>
				<option value="${operation.operationId}" ${operation.isChecked}>${operation.description}</option>
				<%-- <input type="checkbox" id="${operation.operationId}" value="${operation.operationId}" ${operation.isChecked}>
				<label for="${operation.operationId}">${operation.description}</label> --%>
			</c:forEach>
		</optgroup>
	</c:forEach>
	</select>
	<br />
	<input type="hidden" name="groupId" value="${groupId}" />
	<input type="hidden" name="operations" id="operations" value="" />
	<br />
	<c:if test="${not empty operationsByGroup}">
	<input type="submit" value="Apply Rights" onClick="applyRightsToGroup();" />
	</c:if>
</form:form>

<script type="text/javascript">
	/* This script Done by RaSh:Rasool */
	
	function applyRightsToGroup() {
		var operations;
		jQuery('input:checked').each(function() {
			if (operations != undefined) {
				operations = operations + "|" + jQuery(this).attr('id');
			} else {
				operations = jQuery(this).attr('id');
			}
		});
		jQuery('#operations').val(operations);
	}
	
	jQuery(function(){
		jQuery("select").multiselect();
	});
</script>

<script type="text/javascript" src="<c:url value="/scripts/jquery.multiselect.js" />"></script>
<script type="text/javascript" src="<c:url value="/scripts/prettify.js" />"></script>