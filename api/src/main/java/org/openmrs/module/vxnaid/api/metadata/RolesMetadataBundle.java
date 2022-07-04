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

import org.openmrs.module.metadatadeploy.bundle.CoreConstructors;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

public class RolesMetadataBundle extends VersionedMetadataBundle {
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
    install(CoreConstructors.role("Operator", "", emptySet(), emptySet()));
    install(CoreConstructors.role("Sync Admin", "", singleton("Privilege Level: Doctor"), emptySet()));
  }
}
