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

public class DoseEvolution extends VMPBaseReportManager {
  private static final String DATA_SET_NAME = "DoseEvolution";

  protected DoseEvolution() {
    super("342b11b6-5dab-4124-b828-64fccb3aa24b", DATA_SET_NAME);
  }

  @Override
  public String getUuid() {
    return "2d532408-84aa-48e1-84ab-9d862630aa78";
  }

  @Override
  public String getName() {
    return DATA_SET_NAME;
  }

  @Override
  public String getDescription() {
    return "Dose Evolution";
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
    resource.setName("DoseDataProgressionTemplate");
    resource.setContents(ReportUtil.readByteArrayFromResource("reportTemplate/DoseDataProgressionTemplate.xlsx"));
    resource.setReportDesign(design);
    design.addResource(resource);
    return singletonList(design);
  }

  @Override
  public String getVersion() {
    return "1.3";
  }
}
