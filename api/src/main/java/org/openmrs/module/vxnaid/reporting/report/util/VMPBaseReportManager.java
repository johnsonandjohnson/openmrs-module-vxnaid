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

import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.BaseReportManager;
import org.openmrs.module.reporting.report.renderer.ExcelTemplateRenderer;

public abstract class VMPBaseReportManager extends BaseReportManager {
  @Override
  public ReportDefinition constructReportDefinition() {
    final ReportDefinition reportDefinition = new ReportDefinition();
    reportDefinition.setUuid(getUuid());
    reportDefinition.setName(getName());
    reportDefinition.setDescription(getDescription());
    reportDefinition.setParameters(getParameters());
    reportDefinition.addDataSetDefinition(getName(), Mapped.mapStraightThrough(getOrCreateSqlDataSetDefinition()));
    return reportDefinition;
  }

  protected abstract SqlDataSetDefinition getOrCreateSqlDataSetDefinition();

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
