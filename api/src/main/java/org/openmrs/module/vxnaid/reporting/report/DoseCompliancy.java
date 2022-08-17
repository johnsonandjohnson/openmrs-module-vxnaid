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

public class DoseCompliancy extends VMPBaseReportManager {
  private static final String DATA_SET_NAME = "DoseCompliancyDetail";

  protected DoseCompliancy() {
    super("e0f71635-72e2-45a6-abae-1c24993a2b27", DATA_SET_NAME);
  }

  @Override
  public String getUuid() {
    return "c33c2228-90d2-4b95-a5be-d9aa119981a5";
  }

  @Override
  public String getName() {
    return "DosingCompliancy";
  }

  @Override
  public String getDescription() {
    return "DosingCompliancy";
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
    resource.setName("DoseCompliancyTemplate");
    resource.setContents(ReportUtil.readByteArrayFromResource("reportTemplate/DoseCompliancyTemplate.xlsx"));
    resource.setReportDesign(design);
    design.addResource(resource);
    return singletonList(design);
  }

  @Override
  public String getVersion() {
    return "1.3";
  }
}
