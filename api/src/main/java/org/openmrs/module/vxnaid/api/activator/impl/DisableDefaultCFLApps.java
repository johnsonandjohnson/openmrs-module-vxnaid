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
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.cfl.api.activator.ModuleActivatorStep;

import static org.openmrs.module.vxnaid.api.activator.impl.VmpModuleActivatorStepOrderEnum.DISABLE_DEFAULT_CFL_APPS_ACTIVATOR_STEP;
import static org.openmrs.module.vxnaid.api.activator.impl.VmpModuleActivatorStepOrderEnum.VMP_OFFSET;

public class DisableDefaultCFLApps implements ModuleActivatorStep {
  private final static String REGISTER_CAREGIVER_APP = "cflui.registerCaregiver";
  private final static String FIND_CAREGIVER_APP = "cflui.findCaregiver";

  private AppFrameworkService appFrameworkService;

  @Override
  public int getOrder() {
    return VMP_OFFSET + DISABLE_DEFAULT_CFL_APPS_ACTIVATOR_STEP.ordinal();
  }

  @Override
  public void startup(Log log) throws Exception {
    appFrameworkService.disableApp(REGISTER_CAREGIVER_APP);
    appFrameworkService.disableApp(FIND_CAREGIVER_APP);
  }

  public void setAppFrameworkService(AppFrameworkService appFrameworkService) {
    this.appFrameworkService = appFrameworkService;
  }
}
