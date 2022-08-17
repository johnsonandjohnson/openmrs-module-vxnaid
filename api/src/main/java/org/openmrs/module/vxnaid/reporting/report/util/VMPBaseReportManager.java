/*
 *  This Source Code Form is subject to the terms of the Mozilla Public License,
 *  v. 2.0. If a copy of the MPL was not distributed with this file, You can
 *  obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 *  the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *  <p>
 *  Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.vxnaid.reporting.report.util;

import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.service.DataSetDefinitionService;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.BaseReportManager;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;

public abstract class VMPBaseReportManager extends BaseReportManager {
  private final String sqlDataDefinitionUuid;
  private final String dataSetName;

  protected VMPBaseReportManager(final String sqlDataDefinitionUuid, final String dataSetName) {
    this.sqlDataDefinitionUuid = sqlDataDefinitionUuid;
    this.dataSetName = dataSetName;
  }

  @Override
  public ReportDefinition constructReportDefinition() {
    final ReportDefinition reportDefinition = new ReportDefinition();
    reportDefinition.setUuid(getUuid());
    reportDefinition.setName(getName());
    reportDefinition.setDescription(getDescription());
    reportDefinition.setParameters(getParameters());
    reportDefinition.addDataSetDefinition(getName(),
        Mapped.mapStraightThrough(getOrCreateSqlDataSetDefinition(sqlDataDefinitionUuid)));
    return reportDefinition;
  }

  protected SqlDataSetDefinition getOrCreateSqlDataSetDefinition(String sqlDataDefinitionUuid) {
    final DataSetDefinitionService dataSetDefinitionService = Context.getService(DataSetDefinitionService.class);
    SqlDataSetDefinition sqlDataSetDefinition =
        (SqlDataSetDefinition) dataSetDefinitionService.getDefinitionByUuid(sqlDataDefinitionUuid);

    if (sqlDataSetDefinition == null) {
      sqlDataSetDefinition = new SqlDataSetDefinition();
      sqlDataSetDefinition.setUuid(sqlDataDefinitionUuid);
      sqlDataSetDefinition.setName(dataSetName);
      sqlDataSetDefinition.setDescription("SQL DataSet created for Report: " + getName());
      sqlDataSetDefinition.addParameters(getParameters());
      sqlDataSetDefinition.setSqlQuery("SELECT * FROM openmrs." + dataSetName + ";");
      sqlDataSetDefinition = dataSetDefinitionService.saveDefinition(sqlDataSetDefinition);
    }

    return sqlDataSetDefinition;
  }

  protected ReportDesignResource createXLSXReportDesignResource() {
    final ReportDesignResource reportDesignResource = new ReportDesignResource();
    reportDesignResource.setExtension("xlsx");
    reportDesignResource.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    return reportDesignResource;
  }

  protected ReportDesign createExcelTemplateDesign() {
    final ReportDesign reportDesign = new ReportDesign();
    reportDesign.setName("ExcelTemplate");
    reportDesign.setRendererType(ExcelTemplateRenderer.class);
    return reportDesign;
  }
}
