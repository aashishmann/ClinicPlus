$(document).ready(function () {
    $('#appAlert1').hide();
    $('#appPatientDetails').hide();
    $('#appintment').prop('disabled', true);
    var getPatientInfo = function (patientId, callback,
        errCallback) {
        var jsonPatientId = new Object();
        jsonPatientId.requestType = 'GetPatientData';
        jsonPatientId.patientId = patientId;
        var jxhr = $.ajax({
            type: "GET",
            url: "AjaxSevlet",
            data: {
                jsonData: JSON.stringify(jsonPatientId)
            },
            dataType: "json"
        }).done(function (data) {
            callback(data);
        }).fail(function (jqXHR, textStatus) {
            errCallback(textStatus);
        });
    };

    $(document).on("searchPatientSelected", function (e) {
        hideDiv('appAlert1',0);
        if (e.message == undefined) {
            return;
        }
        getPatientInfo(e.message, loadPatientInfo,
            errorLoadPatientInfo);
    });

    $('#appLoad').click(function () {
    	$(this).blur();
    	hideDiv('appPatientDetails',0);
    	hideDiv('appAlert1',0);
        if ($('#appPatientId').val() == "" || !$.isNumeric($('#appPatientId').val())) {
            showAlert('alert alert-warning', 'Woahhh!! Please enter (valid)patient id to load details.', 250);
        } else {
            getPatientInfo($('#appPatientId')
                .val(), loadPatientInfo,
                errorLoadPatientInfo);
        }
    });
    
    $('#appClear').click(function(){
    	$(this).blur();
    	$('#appPatientId').val('');
    	hideDiv('appPatientDetails',0);
    	hideDiv('appAlert1',0);
        $('#appintment').prop('disabled', true);
    });

    var loadPatientInfo = function (patientInfo) {
        if (patientInfo.Result != undefined && patientInfo.Result == 'Success') {
        	var pInfo = patientInfo.patientInfo;
        	$('#appPatientId').val('');
        	$('#appPatientLabelId').text(pInfo.patientID);
        	$('#appPatientLName').text(pInfo.lastName);
        	$('#appPatientContact').text(pInfo.contactNumber);
        	$('#appEmailId').text(pInfo.emailID);
        	$('#appPatientAddress').text(pInfo.address);
        	$('#appPatientDOB').text(pInfo.dob);
        	$('#appPatientFName').text(pInfo.firstName);
        	showDiv(null, 'appPatientDetails',500);
            $('html,body').animate({
                scrollTop: $('#appPanelPatientInfo').offset().top
            },
            'fast');
            $('#appintment').prop('disabled', false);
        } else {
            if (patientInfo.Result == 'Info') {
                showAlert('alert alert-warning', patientInfo.Reason, 250);
            } else if (patientInfo.Result == 'Fail') {
                showAlert('alert alert-danger', patientInfo.Reason, 250);
            }
        }
    };

    var errorLoadPatientInfo = function () {
    	showAlert('alert alert-danger', 'Oh noooo!! we faced an fatal error. Try again. If error persists please contact us.', 250);
    };

    var hideDiv = function (elementId,delay) {
        $('#'+elementId).hide(delay);
        $('#'+elementId).removeClass();

    };
    
    var showDiv = function(divClass, elementId, delay) {
        $('#'+elementId).addClass(divClass);
        $('#'+elementId).show(delay);
    };

    var showAlert = function (alertClass, text, delay) {
        $('#appAlert1').text(text);
        showDiv(alertClass, 'appAlert1', delay);
    };
})