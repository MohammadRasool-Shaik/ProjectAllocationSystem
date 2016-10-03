<div class="body">
	<h2 align="center">Change Password</h2>
	<form class="displayTable" id="changePasswordForm" method="POST" action="changePassword">
		<div class="displayRow">
			<div class="displayCell"> <label>User Name </label></div> <div class="displayCell"><input type="text" readonly="readonly" name="userName" value="${user.userName}"></div>
		</div>
		<div class="displayRow"> 
			<div class="displayCell"> <label>Old Password </label></div> <div class="displayCell"><input type="password" name="password"></div>
		</div>
		<div class="displayRow"> 
			<div class="displayCell"> <label>New Password </label></div> <div class="displayCell"><input type="password" id="newPassword" name="newPassword"" name="newPassword"></div>
		</div>
		<div class="displayRow"> 
			<div class="displayCell"> <label>Confirm New Password </label></div> <div class="displayCell"><input type="password" name="cnewPassword"></div>
		</div>
		<div class="displayRow">
			<div class="displayCell"><input type="submit" value="Change Password"></div> 
		</div>
	</form>
</div>