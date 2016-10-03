jQuery().ready(function () {
	$('#SkillSetTableContainer').jtable('load');
	$('#EmployeeTableContainer').jtable('load');
	$('#UserTableContainer').jtable('load');
	$('#userGroupTableContainer').jtable('load');
	$('#customerTableContainer').jtable('load');
	$('#ProjectTableContainer').jtable('load');
	$('#allocationRequestTableContainer').jtable('load');
	$('.jtable-toolbar-item-text').html("Raise Allocation Request");
    jQuery('#startDate').datepicker({
        dateFormat: "yy-mm-dd",
        maxDate: 30
    });
    jQuery('#endDate').datepicker({
        dateFormat: "yy-mm-dd",
        minDate: 0
    });
    
    
});

function showAlert(msg) {
    alert(msg);
}

function getDateObj(date) {
    var year = date.split("-")[0];
    var month = date.split("-")[1];
    var day = date.split("-")[2];
    return new Date(year, month - 1, day);
}

function isEndDateAfterStartDate(startDate, endDate) {
    var startDateObj = getDateObj(startDate);
    var endDateObj = getDateObj(endDate);
    if (endDateObj.getTime() >= startDateObj.getTime()) {
        return true;
    }
    alert("Please enter an end date after the start date");
    return false;
}

/* popup JS */
jQuery('.popup-bg').click(function () {
    //	jQuery('.popup-bg').hide();
});

jQuery('.popup-content').click(function () {
    //	return false;
});
jQuery('#closePopup').click(function () {
    jQuery('.popup-bg').hide();
});



function showPopUp() {

    var docHeight = jQuery(document).height();

    jQuery('.popup-bg').show().css({
        'height': docHeight
    });

    var divHeight = jQuery('.popup-content').height();
    var divTop = ((docHeight / 2) - (divHeight / 2));

    jQuery('.popup-content').css({
        'top': divTop
    });
}

/* changepwd popup JS */
jQuery('#changepwd-bg').click(function () {
    jQuery('#changepwd-bg').hide();
});

jQuery('.changepwd-content').click(function () {
    return false;
});

/* user group Related JS */
function assignRights(groupInfo) {
	var groupArray=groupInfo.split("~");
	groupId=groupArray[0];
	groupName=groupArray[1];
	
	//var groupId = jQuery(this).prevAll('.groupId').text();
//    var groupId = jQuery(this).attr('id');
    jQuery.ajax({
        url: "/ProjectAllocationSystem/fetchgrouprights",
        type: 'GET',
        async: true,
        data: {
            "groupId": groupId,
            "groupName" : groupName
        },
        success: function (data) {
            jQuery("#assignRights-bg").html(data);
            jQuery('#applyRightsSelect').multiSelect({
                selectableOptgroup: true,
                selectableHeader: "<div style='font-size:large;' class='custom-header'>Available Rights</div>",
                selectionHeader: "<div style='font-size:large;' class='custom-header'>Assigned Rights</div>"
            });
            
            showAssignRightsPopUp();
        },
        error: function () {
            alert('Something went Wrong, catch you soon');
        }
    });
}

function showAssignRightsPopUp() {

    var docHeight = jQuery(document).height();

    jQuery('#assignRights-bg').show().css({
        'height': docHeight
    });

    var divHeight = jQuery('.assignRights-content').height();
    var divTop = ((docHeight / 2) - (divHeight / 2));

    jQuery('.assignRights-content').css({
        'top': divTop
    });
    
       
    /*
     * var multiGroupDivHeight = jQuery('.ms-container').height();
     *
     * var multipGroupTop = ((divHeight / 2) - (multiGroupDivHeight / 2))-20;
     *
     * jQuery('#ms-applyRightsSelect').css({ 'top' : multipGroupTop });
     */

    jQuery('#closePopupAssignRights').click(function () {
        jQuery('#assignRights-bg').hide();
    });
}


jQuery('#applyRights').click(function () {
    jQuery.ajax({
        url: $('#applyRightsToGroupForm').attr('action'),
        type: 'POST',
        cache: false,
        data: $('#applyRightsToGroupForm').serialize(),
        success: function (data) {
            //			 var obj = JSON.parse(response);
            var msgArray = data.split(",");
            var groupId = msgArray[0];
            if (data.search('Success') != -1) {
                jQuery('#assignRights-bg').hide();
                jQuery('#userGroupTableContainer').jtable('load');
                jQuery('.groupRightsMsg_' + groupId).show().html("Updated Successfully");
            } else {
                jQuery('#assignRights-bg').hide();
                jQuery('.groupRightsMsg_' + groupId).show().html("Got Some Problem while updating Rights to group");
            }

        },
        error: function () {
            alert('Something Wrong, catch you soon');
        }
    });
});

