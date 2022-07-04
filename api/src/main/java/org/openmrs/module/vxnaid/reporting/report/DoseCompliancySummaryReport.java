/*
 *  This Source Code Form is subject to the terms of the Mozilla Public License,
 *  v. 2.0. If a copy of the MPL was not distributed with this file, You can
 *  obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 *  the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *  <p>
 *  Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.vxnaid.reporting.report;

import org.openmrs.api.context.Context;
import org.openmrs.module.vxnaid.reporting.report.util.VMPBaseReportManager;
import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.service.DataSetDefinitionService;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.util.ReportUtil;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class DoseCompliancySummaryReport extends VMPBaseReportManager {
  @Override
  public String getUuid() {
    return "480fdc06-39cb-4fd4-993e-3d29585b1db9";
  }

  @Override
  public String getName() {
    return "DosingCompliancy Summary Report";
  }

  @Override
  public String getDescription() {
    return "Aggregation of detail report Dosing Compliancy";
  }

  @Override
  public List<Parameter> getParameters() {
    return emptyList();
  }

  @Override
  public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
    final ReportDesign design = createExcelTemplateDesign();
    design.setReportDefinition(reportDefinition);
    design.addPropertyValue("repeatingSections", "sheet:2,row:2,dataset:DoseCompliancyAggregate");

    final ReportDesignResource resource = createXLSXReportDesignResource();
    resource.setName("DoseCompliancyAggTemplate");
    resource.setContents(ReportUtil.readByteArrayFromResource("reportTemplate/DoseCompliancyAggTemplate.xlsx"));
    resource.setReportDesign(design);
    design.addResource(resource);
    return singletonList(design);
  }

  @Override
  public String getVersion() {
    return "1.3";
  }

  @Override
  protected SqlDataSetDefinition getOrCreateSqlDataSetDefinition() {
    final DataSetDefinitionService dataSetDefinitionService = Context.getService(DataSetDefinitionService.class);
    final String sqlDataDefinitionUuid = "815c6883-78f9-4774-9d76-8dd25244b855";

    SqlDataSetDefinition sqlDataSetDefinition =
        (SqlDataSetDefinition) dataSetDefinitionService.getDefinitionByUuid(sqlDataDefinitionUuid);

    if (sqlDataSetDefinition == null) {
      sqlDataSetDefinition = new SqlDataSetDefinition();
      sqlDataSetDefinition.setUuid(sqlDataDefinitionUuid);
      sqlDataSetDefinition.setName(getName());
      sqlDataSetDefinition.addParameters(getParameters());
      sqlDataSetDefinition.setSqlQuery("SELECT * FROM openmrs.DoseCompliancyAggregate;");
      sqlDataSetDefinition = dataSetDefinitionService.saveDefinition(sqlDataSetDefinition);
    }

    return sqlDataSetDefinition;
  }
}
