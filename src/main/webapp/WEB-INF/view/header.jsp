
<%-- <security:authentication property="authorities"/> --%>
<%@ include file="/WEB-INF/view/includes.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/styles/projectAllocation.css"/>"/>

<!-- Include one of jTable styles. -->
<link href="<c:url value="/styles/metro/crimson/jtable.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/styles/validationEngine.jquery.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/styles/jquery-ui-1.10.3.custom.css"/>" rel="stylesheet" type="text/css" />
<div class="header" id="header">
	<span id="home"><a href="<c:url value="home"/>"><img id="home-logo" src="<c:url value="/styles/images/logo.png"/>" alt="Smiley face" > </a></span>
	<span class="logout"><a href="<c:url value="/logout"/>" style=" padding-right: 50px; " >Logout</a></span>
	<span class="logout"><a href="<c:url value="/changePassword"/>" style="padding: 30px 0px 0px 0px; ">Change Password</a></span>
	<div id="headerUserName" style="padding: 10px 10px 0px;">	Hi <security:authentication property="name"/> </div>
</div>

