<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="ProjectTableContainer" class="body">
</div>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasRole('NEWPROJ')">
  <input type="hidden" id="createProjectAction" value="${baseURL}/prjctm/projectaction?action=save"/> 
</security:authorize>
<security:authorize access="hasRole('EDITPROJ')">
	<input type="hidden" id="editProjectAction" value="${baseURL}/prjctm/projectaction?action=update"/>
</security:authorize>
