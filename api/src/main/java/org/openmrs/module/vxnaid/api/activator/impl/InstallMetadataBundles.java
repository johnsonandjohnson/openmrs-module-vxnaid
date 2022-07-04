/*
 *  This Source Code Form is subject to the terms of the Mozilla Public License,
 *  v. 2.0. If a copy of the MPL was not distributed with this file, You can
 *  obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 *  the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *  <p>
 *  Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.vxnaid.api.activator.impl;

import org.apache.commons.logging.Log;
import org.openmrs.api.context.Context;
import org.openmrs.module.cfl.api.activator.ModuleActivatorStep;
import org.openmrs.module.vxnaid.CflDistributionVmpModuleActivator;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.openmrs.module.metadatadeploy.bundle.MetadataBundle;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.openmrs.api.context.Context.getRegisteredComponent;
import static org.openmrs.module.vxnaid.api.activator.impl.VmpModuleActivatorStepOrderEnum.INSTALL_METADATA_BUNDLES_ACTIVATOR_STEP;
import static org.openmrs.module.vxnaid.api.activator.impl.VmpModuleActivatorStepOrderEnum.VMP_OFFSET;

public class InstallMetadataBundles implements ModuleActivatorStep {
  @Override
  public int getOrder() {
    return VMP_OFFSET + INSTALL_METADATA_BUNDLES_ACTIVATOR_STEP.ordinal();
  }

  @Override
  public void startup(Log log) {
    final MetadataDeployService service = getRegisteredComponent("metadataDeployService", MetadataDeployService.class);
    final List<MetadataBundle> vmpBundles = Context
        .getRegisteredComponents(MetadataBundle.class)
        .stream()
        .filter(component -> component.getClass().getName().startsWith(CflDistributionVmpModuleActivator.MODULE_API_PACKAGE))
        .collect(toList());

    service.installBundles(vmpBundles);
  }
}
