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

import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.module.vxnaid.reporting.report.util.VMPBaseReportManager;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class DoseEvolutionSummaryAggregation extends VMPBaseReportManager {
  private static final String DATA_SET_NAME = "DoseEvolutionAgg";

  protected DoseEvolutionSummaryAggregation() {
    super("e145cf0f-6717-4ec1-ab21-313bb6337f4a", DATA_SET_NAME);
  }

  @Override
  public String getUuid() {
    return "8e851128-5f7b-46fa-a4c2-b442af5820a2";
  }

  @Override
  public String getName() {
    return "Dose Evolution Summary Aggregation";
  }

  @Override
  public String getDescription() {
    return "Aggregation of Dose Evolution for Summary reports";
  }

  @Override
  public List<Parameter> getParameters() {
    return emptyList();
  }

  @Override
  public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
    final ReportDesign design = createExcelTemplateDesign();
    design.setReportDefinition(reportDefinition);
    design.addPropertyValue("repeatingSections", "sheet:2,row:2,dataset:" + DATA_SET_NAME);

    final ReportDesignResource resource = createXLSXReportDesignResource();
    resource.setName("DoseDataProgressionAggTemplate");
    resource.setContents(ReportUtil.readByteArrayFromResource("reportTemplate/DoseDataProgressionAggTemplate.xlsx"));
    resource.setReportDesign(design);
    design.addResource(resource);
    return singletonList(design);
  }

  @Override
  public String getVersion() {
    return "1.3";
  }
}
