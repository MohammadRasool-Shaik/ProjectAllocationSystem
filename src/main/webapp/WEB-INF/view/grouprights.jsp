<%@ include file="/WEB-INF/view/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Rights Management</title>
</head>
<body>
	<h2 align="center">For ${groupId} group, Rights/Operations Management</h2>
	<form:form commandName="applyRightsToGroup" action="applyRightsToGroup" method="GET">
		<c:forEach var="operationByModule" items="${operationsByGroup}">
			<table border=1>
				<tr>
					<th>${operationByModule.key}</th>
				</tr>
				<c:forEach var="operation" items="${operationByModule.value}">
					<c:set var="groupId" value="${groupId}"></c:set>
					<tr>
						<td><input type="checkbox" id="${operation.operationId}"
							value="${operation.operationId}" ${operation.isChecked}>
							<label for="${operation.operationId}">${operation.description}</label>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
		<br />
		<input type="hidden" name="groupId" value="${groupId}" />
		<input type="hidden" name="operations" id="operations" value="" />
		<br />
		<input type="submit" value="Apply Rights"
			onClick="applyRightsToGroup();" />
	</form:form>
<%@ include file="/WEB-INF/view/footer.jsp"%>
</body>
</html>

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
</script>