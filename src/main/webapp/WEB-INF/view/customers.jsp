<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="customerTableContainer" class="body">
</div>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasRole('NEWCUST')">
  <input type="hidden" id="createCustomerAction" value="${baseURL}/custm/customeractions?action=save"/> 
</security:authorize>
<security:authorize access="hasRole('EDITCUST')">
	<input type="hidden" id="editCustomerAction" value="${baseURL}/custm/customeractions?action=update"/>
</security:authorize>
