/* This script Done by RaSh:Rasool */
function parseformValues(formId) {
	var names = [];
	var myvalues = {};
	var formValuesList = [];
	jQuery.each(jQuery('#' + formId).serializeArray(), function(i, field) {
		names.push(field.name);
		if (myvalues[field.name] != undefined) {
			myvalues[field.name] = myvalues[field.name] + "|" + field.value;
		} else {
			myvalues[field.name] = field.value;
		}
		// alert(field.name+"="+myvalues[field.name]);
	});
	uniqueNames = jQuery.unique(names);
	jQuery.each(uniqueNames, function(j, value) {
		var param = value + "=" + myvalues[value];
		formValuesList.push(param);
	});
	formValuesList.join("&");
	var formValues = formValuesList.join("&");
	return formValues;
}
