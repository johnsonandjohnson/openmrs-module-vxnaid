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

import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import static java.util.Optional.ofNullable;

/**
 * Retires Encounter Types from default CFL configuration which are not used by the VMP.
 */
public class RetireDefaultEncounterTypesBundle extends VersionedMetadataBundle {
  private static final String ATTACHMENT_UPLOAD_ENCOUNTER_TYPE_UUID = "5021b1a1-e7f6-44b4-ba02-da2f2bcf8718";
  private static final String COMMON_RETIRE_REASON = "Encounter Type not needed in VMP.";

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
    final EncounterService encounterService = Context.getEncounterService();

    ofNullable(encounterService.getEncounterTypeByUuid(ATTACHMENT_UPLOAD_ENCOUNTER_TYPE_UUID)).ifPresent(
        encounterType -> encounterService.retireEncounterType(encounterType, COMMON_RETIRE_REASON));
  }
}