/* Skill Management/CU(Create and Update) JS */
jQuery('.editSkill').click(function () {
    var skillId = $(this).prevAll('.skillID').text();
    var description = $(this).prevAll('.description').text();
    var groupInfo = $(this).prevAll('.groupInfo').text();
    jQuery("#skillActionForm #skillID").val(skillId);
    jQuery("#skillActionForm #skillID").attr('readOnly', 'readOnly');
    jQuery("#skillActionForm #description").val(description);
    jQuery("#skillActionForm #groupInfo").val(groupInfo);

    var action = jQuery('#UpdateSkillAction').attr('id');

    action = action.substring(0, action.indexOf('SkillAction'));

    jQuery('#updateSkillAction').val(action);

    showPopUp();
    jQuery('#skillActionForm').valid();
});

jQuery('#SaveSkillAction').click(function () {

    jQuery('#skillID').removeAttr('readonly');

    jQuery('#skillActionForm :input').each(function () {
        jQuery(this).val('');
    });

    jQuery('#groupInfo option:eq(0)').prop('selected', true);

    var action = jQuery(this).attr('id');
    action = action.substring(0, action.indexOf('SkillAction'));

    jQuery('#updateSkillAction').val(action);

    showPopUp();

});

jQuery('#updateSkillAction').click(function () {
    var actionValue = jQuery(this).val();
    jQuery('#skillAction').val(actionValue);
    jQuery('#skillActionForm').submit();
});

/* Customer Management/CU(Create and Update) JS */
jQuery('.editCustomer').click(function () {
    var customerID = $(this).prevAll('.customerID').text();
    var customerName = $(this).prevAll('.customerName').text();

    jQuery("#customerActionForm #customerID").val(customerID);
    jQuery("#customerActionForm #customerID").attr('readOnly', 'readOnly');
    jQuery("#customerActionForm #customerName").val(customerName);

    var action = jQuery('#UpdateCustomerAction').attr('id');

    action = action.substring(0, action.indexOf('CustomerAction'));

    jQuery('#updateCustomerAction').val(action);

    showPopUp();
    jQuery('#customerActionForm').valid();

});

jQuery('#SaveCustomerAction').click(function () {

    jQuery('#customerActionForm #customerID').removeAttr('readonly');

    jQuery('#customerActionForm :input').each(function () {
        jQuery(this).val('');
    });

    var action = jQuery(this).attr('id');
    action = action.substring(0, action.indexOf('CustomerAction'));

    jQuery('#updateCustomerAction').val(action);

    showPopUp();
});

jQuery('#updateCustomerAction').click(function () {
    var actionValue = jQuery(this).val();
    jQuery('#customerAction').val(actionValue);
    jQuery('#customerActionForm').submit();
});

/* Project Management/CU(Create and Update) JS */
jQuery('.editProject').click(function () {
    var projectID = $(this).prevAll('.projectID').text();
    var projectName = $(this).prevAll('.projectName').text();
    var adminSPOC = $(this).prevAll('.adminSPOC').text();
    var techSPOC = $(this).prevAll('.techSPOC').text();
    var startDate = $(this).prevAll('.startDate').text();
    var expectedEndDate = $(this).prevAll('.expectedEndDate').text();
    var customerName = $(this).prevAll('.customerName').text();

    jQuery("#projectActionForm #projectID").val(projectID);
    jQuery("#projectActionForm #projectID").attr('readOnly', 'readOnly');
    jQuery("#projectActionForm #projectName").val(projectName);
    jQuery("#projectActionForm #adminSPOC").val(adminSPOC);
    jQuery("#projectActionForm #techSPOC").val(techSPOC);
    jQuery("#projectActionForm #startDate").val(startDate);
    jQuery("#projectActionForm #endDate").val(expectedEndDate);

    jQuery("#projectActionForm #customerId option").filter(function () {
        return this.text == customerName;
    }).prop('selected', true);

    var action = jQuery('#UpdateProjectAction').attr('id');

    action = action.substring(0, action.indexOf('ProjectAction'));

    jQuery('#updateProjectAction').val(action);

    showPopUp();
    jQuery('#projectActionForm').valid();

});

