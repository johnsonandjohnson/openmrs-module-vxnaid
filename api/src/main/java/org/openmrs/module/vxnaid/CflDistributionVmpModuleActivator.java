/*
 *  This Source Code Form is subject to the terms of the Mozilla Public License,
 *  v. 2.0. If a copy of the MPL was not distributed with this file, You can
 *  obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 *  the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *  <p>
 *  Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.vxnaid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.module.ModuleException;
import org.openmrs.module.emrapi.utils.MetadataUtil;

public class CflDistributionVmpModuleActivator extends BaseModuleActivator {
  public static final String MODULE_API_PACKAGE = "org.openmrs.module.vxnaid.api";

  private Log log = LogFactory.getLog(this.getClass());

  @Override
  public void started() {
    log.info("Started Vxnaid Distribution Module");
    try {
      MetadataUtil.setupStandardMetadata(getClass().getClassLoader());
    } catch (Exception e) {
      throw new ModuleException("Failed to activate vxnaid module!", e);
    }
  }

  @Override
  public void stopped() {
    log.info("Shutting down Vxnaid Distribution Module");
  }
}
