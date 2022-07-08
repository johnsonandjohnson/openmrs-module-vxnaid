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
import org.openmrs.api.FormService;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

public class RetireHIVProgramFormBundle extends VersionedMetadataBundle {
  private static final String HIV_PROGRAM_UUID_TO_RETIRE = "06c596e7-53dd-44c1-9609-f1406fd9e76d";
  private static final List<String> HIV_PROGRAM_ENCOUNTER_TYPE_UUIDS =
      asList("f1c23f25-20e1-4503-b09e-116aba0a6063", "b3af129b-90c9-4f84-966e-f4f9515083e6");
  private static final List<String> HIV_PROGRAM_FORMS_UUIDS =
      Arrays.asList("ad796f8c-b966-11eb-9ad8-0242ac120002", "39258edb-92c5-11eb-bf66-0242ac120002");
  private static final String HIV_PROGRAM_RETIRE_REASON = "HIV Program not needed in Vxnaid.";

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
    retireProgram();
    retireEncounterTypes();
    retireForms();
  }

  private void retireProgram() {
    final ProgramWorkflowService programWorkflowService = Context.getProgramWorkflowService();
    ofNullable(programWorkflowService.getProgramByUuid(HIV_PROGRAM_UUID_TO_RETIRE)).ifPresent(
        programToRetire -> programWorkflowService.retireProgram(programToRetire, HIV_PROGRAM_RETIRE_REASON));
  }

  private void retireEncounterTypes() {
    final EncounterService encounterService = Context.getEncounterService();

    for (String encounterTypeName : HIV_PROGRAM_ENCOUNTER_TYPE_UUIDS) {
      ofNullable(encounterService.getEncounterTypeByUuid(encounterTypeName)).ifPresent(
          encounterType -> encounterService.retireEncounterType(encounterType, HIV_PROGRAM_RETIRE_REASON));
    }
  }

  private void retireForms() {
    final FormService formService = Context.getFormService();

    for (String formUuid : HIV_PROGRAM_FORMS_UUIDS) {
      ofNullable(formService.getFormByUuid(formUuid)).ifPresent(
          form -> formService.retireForm(form, HIV_PROGRAM_RETIRE_REASON));
    }
  }
}