jQuery('#SaveProjectAction').click(function () {

    jQuery('#projectID').removeAttr('readonly');

    jQuery('#projectActionForm :input').each(function () {
        jQuery(this).val('');
    });

    jQuery('#customerId option:eq(0)').prop('selected', true);

    var action = jQuery(this).attr('id');
    action = action.substring(0, action.indexOf('ProjectAction'));

    jQuery('#updateProjectAction').val(action);

    showPopUp();

});

function validateDates() {
    var startDate = jQuery('#Edit-startDate').val();
    var endDate = jQuery('#EditendDate').val();
    if (startDate != "" || endDate != "") {
        return isEndDateAfterStartDate(startDate, endDate);
    }
    return true;
}

jQuery('#updateProjectAction').click(function () {
    var actionValue = jQuery(this).val();
    jQuery('#projectAction').val(actionValue);
    if (validateDates()) {
        jQuery('#projectActionForm').submit();
    }
});

/* userAccount Management/CU(Create and Update) JS */
jQuery('.editUser').click(function () {
    var userId = $(this).prevAll('.userId').text();
    var userName = $(this).prevAll('.userName').text();
    var emailId = $(this).prevAll('.emailId').text();
    var accountStatus = $(this).prevAll('.accountStatus').text();
    var groupId = $(this).prevAll('.groupId').text();

    jQuery("#userActionForm #userId").val(userId);
    jQuery("#userActionForm #userId").attr('readOnly', 'readOnly');
    jQuery("#userActionForm #userName").val(userName);
    jQuery("#userActionForm #emailId").val(emailId);

    jQuery("#userActionForm #accountStatus option").filter(function () {
        return this.text == accountStatus;
    }).prop('selected', true);

    jQuery("#userActionForm #groupId option").filter(function () {
        return this.text == groupId;
    }).prop('selected', true);

    var action = jQuery('#UpdateUserAction').attr('id');

    action = action.substring(0, action.indexOf('UserAction'));

    jQuery('#updateUserAction').val(action);

    showPopUp();
    jQuery('#userActionForm').valid();

});

jQuery('#updateUserAction').click(function () {
    var actionValue = jQuery(this).val();
    jQuery('#userAction').val(actionValue);
    jQuery('#userActionForm').submit();
});

/* userGroup Management/CU(Create and Update) JS */
jQuery('.editUserGroup').click(function () {
    var groupId = $(this).prevAll('.groupId').text();
    var groupName = $(this).prevAll('.groupName').text();

    jQuery("#userGrouActionForm #groupId").val(groupId);
    jQuery("#userGrouActionForm #groupId").attr('readOnly', 'readOnly');
    jQuery("#userGrouActionForm #groupName").val(groupName);

    var action = jQuery('#UpdateUserGroupAction').attr('id');

    action = action.substring(0, action.indexOf('UserGroupAction'));

    jQuery('#updateUserGroupAction').val(action);

    showPopUp();
    jQuery('#userGrouActionForm').valid();

});

jQuery('#SaveUserGroupAction').click(function () {

    jQuery('#userGrouActionForm #groupId').removeAttr('readonly');

    jQuery('#userGrouActionForm :input').each(function () {
        jQuery(this).val('');
    });

    var action = jQuery(this).attr('id');
    action = action.substring(0, action.indexOf('UserGroupAction'));

    jQuery('#updateUserGroupAction').val(action);

    showPopUp();
});

jQuery('#updateUserGroupAction').click(function () {
    var actionValue = jQuery(this).val();
    jQuery('#userGroupAction').val(actionValue);
    jQuery('#userGrouActionForm').submit();
});

/* Employee Management/CU(Create and Update) JS */
jQuery('.editEmployee').click(function () {
    var employeeId = $(this).prevAll('.employeeId').text();
    var skillId = $(this).prevAll('.skillId').text();
    var empStatus = $(this).prevAll('.empStatus').text();
    var competency = $(this).prevAll('.competency').text();
    var projectId = $(this).prevAll('.projectId').text();
    var assignedDate = $(this).prevAll('.assignedDate').text();
    var endDate = $(this).prevAll('.endDate').text();

    jQuery("#employeeActionForm #employeeId").val(employeeId);
    jQuery("#employeeActionForm #employeeId").attr('readOnly', 'readOnly');
    jQuery("#employeeActionForm #skillId option").filter(function () {
        return this.text == skillId;
    }).prop('selected', true);
    jQuery("#employeeActionForm #empStatus option").filter(function () {
        return this.text == empStatus;
    }).prop('selected', true);

    jQuery("#employeeActionForm #competency option").filter(function () {
        return this.text == competency;
    }).prop('selected', true);
    jQuery("#employeeActionForm #projectId option").filter(function () {
        return this.text == projectId;
    }).prop('selected', true);

    jQuery("#employeeActionForm #startDate").val(assignedDate);
    jQuery("#employeeActionForm #endDate").val(endDate);

    var action = jQuery('#UpdateEmployeeAction').attr('id');

    action = action.substring(0, action.indexOf('EmployeeAction'));

    jQuery('#updateEmployeeAction').val(action);

    showPopUp();
    jQuery('#employeeActionForm').valid();

});

