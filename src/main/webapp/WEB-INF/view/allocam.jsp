<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="allocationRequestTableContainer" >
</div>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<security:authorize access="hasRole('SRCHALL')">
	<input type="hidden" id="editAllocationRequestAction" value="${baseURL}/allocam/editAllocatedRequest"/> 
</security:authorize>

<security:authorize access="hasRole('NEWALL')">
	<input type="hidden" id="raiseAllocationRequestAction" value="${baseURL}/allocam/raiseAllocationRequest"/> 
</security:authorize>
<%-- 
<h3>Raise Request</h3>
<div><a href="<c:url value="allocateRequest"></c:url>">Allocate Request</a></div>
<div class="displayTable">
	<form method="POST" action="raiseRequest">
		<div class="displayRow">
			<div class="displayCell"> <label>Mail Id </label></div>  <div class="displayCell"><input type="email" name="emailIds"><!-- <input type="email" name="emailIds"><input type="email" name="emailIds"> --></div>  
		</div> 
		<div class="displayRow"> 
			<div class="displayCell"> <label>Subject </label></div>  <div class="displayCell"><input type="text" name="subject"></div> 
		</div> 
		<div class="displayRow"> 
			<div class="displayCell"> <label>Message </label></div>  <div class="displayCell"><textarea name="message" rows="10" cols="100"></textarea> </div> 
		</div> 
		<div class="displayRow">
			<div class="displayCell"><input type="submit" value="Raise Request"></div>  
		</div> 
	</form>
</div> 

<a href="<c:url value="/allocam/fetchAllocationRequests"></c:url>">allocation requests</a> --%>