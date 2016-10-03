<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="UserTableContainer" class="body">
</div>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasRole('EDITUSER')">
  <input type="hidden" id="editUserAction" value="${baseURL}/usraccm//useractions?action=update"/> 
</security:authorize>