jQuery('#SaveEmployeeAction').click(function () {

    jQuery('#employeeId').removeAttr('readonly');

    jQuery('#employeeActionForm :input').each(function () {
        jQuery(this).val('');
    });

    jQuery('#skillId option:eq(0)').prop('selected', true);
    jQuery('#empStatus option:eq(0)').prop('selected', true);
    jQuery('#competency option:eq(0)').prop('selected', true);
    jQuery('#projectId option:eq(0)').prop('selected', true);


    var action = jQuery(this).attr('id');
    action = action.substring(0, action.indexOf('EmployeeAction'));

    jQuery('#updateEmployeeAction').val(action);

    showPopUp();

});


jQuery('#updateEmployeeAction').click(function () {
    var actionValue = jQuery(this).val();
    jQuery('#employeeAction').val(actionValue);
    if (validateDates()) {
        jQuery('#employeeActionForm').submit();
    }
});


/* validating Adding a user-group */
jQuery('#userGrouActionForm').validate({
    rules: {
        groupId: {
            required: true,
            maxlength: 10,
        },
        groupName: {
            required: true,
            maxlength: 50,
        },
    },
    messages: {
        groupId: {
            required: "Please enter your Group Id",
            maxlength: "Your user Id must less than 10 characters",
        },
        groupName: {
            required: "Please enter your Group Name",
            maxlength: "Your user Id must less than 50 characters",
        },
    }
});
/* change password popup */

jQuery('#changepwd').click(function () {
    var docHeight = jQuery(document).height();

    jQuery('#changepwd-bg').show().css({
        'height': docHeight
    });

    var divHeight = jQuery('.changepwd-content').height();
    var divTop = ((docHeight / 2) - (divHeight / 2));

    jQuery('#changepwd-bg').children('.changepwd-content').css({
        'top': divTop
    });
});

jQuery('#changepasswordButton').click(function () {
    jQuery('#changePasswordForm').validate();
    jQuery('#changePasswordForm').submit();
});

jQuery('#changePasswordForm').validate({
    rules: {
        password: "required",
        newPassword: {
            required: true,
            minlength: 5
        },
        cnewPassword: {
            required: true,
            equalTo: '#newPassword'
        }
    },
    messages: {
        password: "Please enter a password",
        newPassword: {
            required: "Please provide a New Password",
            minlength: "Your password must be at least 5 characters long"
        },
        cnewPassword: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long",
            equalTo: "Please enter the same password as above"
        }
    }
});

jQuery.validator.addMethod("valueNotEquals", function (value, element, arg) {
    return arg != value;
}, "Value must not equal arg.");

