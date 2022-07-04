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

public class DoseStatus extends VMPBaseReportManager {
  @Override
  public String getUuid() {
    return "5b6d6e70-ae35-4e0b-827a-be1bc1895813";
  }

  @Override
  public String getName() {
    return "DoseStatus";
  }

  @Override
  public String getDescription() {
    return "DosingStatus";
  }

  @Override
  public List<Parameter> getParameters() {
    return emptyList();
  }

  @Override
  public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
    final ReportDesign design = createExcelTemplateDesign();
    design.setReportDefinition(reportDefinition);
    design.addPropertyValue("repeatingSections", "sheet:2,row:2,dataset:DosingData");

    final ReportDesignResource resource = createXLSXReportDesignResource();
    resource.setName("DoseDataV1Template");
    resource.setContents(ReportUtil.readByteArrayFromResource("reportTemplate/DoseDataV1Template.xlsx"));
    resource.setReportDesign(design);
    design.addResource(resource);
    return singletonList(design);
  }

  @Override
  public String getVersion() {
    return "1.2";
  }

  @Override
  protected SqlDataSetDefinition getOrCreateSqlDataSetDefinition() {
    final DataSetDefinitionService dataSetDefinitionService = Context.getService(DataSetDefinitionService.class);
    final String sqlDataDefinitionUuid = "66e894f1-21af-4e69-bfd7-38913f4265f2";

    SqlDataSetDefinition sqlDataSetDefinition =
        (SqlDataSetDefinition) dataSetDefinitionService.getDefinitionByUuid(sqlDataDefinitionUuid);

    if (sqlDataSetDefinition == null) {
      sqlDataSetDefinition = new SqlDataSetDefinition();
      sqlDataSetDefinition.setUuid(sqlDataDefinitionUuid);
      sqlDataSetDefinition.setName(getName());
      sqlDataSetDefinition.addParameters(getParameters());
      sqlDataSetDefinition.setSqlQuery(
          "SELECT * FROM openmrs.DoseData WHERE `Visit Status`='OCCURRED' and `name`='Dosing' and `Dose Number`!= 'Dose " +
              "99';");
      sqlDataSetDefinition = dataSetDefinitionService.saveDefinition(sqlDataSetDefinition);
    }

    return sqlDataSetDefinition;
  }
}
