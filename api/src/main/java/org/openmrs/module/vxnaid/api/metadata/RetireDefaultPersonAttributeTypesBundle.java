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

import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;

/**
 * Retires Person Attribute Types from default CFL configuration which are not used by the Vxnaid.
 */
public class RetireDefaultPersonAttributeTypesBundle extends VersionedMetadataBundle {
  private static final List<String> PERSON_ATTRIBUTE_TYPES_TO_RETIRE =
      asList("Gender Identity", "Nationality", "City of current residence", "City of origin", "Education degree", "Sector",
          "Job");
  private static final String COMMON_RETIRE_REASON = "Attribute not needed in Vxnaid.";

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
    final PersonService personService = Context.getPersonService();

    PERSON_ATTRIBUTE_TYPES_TO_RETIRE.forEach(name -> ofNullable(personService.getPersonAttributeTypeByName(name)).ifPresent(
        personAttributeType -> personService.retirePersonAttributeType(personAttributeType, COMMON_RETIRE_REASON)));
  }
}