jQuery.validator.addMethod("letterswithbasicpunc", function (value, element) {
    return this.optional(element) || /^[a-z\.'"\s]+$/i.test(value);
}, "Letters or punctuation only please");

jQuery.validator.addMethod("letterswithnumbers", function (value, element) {
    return this.optional(element) || /^[a-z0-9\.'"\s]+$/i.test(value);
}, "Letters or punctuation only please");

/* validations */

/* validating Register form */
jQuery('#registertrationForm').validate({
    rules: {
        userName: {
            required: true,
            letterswithbasicpunc: true,
            maxlength: 50
        },
        userId:{
        	required: true,
        	letterswithnumbers: true,
        	maxlength: 10
        },
        emailId: {
            required: true,
            email: true
        },
        password: {
            required: true,
            minlength: 5
        },
        cpassword: {
            required: true,
            minlength: 5,
            equalTo: "#password"
        }
    },
    messages: {
        userName: {
            required: "Please enter a username",
            maxlength: "Your user Id must less than 10 characters"
        },
        emailId: {
            required: "Please enter your email",
            email: "Please enter a valid email address"
        },
        password: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
        cpassword: {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long",
            equalTo: "Please enter the same password as above"
        }
    },
    submitHandler: function (form) {
        var valid = jQuery('#validUser').attr("class");
        if (valid == "false") {
            return false;
        } else {
            form.submit();
        }

    }
});

/* validating Register form */
jQuery('#loginForm').validate({
    rules: {
        j_username: "required",
        j_password: "required"
    },
    messages: {
        j_username: "Please enter a username",
        j_password: "Please provide a password"
    }
});

jQuery('#customerActionForm').validate({
    rules: {
        customerID: {
            required: true,
            maxlength: 10
        },
        customerName: {
            required: true,
            maxlength: 50
        }
    },
    messages: {
        customerID: {
            required: "Please enter your customer Id",
            maxlength: "Your customer Id must less than 10 characters"
        },
        customerName: {
            required: "Please enter your customer Name",
            maxlength: "Your customer Name must less than 50 characters"
        }
    },

    submitHandler: function (form) {
        form.submit();
    }
});

jQuery('#skillActionForm').validate({
    rules: {
        skillID: {
            required: true,
            maxlength: 10
        },
        description: {
            required: true,
            maxlength: 50
        },
        groupInfo: {
            valueNotEquals: "default"
        }
    },
    messages: {
        skillID: {
            required: "Please enter your Skill Id",
            maxlength: "Your Skill Id must less than 10 characters"
        },
        description: {
            required: "Please enter your Skill Description",
            maxlength: "Your skill description must less than 50 characters"
        },
        groupInfo: {
            valueNotEquals: "Please select a skill group"
        }
    }
});

jQuery('#projectActionForm').validate({
    rules: {
        projectID: {
            required: true,
            maxlength: 10
        },
        projectName: {
            required: true,
            maxlength: 50
        },
        startDate: {
            required: true
        },
        expectedEndDate: {
            required: true
        },
        customerId: {
            valueNotEquals: "default"
        }
    },
    messages: {
        projectID: {
            required: "Please enter your Project Id",
            maxlength: "Your Project Id must less than 10 characters"
        },
        projectName: {
            required: "Please enter your Project Name",
            maxlength: "Your Project Name must less than 50 characters"
        },
        startDate: {
            required: "Please select start Date"
        },
        expectedEndDate: {
            required: "Please select end Date"
        },
        customerId: {
            valueNotEquals: "Please select a Customer"
        }
    }
});

jQuery('#forgotpasswordForm').validate({
    rules: {
        emailId: {
            required: true,
            email: true
        }
    },
    messages: {
        required: "Please enter your email",
        email: "Please enter a valid email address"
    }
});


jQuery('#registertrationForm #userName').focusout(function () {
    var userName = jQuery(this).val();
    if (userName.length > 0) {
        jQuery.ajax({
            url: "/ProjectAllocationSystem/findUser",
            type: 'POST',
            async: true,
            data: {
                "userName": userName,
            },
            success: function (data) {
//            	alert(data.statusDto.statusCode);
                if (data.statusDto != null && data.statusDto.statusCode == 'SUCCESS') {
                    jQuery('#validUser').show();
                    jQuery('#validUser').attr("class", "false");
                    jQuery('#validUser').html(data.statusDto.statusMessage);
                    
                    jQuery('#userId').attr("readonly","readonly");
                    jQuery('#userId').attr("value",data.userId);
                    //					jQuery('#userName').addClass("error");
                } else {
                    jQuery('#validUser').hide();
                    jQuery('#userId').removeAttr("readonly");
                    jQuery('#userId').attr("value","");
                    
                    jQuery('#validUser').attr("class", "true");
                }
            },
            error: function () {
                alert('Something went Wrong, catch you soon');
            }
        });
    }

});

jQuery('#registertrationForm #userName').focusin(function () {
    jQuery('#validUser').hide();
    jQuery('#validUser').attr("class", "false");
});


jQuery('#forgotpwdButton').click(function(){
	jQuery('#forgotpasswordForm').toggle();
});

jQuery('#forgotPWD').click(function(){
	var emailId=jQuery('#emailId').val();
	var forgotPWDURL=$('#forgotPasswordURL').val()+"/forgotPassword";
	if(jQuery('#forgotpasswordForm').valid()){
		jQuery.ajax({
	        url: forgotPWDURL,
	        type: 'POST',
	        data: {
	            "emailId": emailId,
	        },
	        success: function (data) {
	    		if(data.statusCode == 'FAILURE'){
	    			jQuery("#forgotPWDErrorMessage").show().html(data.statusMessage).attr("class", "error");;
	    		}else{
	    			jQuery('#forgotpasswordForm').hide();
	    			jQuery("#forgotPWDErrorMessage").show().html(data.statusMessage).attr("class", "true");;
	    		}
	        	
	        },
	        error: function () {
	            alert('Something went Wrong, catch you soon');
	        }
	    });
	}
});


jQuery('#SkillSetTableContainer').jtable({
    title: 'Skill Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    actions: {
        listAction: '/ProjectAllocationSystem/skillm/getAllSkills',
    	createAction: jQuery("#createSkillAction").val(),
    	updateAction: jQuery("#editSkillAction").val(),
//      deleteAction:'${pageContext.request.contextPath}/skillm/skillsetactions?action=delete'
    },
    fields: {
    	skillID: {
    		 title: 'Skill ID',
             key: true,
             list: true,
             create: true,
             edit: false
        },
        description: {
            title: 'Skill Description',
        },
        groupInfo:  {
            title: 'Group Info'
            /*options :{"TECHNICAL":"Technical","FUNCTIONAL":"Functional"},*/
        }
    },
    formCreated: function (event, data) {
        data.form.find('input[name="skillID"]').addClass(
          'validate[required]');
        data.form.find('input[name="description"]').addClass(
          'validate[required]');
        data.form.validationEngine();
    },
    formSubmitting: function (event, data) {
        return data.form.validationEngine('validate');
    },
    formClosed: function (event, data) {
        data.form.validationEngine('hide');
        data.form.validationEngine('detach');
    }
});

jQuery('#EmployeeTableContainer').jtable({
    title: 'Employee Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    actions: {
        listAction: '/ProjectAllocationSystem/empm/fetchAllEmployees',
       	updateAction : jQuery('#editEmployeeAction').val(),
       	createAction : jQuery('#createEmployeeAction').val()
    },
    fields: {
    	employeeId: {
    		 title: 'Employee ID',
             key: true,
             list: true,
             create: true,
             edit: false
        },
        employeeName:{
        	title : 'Employee Name'
        },
        empStatus: {
            title: 'Employee Status',
            options: { '0': 'Billable', '1': 'Un Billable','2':'Pool' }
        },
        competency:  {
            title: 'Competency',
            options: { 'c1': 'C1', 'c2': 'C2','c3':'C3' }
        },
        skillSetId :{
        	title: 'SkillSet',
        	options: '/ProjectAllocationSystem/skillm/getSkillOptions',
        	create: true,
        	sorting: false,
            edit: true
        },
        projectId :{
        	title: 'Project',
        	options: '/ProjectAllocationSystem/prjctm/getProjectOptions',
        	create: true,
        	sorting: false,
            edit: true
        },	
        assignedDate: {
            title: 'Assigned date',
            type: 'date',
            create: true,
            edit: true
        },
        endDate: {
            title: 'End date',
            type: 'date',
            create: true,
            edit: true
        }
    },
    formCreated: function (event, data) {
        data.form.find('input[name="employeeId"]').addClass('validate[required]');
        data.form.find('input[name="assignedDate"]').addClass('validate[required,custom[date]]');
        data.form.find('input[name="endDate"]').addClass('validate[required,custom[date]]');
        /* data.form.find('select[name=skillSetId]').attr('multiple','multiple'); */
        data.form.validationEngine();
    },
    formSubmitting: function (event, data) {
    	var startDate=data.form.find('input[name="assignedDate"]').val();
    	var endDate=data.form.find('input[name="endDate"]').val();
    	if(startDate != "" || endDate != ""){
    		return (data.form.validationEngine('validate') && isEndDateAfterStartDate(startDate,endDate));
    	}
        return data.form.validationEngine('validate');
    	
    },
    formClosed: function (event, data) {
        data.form.validationEngine('hide');
        data.form.validationEngine('detach');
    }
});

