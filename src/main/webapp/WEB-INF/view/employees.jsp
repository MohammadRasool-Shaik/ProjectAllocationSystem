<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasRole('NEWEMP')">
  <input type="hidden" id="createEmployeeAction" value="${baseURL}/empm/employeeactions?action=save"/> 
</security:authorize>
<security:authorize access="hasRole('EDITEMP')">
	<input type="hidden" id="editEmployeeAction" value="${baseURL}/empm/employeeactions?action=update"/>
</security:authorize>
	 
<div class="body" id="EmployeeTableContainer">
</div>
	
