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

import org.apache.commons.io.IOUtils;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.cfl.api.util.MetadataSQLScriptRunner;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;
import org.openmrs.module.reporting.report.manager.BaseReportManager;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class AbstractReportBundle extends VersionedMetadataBundle {
  private static final String DROP_VIEW_IF_EXISTS_STATEMENT = "DROP VIEW IF EXISTS ";
  private static final String REPORT_DB_VIEW_RESOURCE_DIR = "mysql/report/";
  private static final String SQL_EXT = ".sql";

  private final MetadataSQLScriptRunner metadataSQLScriptRunner;
  private final BaseReportManager reportManager;

  AbstractReportBundle(DbSessionFactory dbSessionFactory, BaseReportManager reportManager) {
    this.metadataSQLScriptRunner = new MetadataSQLScriptRunner(dbSessionFactory);
    this.reportManager = reportManager;
  }

  @Override
  protected void installEveryTime() {
    ReportManagerUtil.setupReport(reportManager);
  }

  @Override
  protected void installNewVersion() throws Exception {
    for (String dbViewName : getDBViewNames()) {
      metadataSQLScriptRunner.executeQuery(DROP_VIEW_IF_EXISTS_STATEMENT + dbViewName);
      metadataSQLScriptRunner.executeQuery(getQueryFromResource(REPORT_DB_VIEW_RESOURCE_DIR + dbViewName + SQL_EXT));
    }
  }

  private String getQueryFromResource(String resourcesPath) throws IOException {
    String query = null;
    InputStream in = AbstractReportBundle.class.getClassLoader().getResourceAsStream(resourcesPath);
    if (in != null) {
      query = IOUtils.toString(in);
    }

    return query;
  }

  /**
   * @return the DB View names used by this Report - for each name a file in {@link #REPORT_DB_VIEW_RESOURCE_DIR} with the
   * same name and .sql extension must exist and must contain Create-statement for the view.
   */
  abstract List<String> getDBViewNames();
}