jQuery('#UserTableContainer').jtable({
    title: 'User Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    multiSorting: true,
    actions: {
        listAction: '/ProjectAllocationSystem/usraccm/getAllUsers',
       	updateAction: jQuery('#editUserAction').val()
    },
    fields: {
    	userId: {
    		 title: 'User ID',
             key: true,
             list: true,
             edit: false
        },
        userName: {
            title: 'Usre Name',
            key: true,
            edit: false
        },
        emailId:  {
            title: 'Email Id',
            edit: false
        },
        accountStatus:  {
            title: 'Account Status',
            options: { '0': 'Locked', '1': 'Un Locked' }
        },
        userGroupId:{
        	title :'UserGroup Name' ,
        	options: '/ProjectAllocationSystem/usrgrpm/getAllUserGroupOptions',
        	sorting: false
        }
    }
});

jQuery('#userGroupTableContainer').jtable({
    title: 'User Group Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    actions: {
        listAction: '/ProjectAllocationSystem/usrgrpm/getAllUserGroups',
       /*  <security:authorize access="hasRole('EDITUG')">,
        	updateAction: '${baseURL}/usrgrpm/usergroupactions?action=update'
        </security:authorize> */
        createAction : jQuery('#createUserGroupAction').val()
       	/* deleteAction : '${baseURL}/usrgrpm/usergroupactions?action=delete' */
    },
    fields: {
    	groupId: {
    		 title: 'Group ID',
             key: true,
             list: true,
             create: true,
             edit: false
        },
        
        groupName:{
        	title: 'Group Name',
            width: '20%',
            inputClass : 'validate[required]'
        },
        TestColumn: {
            title: ' ',
            width: '20%',
            create: false,
            sorting: false,
            edit:false,
            display: function (data) {
                return $('<input type="button" onclick="assignRights(\''+data.record.groupId +'~'+data.record.groupName +'\')" value="Edit & Assign Rights for ' + data.record.groupName + ' group"/><div class="groupRightsMsg_'+data.record.groupId+'" style="display:none;color: green; font-size: 11px;"></div>');
            }
        }
    },
    formCreated: function (event, data) {
        data.form.find('input[name="groupId"]').addClass(
          'validate[required]');
        data.form.find('input[name="groupName"]').addClass(
          'validate[required]');
        
        data.form.validationEngine();
    },
    formSubmitting: function (event, data) {
        return data.form.validationEngine('validate');
    },
    formClosed: function (event, data) {
        data.form.validationEngine('hide');
        data.form.validationEngine('detach');
    }
});


