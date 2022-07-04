var delPatient = delPatient || {};

delPatient.deletePatientCreationDialog = null;

/**
 * Functions used to Delete a Patient
 */

delPatient.returnUrl = "/coreapps/findpatient/findPatient.page?app=coreapps.findPatient";

delPatient.createDeletePatientCreationDialog = function() {
    emr.loadMessages([
        "coreapps.task.deletePatient.deletePatientSuccessful",
        "coreapps.task.deletePatient.deletePatientUnsuccessful"
    ]);
    delPatient.deletePatientCreationDialog = emr.setupConfirmationDialog({
        selector: '#vmp-delete-patient-creation-dialog',
        actions: {
            confirm: function() {
                var deleteReason = jq('#vmp-delete-reason').val().trim(); //Retrieve text from text box
                if(deleteReason && deleteReason.length > 0) { //Should not be invalid or empty
                    jq.ajax({
                        url: '/' + OPENMRS_CONTEXT_PATH + '/ws/rest/v1/biometric/participant/' + delPatient.patientUUID + '?reason=' + deleteReason,
                        type: 'PUT',
                        success: function() {
                            emr.successMessage('coreapps.task.deletePatient.deletePatientSuccessful');
                            jq('#vmp-delete-patient-creation-dialog' + ' .icon-spin').css('display', 'inline-block').parent().addClass('disabled');
                            delPatient.deletePatientCreationDialog.close();
                            jq(setTimeout(delPatient.goToReturnUrl, 1275)); //Allow the success message to display before redirecting to another page
                        },
                        error: function() {
                            emr.errorMessage("coreapps.task.deletePatient.deletePatientUnsuccessful");
                            delPatient.deletePatientCreationDialog.close();
                        }
                    });
                } else {
                    jq('#vmp-delete-reason-empty').css({'color' : 'red', display : 'inline'}).show(); //Show warning message if empty
                    jq('#vmp-delete-reason').val(""); //Clear the text box
                }
            },
            cancel: function() {
                //Clear fields, close dialog box
                jq('#vmp-delete-reason').val("");
                jq('#vmp-delete-reason-empty').hide();
                delPatient.deletePatientCreationDialog.close();
            }
        }
    });
};

delPatient.showDeletePatientCreationDialogForVMP = function(patientUUID) {
    delPatient.patientUUID = patientUUID;
    if (delPatient.deletePatientCreationDialog == null) {
        delPatient.createDeletePatientCreationDialog();
    }
    jq('#vmp-delete-reason-empty').hide();
    delPatient.deletePatientCreationDialog.show();
};

delPatient.goToReturnUrl = function() {
    emr.navigateTo({ applicationUrl: emr.applyContextModel(delPatient.returnUrl)});
};
