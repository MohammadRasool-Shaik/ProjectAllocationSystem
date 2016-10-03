<%@ include file="/WEB-INF/view/includes.jsp"%>
Hi <security:authentication property="name"/>
<%-- <security:authentication property="authorities"/> --%>
<a href="<c:url value="home"/>">Home </a>
<br/>
<a href="logout">logout</a>
