<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="menu">
	 <c:if test="${empty modules}">
		<div><font color="Red" size="5"> <span>"No rights assigned",  Contact administrator</span></font></div>
	</c:if>
	<ul>
	<c:forEach var="moduleOrder" items="${modules}">
		<c:if test="${not empty moduleOrder}">
			<li id="${moduleOrder.moduleId}">
				<a href="${fn:toLowerCase(moduleOrder.moduleId)}"> ${moduleOrder.moduleName}</a>
			</li>
		</c:if>
	</c:forEach>
			<li><a href="<c:url value="loadinitialData"></c:url>">Load Module and Operations Init Data</a></li>
	</ul>
</div>

