$(document).ready(function () {
    $("#spResults").hide();
    $('#spAlert1').hide();
    $("#spSearch").click(function (e) {
        $("#spResults").hide();
        $('#spAlert1').hide();
        $('#spResultPanel').removeClass();
        e.preventDefault();
        $(this).blur();
        var validInput = false;
        var searchData = new Object();
        searchData.requestType = 'SearchPatient';
        searchData.patient = new Object();
        if ($('#spPatientId').val() != "") {
            validInput = true;
            searchData.patient.patientId = $('#spPatientId').val();
        }
        if ($('#spPatientEmail').val() != "") {
            validInput = true;
            searchData.patient.patientEmail = $('#spPatientEmail').val();
        }
        if ($('#spPatientFName').val() != "") {
            validInput = true;
            searchData.patient.firstName = $('#spPatientFName').val();
        }
        if ($('#spPatientContact').val() != "") {
            validInput = true;
            searchData.patient.contactNumber = $('#spPatientContact').val();
        }
        if (!validInput) {
            $('#spAlert1').text('Ooopss!! Please enter atleast one criteria for search');
            $('#spAlert1').show(250);
            return;
        }
        var jsonSearchData = JSON.stringify(searchData);
        var jxhr = $.ajax({
            type: "GET",
            url: "AjaxSevlet",
            data: {
                jsonData: jsonSearchData
            },
            dataType: "json"
        }).done(function (data) {
            $('#spResults').show(500);
            if (data.Result != undefined && data.Result == "Success") {
                if (data.List == "Empty") {
                    $('#spResultPanel').addClass('panel panel-danger');
                    $('#spResultBody').html('Sorry no patient record found. Try again or create a new patient record');
                } else {
                    $('#spResultPanel').addClass('panel panel-default');
                    var jsonObject = {
                        'tableID': 'spResultTable',
                        'tableClass': 'table table-hover table-condensed',
                        'tableHead': [{
                            'titleAttr': 'patientID',
                            'displayAttr': 'ID'
                        }, {
                            'titleAttr': 'firstName',
                            'displayAttr': 'First Name'
                        }, {
                            'titleAttr': 'lastName',
                            'displayAttr': 'Last Name'
                        }, {
                            'titleAttr': 'emailID',
                            'displayAttr': 'Email'
                        }, {
                            'titleAttr': 'contactNumber',
                            'displayAttr': 'Contact'
                        }],
                        'tableBody': data.List,
                        'tableSelect': 'SelectOne',
                        'tableRowIdentifier': 'patientID',
                        'tableAction': [{
                            'buttonText': 'Select',
                            'class': 'btn btn-primary',
                            'id': 'spSelect',
                            'callback': function () {
                                $('#spSelect').blur();
                                if ($('input[name=patientID]:radio:checked').val() == undefined) {
                                    $('#spAlert1').text('Snappp!! Please select a patient');
                                    $('#spAlert1').show(250);
                                    $('html,body').animate({
                                            scrollTop: $('#spAlert1').offset().top
                                        },
                                        'slow');
                                    return;
                                }
                                $.event.trigger({
                                    type: "searchPatientSelected",
                                    message: $('input[name=patientID]:radio:checked').val()
                                });
                                $('#spAlert1').hide();
                            }
                        }]
                    };
                    var tableUtil = new TableUtil(jsonObject);
                    $('#spResultBody').html(tableUtil.generateTableHTML());
                    if (jsonObject.tableAction != undefined) {
                        $.each(jsonObject.tableAction, function (index, action) {
                            $('#' + action.id).click(action.callback);
                        });
                    }
                }
            } else if (data.Result != undefined && data.Result == "Fail") {
                $('#spResultPanel').addClass('panel panel-danger');
                $('#spResultBody').html(data.Reason);
            }
        }).fail(function (jqXHR, textStatus) {
            $('#spResults').show(500);
            $('#spResultPanel').addClass('panel panel-danger');
            $('#spResultBody').html('<font color="red">Error fetching data from server</font>' + '<br>If error persists please contact us.' + JSON.stringify(jqXHR));
        });
    });

    $("#spClear").click(function (e) {
        e.preventDefault();
        $(this).blur();
        $('#spPatientId').val('');
        $('#spPatientEmail').val('');
        $('#spPatientFName').val('');
        $('#spPatientLName').val('');
        $('#spPatientContact').val('');
        $('#spResultPanel').removeClass();
        $('#spResults').hide(500);
        $('#spAlert1').hide();
    });

    function TableUtil(jsonData) {
        this.tableID = jsonData.tableID;
        this.tableClass = jsonData.tableClass;
        this.tableHead = jsonData.tableHead;
        this.tableBody = jsonData.tableBody;
        this.tableSelect = jsonData.tableSelect;
        this.tableRowIdentifier = jsonData.tableRowIdentifier;
        this.tableAction = jsonData.tableAction;
        this.generateTableHTML = function () {
            var tableSelect = '';
            if (this.tableSelect != undefined && this.tableRowIdentifier != undefined) {
                if (this.tableSelect == 'SelectOne') {
                    tableSelect = '<input type="radio" name="' + this.tableRowIdentifier + '" value="$val$">';
                }
            }
            var thead = '';
            var headerAttr = new Array();
            if (tableSelect != '') {
                thead += '<th></th>';
            }
            $.each(this.tableHead, function (index, value) {
                thead += '<th>' + value.displayAttr + '</th>';
                headerAttr.push(value.titleAttr);
            });
            thead = '<tr>' + thead + '</tr>';
            var tbody = '';
            var self = this;
            $.each(this.tableBody, function (index, row) {
                var trow = '';
                if (tableSelect != '') {
                    trow += '<td>' + tableSelect.replace('$val$', row[self.tableRowIdentifier]) + '</td>';
                }
                $.each(headerAttr, function (index, attr) {
                    trow += '<td>' + row[attr] + '</td>';
                });
                trow = '<tr>' + trow + '</tr>';
                tbody += trow;
            });
            var tableHTML = '<table id="' + this.tableID + '" class="' + this.tableClass + '">';
            tableHTML += '<thead>' + thead + '</thead>';
            tableHTML += '<tbody>' + tbody + '</tbody>';
            tableHTML += '</table>';
            tableHTML = '<div class="table-responsive">'+tableHTML+'</div>';
            if (this.tableAction != undefined) {
                var buttonText = '';
                $.each(this.tableAction, function (index, action) {
                    buttonText += '<button id="' + action.id + '" class="' + action.class + '">' + action.buttonText + '</button>';
                });
                tableHTML += buttonText;
            }
            return tableHTML;
        };
    };
});