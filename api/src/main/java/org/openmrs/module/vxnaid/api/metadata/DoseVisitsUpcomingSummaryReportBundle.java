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

import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.vxnaid.reporting.report.DoseVisitsUpcomingSummaryReport;

import java.util.List;

import static java.util.Arrays.asList;

public class DoseVisitsUpcomingSummaryReportBundle extends AbstractReportBundle {
  DoseVisitsUpcomingSummaryReportBundle(DbSessionFactory dbSessionFactory,
                                        DoseVisitsUpcomingSummaryReport doseVisitsUpcomingSummaryReport) {
    super(dbSessionFactory, doseVisitsUpcomingSummaryReport);
  }

  @Override
  List<String> getDBViewNames() {
    return asList("UpcomingDose", "UpcomingDoseHelpTable", "UpcomingDoseAgg");
  }

  @Override
  public int getVersion() {
    return 1;
  }
}
