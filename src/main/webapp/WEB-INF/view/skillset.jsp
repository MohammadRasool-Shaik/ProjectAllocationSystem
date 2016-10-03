<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="SkillSetTableContainer" class="body">
</div>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasRole('NEWSKL')">
  <input type="hidden" id="createSkillAction" value="${baseURL}/skillm/skillsetactions?action=save"/> 
</security:authorize>
<security:authorize access="hasRole('EDITSKL')">
	<input type="hidden" id="editSkillAction" value="${baseURL}/skillm/skillsetactions?action=update"/>
</security:authorize>
