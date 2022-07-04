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

import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

/**
 * Retires Patient Identifier Types from default CFL configuration which are not used by the VMP.
 */
public class RetirePatientIdentifiersBundle extends VersionedMetadataBundle {
  private static final List<String> PATIENT_IDENTIFIER_TYPE_TO_RETIRE_NAMES =
      asList("Old Identification Number", "OpenEMPI ID", "OpenMRS Identification Number");
  private static final String COMMON_RETIRE_REASON = "Person Identifier not needed in VMP.";

  @Override
  public int getVersion() {
    return 0;
  }

  @Override
  protected void installEveryTime() {
    // Install only once, when version changes
  }

  @Override
  protected void installNewVersion() {
    final PatientService patientService = Context.getPatientService();

    PATIENT_IDENTIFIER_TYPE_TO_RETIRE_NAMES.forEach(
        typeName -> ofNullable(patientService.getPatientIdentifierTypeByName(typeName)).ifPresent(
            type -> patientService.retirePatientIdentifierType(type, COMMON_RETIRE_REASON)));
  }
}
