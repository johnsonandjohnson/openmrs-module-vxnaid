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

import org.openmrs.PersonAttributeType;import org.openmrs.api.PersonService;import org.openmrs.api.context.Context;import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

public class InitialPersonAttributeTypesMetadata extends VersionedMetadataBundle {
  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  protected void installEveryTime() throws Exception {
    //do nothing
  }

  @Override
  protected void installNewVersion() throws Exception {
    createNewPersonAttributeType("Birth Weight", "4f9b97bf-4381-11ef-92cb-0242ac1d0002");
  }

  private void createNewPersonAttributeType(String name, String uuid) {
    PersonService personService = Context.getPersonService();

    if (personService.getPersonAttributeTypeByName(name) == null) {
      PersonAttributeType personAttributeType = new PersonAttributeType();
      personAttributeType.setName(name);
      personAttributeType.setUuid(uuid);
      personAttributeType.setFormat("java.lang.String");
      personService.savePersonAttributeType(personAttributeType);
    }
  }
}
