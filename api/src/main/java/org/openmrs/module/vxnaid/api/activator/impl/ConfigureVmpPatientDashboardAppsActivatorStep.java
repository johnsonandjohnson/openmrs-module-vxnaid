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
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.cfl.api.activator.ModuleActivatorStep;

import java.util.Arrays;
import java.util.List;

import static org.openmrs.module.vxnaid.api.activator.impl.VmpModuleActivatorStepOrderEnum.CONFIGURE_VMP_PATIENT_DASHBOARD_APPS_ACTIVATOR_STEP;
import static org.openmrs.module.vxnaid.api.activator.impl.VmpModuleActivatorStepOrderEnum.VMP_OFFSET;
import static org.openmrs.module.vxnaid.api.util.VmpPatientDashboardAppsConstants.COREAPPS_DELETE_PATIENT_EXT;

public class ConfigureVmpPatientDashboardAppsActivatorStep implements ModuleActivatorStep {

  /**
   * The list of extensions which should be disabled for Vxnaid
   */
  private static final List<String> EXTENSION_IDS = Arrays.asList(COREAPPS_DELETE_PATIENT_EXT);

  @Override
  public int getOrder() {
    return VMP_OFFSET + CONFIGURE_VMP_PATIENT_DASHBOARD_APPS_ACTIVATOR_STEP.ordinal();
  }

  @Override
  public void startup(Log log) {
    disableUnusedExtensions(EXTENSION_IDS);
  }

  private void disableUnusedExtensions(List<String> extensions) {
    final AppFrameworkService appFrameworkService = Context.getService(AppFrameworkService.class);
    extensions.forEach(appFrameworkService::disableExtension);
  }
}
