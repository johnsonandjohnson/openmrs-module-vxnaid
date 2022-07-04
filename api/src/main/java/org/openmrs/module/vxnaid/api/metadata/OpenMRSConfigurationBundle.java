/*
 *  This Source Code Form is subject to the terms of the Mozilla Public License,
 *  v. 2.0. If a copy of the MPL was not distributed with this file, You can
 *  obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 *  the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *  <p>
 *  Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.vxnaid.api.metadata;

import org.openmrs.PatientIdentifierType;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

/**
 * Adjusts OpenMRS configuration.
 */
public class OpenMRSConfigurationBundle extends VersionedMetadataBundle {
  private static final String OPENMRS_ID_PATIENT_IDENTIFIER_TYPE_NAME = "OpenMRS ID";

  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  protected void installEveryTime() {
    // Install only once, when version changes
  }

  @Override
  protected void installNewVersion() {
    disableOpenMRSIdValidation();
  }

  private void disableOpenMRSIdValidation() {
    final PatientService patientService = Context.getPatientService();
    final PatientIdentifierType openMrsId =
        patientService.getPatientIdentifierTypeByName(OPENMRS_ID_PATIENT_IDENTIFIER_TYPE_NAME);

    if (openMrsId != null) {
      openMrsId.setValidator(null);
    }
  }
}