jQuery('#customerTableContainer').jtable({
    title: 'Customer Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    multiSorting: true,
    actions: {
        listAction: '/ProjectAllocationSystem/custm/fetchcustomers',
    	updateAction: jQuery('#editCustomerAction').val(),
    	createAction : jQuery('#createCustomerAction').val()
       	/* deleteAction : '${pageContext.request.contextPath}/custm/customeractions?action=delete' */
    },
    fields: {
    	customerID: {
    		 title: 'Customer ID',
             key: true,
             list: true,
             create: true,
             edit: false
        },
        customerName: {
            title: 'Customer Name'
        }
    },
    formCreated: function (event, data) {
        data.form.find('input[name="customerID"]').addClass(
          'validate[required]');
        data.form.find('input[name="customerName"]').addClass(
          'validate[required]');
        data.form.validationEngine();
    },
    formSubmitting: function (event, data) {
        return data.form.validationEngine('validate');
    },
    formClosed: function (event, data) {
        data.form.validationEngine('hide');
        data.form.validationEngine('detach');
    }
});


jQuery('#ProjectTableContainer').jtable({
    title: 'Project Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    multiSorting: true,
    actions: {
        listAction: '/ProjectAllocationSystem/prjctm/fetchAllProjects',
        updateAction: jQuery('#editProjectAction').val(),
        createAction : jQuery('#createProjectAction').val()
    },
    fields: {
    	projectID: {
    		 title: 'Project ID',
             key: true,
             list: true,
             create: true,
             edit: false
        },
        projectName: {
            title: 'Project Name'
        },
        adminSPOC:  {
            title: 'Admin SPOC',
        },
        techSPOC:  {
            title: 'Tech SPOC',
        },
        startDate: {
            title: 'start date',
            type: 'date',
			maxDate: 30,
            create: true,
            edit: true
        },
        expectedEndDate: {
            title: 'End date',
            width: '10%',
            type: 'date',
            create: true,
            edit: true
        },
        customerId:{
        	title :'Customer Name' ,
        	options: '/ProjectAllocationSystem/custm/getAllCustomers',
        	sorting: false
        }
    },
    //Initialize validation logic when a form is created
    formCreated: function (event, data) {
        data.form.find('input[name="projectID"]').addClass(
          'validate[required]');
        data.form.find('input[name="projectName"]').addClass(
          'validate[required]');
        data.form.find('input[name="startDate"]').addClass('validate[required,custom[date]]');
        data.form.find('input[name="expectedEndDate"]').addClass('validate[required,custom[date]]');
        data.form.validationEngine();
    },
    //Validate form when it is being submitted
    formSubmitting: function (event, data) {
    	var startDate=data.form.find('input[name="startDate"]').val();
    	var endDate=data.form.find('input[name="expectedEndDate"]').val();
    	if(startDate != "" || endDate != ""){
    		return (data.form.validationEngine('validate') && isEndDateAfterStartDate(startDate,endDate));
    	}
        return data.form.validationEngine('validate');
    	
    },
  //Dispose validation logic when form is closed
    formClosed: function (event, data) {
        data.form.validationEngine('hide');
        data.form.validationEngine('detach');
    }
});



