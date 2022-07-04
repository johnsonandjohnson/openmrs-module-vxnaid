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

public enum VmpModuleActivatorStepOrderEnum {
  /**
   * @see ConfigureVmpPatientDashboardAppsActivatorStep
   */
  CONFIGURE_VMP_PATIENT_DASHBOARD_APPS_ACTIVATOR_STEP,

  /**
   * @see DisableDefaultCFLApps
   */
  DISABLE_DEFAULT_CFL_APPS_ACTIVATOR_STEP,

  /**
   * @see InstallMetadataBundles
   */
  INSTALL_METADATA_BUNDLES_ACTIVATOR_STEP;

  static final int VMP_OFFSET = 100;
}