jQuery('#allocationRequestTableContainer').jtable({
    title: 'Allocation Request Management',
    paging: true, 
    pageSize: 10,
    sorting: true, 
    defaultSorting: 'Name ASC',
    multiSorting: true,
    actions: {
        listAction: '/ProjectAllocationSystem/allocam/fetchAllocationRequests',
        updateAction: jQuery('#editAllocationRequestAction').val(),
        createAction: jQuery('#raiseAllocationRequestAction').val()
    },
    fields: {
    	requestID: {
    		 title: 'Request ID',
             key: true,
             list: true,
             create: false,
             edit: false
        },
        requestor: {
            title: 'Requestor Name',
            list: true,
            create: false,
            edit: false
        },
        typeOfRequest:  {
            title: 'Request Type',
            options: { 'A': "Allocation",'D': "De Allocation",'B': "Billable To Non-Billable",'N':"Non-Billable To Billable"}
        },
        requestDate:{
        	title : 'Requested Date',
        	list:true,
        	create: false,
            edit: false
        },
        employeeName:  {
            title: 'Employee Name/ EmployeeId',
        }, 
        skillSetId :{
        	title: 'SkillSet',
        	options: '/ProjectAllocationSystem/skillm/getSkillOptions',
        	create: true,
        	sorting: false,
            edit: true
        },
        dateHandled: {
            title: 'Date Handled',
            type: 'date',
            create: true,
            edit: true
        },
        handledBy:  {
            title: 'Handled By ',
        },
        projectId :{
        	title: 'Project',
        	options: '/ProjectAllocationSystem/prjctm/getProjectOptions',
        	create: true,
        	sorting: false,
            edit: true
        },
        startDate: {
            title: 'Start Date',
            width: '10%',
            type: 'date',
            create: true,
            edit: true
        },
        endDate: {
            title: 'End date',
            width: '10%',
            type: 'date',
            create: true,
            edit: true
        },
        smilesUpdated: {
            title: 'Smiles Updated',
            width: '10%',
            type: 'date',
            create: true,
            edit: true
        },
        billableStatus: {
            title: 'Billable Status',
            options: { '0': 'Billable', '1': 'Un Billable','2':'Pool' }
        },
        comments :{
        	title : 'Comments'
        }
    },
    formCreated: function (event, data) {
        data.form.find('input[name="typeOfRequest"]').addClass('validate[required]');
        data.form.find('input[name="employeeId"]').addClass('validate[required]');
        data.form.find('input[name="startDate"]').addClass('validate[required,custom[date]]');
        data.form.find('input[name="endDate"]').addClass('validate[required,custom[date]]');
        data.form.find('[name=employeeName]').autocomplete({
		   max: 10,
		   delay: 300,
		   minLength: 2,
		   source: function(request, response) {
			   fetchEmployees(request,response);
		   }
        });
        data.form.validationEngine();
    },
    formSubmitting: function (event, data) {
    	var startDate=data.form.find('input[name="startDate"]').val();
    	var endDate=data.form.find('input[name="endDate"]').val();
    	if(startDate != "" || endDate != ""){
    		return (data.form.validationEngine('validate') && isEndDateAfterStartDate(startDate,endDate));
    	}
        return data.form.validationEngine('validate');
    	
    },
    formClosed: function (event, data) {
        data.form.validationEngine('hide');
        data.form.validationEngine('detach');
    },
    /*toolbar: {
        items: [{
            text: 'Raise a Allocation Request',
            click: function () {
                alert("test");
            }
        }]
    }*/
});


function fetchEmployees(request, response) {
    $.ajax({
        url: "/ProjectAllocationSystem/empm/searchemployeesbykeyword",
        dataType: "json",
        async: true,
        data: {
     	   keyword : request.term
        },
        success: function( data, textStatus, jqXHR) {
            response( $.map( data, function( item ) {
                return {
                    label: item.employeeName,
                    value: item.employeeId,
                }
            }));
        },
        error: function(jqXHR, textStatus, errorThrown){
             alert( textStatus);
        }
    });
}