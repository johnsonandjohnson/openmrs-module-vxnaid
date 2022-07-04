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

import org.apache.commons.lang3.StringUtils;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.cfl.api.builder.MessageTemplateBuilder;
import org.openmrs.module.cfl.api.builder.MessageTemplateFieldBuilder;
import org.openmrs.module.messages.api.model.Template;
import org.openmrs.module.messages.api.model.TemplateField;
import org.openmrs.module.messages.api.model.TemplateFieldType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitReminderMetadata extends AbstractMessageServiceMetadata {
  private static final int VERSION = 2;

  public VisitReminderMetadata(DbSessionFactory dbSessionFactory) {
    super(dbSessionFactory, VERSION, "95573fe3-20b2-11ea-ac12-0242c0a82002");
  }

  @Override
  protected Template createTemplate() throws IOException {
    final String visitReminderServiceQuery = getVisitReminderServiceQuery();
    final String visitReminderCalendarServiceQuery = getVisitReminderCalendarServiceQuery();

    return MessageTemplateBuilder.buildMessageTemplate(visitReminderServiceQuery, SQL_QUERY_TYPE,
        visitReminderCalendarServiceQuery, "Visit reminder", true, templateUuid);
  }

  @Override
  protected void updateTemplate(Template template) throws IOException {
    final String visitReminderServiceQuery = getVisitReminderServiceQuery();
    final String visitReminderCalendarServiceQuery = getVisitReminderCalendarServiceQuery();

    template.setServiceQuery(visitReminderServiceQuery);
    template.setCalendarServiceQuery(visitReminderCalendarServiceQuery);
    template.setShouldUseOptimizedQuery(true);
  }

  @Override
  protected void createAndSaveTemplateFields(Template template) {
    List<TemplateField> templateFields = new ArrayList<>();

    if (isTemplateFieldNotExist("95574976-20b2-11ea-ac12-0242c0a82002")) {
      templateFields.add(
          MessageTemplateFieldBuilder.buildMessageTemplateField("Service type", true, "Deactivate service", template,
              TemplateFieldType.SERVICE_TYPE, "Deactivate service|SMS|Call", "95574976-20b2-11ea-ac12-0242c0a82002"));
    }

    if (isTemplateFieldNotExist("95575327-20b2-11ea-ac12-0242c0a82002")) {
      templateFields.add(MessageTemplateFieldBuilder.buildMessageTemplateField("Start of messages", false, "", template,
          TemplateFieldType.START_OF_MESSAGES, null, "95575327-20b2-11ea-ac12-0242c0a82002"));
    }

    if (isTemplateFieldNotExist("95575cbd-20b2-11ea-ac12-0242c0a82002")) {
      templateFields.add(
          MessageTemplateFieldBuilder.buildMessageTemplateField("End of messages", true, "NO_DATE|EMPTY", template,
              TemplateFieldType.END_OF_MESSAGES, null, "95575cbd-20b2-11ea-ac12-0242c0a82002"));
    }

    templateFields.forEach(getTemplateFieldService()::saveOrUpdate);
  }

  @Override
  protected void performAdditionalUpdate() throws IOException {
    final String functionCreateQuery =
        metadataSQLScriptRunner.getQueryFromResource(SERVICES_BASE_PATH + "VisitReminder/VisitReminderStartDateFunction.sql",
            getClass().getClassLoader());

    metadataSQLScriptRunner.executeQuery("DROP FUNCTION IF EXISTS GET_PREDICTION_START_DATE_FOR_VISIT;");
    metadataSQLScriptRunner.executeQuery(functionCreateQuery);

    createRelatedGlobalProperty("message.daysToCallBeforeVisit.default", "1,7",
        "Used to determine the how many days before visit reminder should be scheduled. " +
            "Note: if the property will store negative values then " + "the visit reminder will be sent after visit.");
  }

  private void createRelatedGlobalProperty(String key, String value, String description) {
    String existSetting = Context.getAdministrationService().getGlobalProperty(key);
    if (StringUtils.isBlank(existSetting)) {
      GlobalProperty gp = new GlobalProperty(key, value, description);
      Context.getAdministrationService().saveGlobalProperty(gp);
    }
  }

  private String getVisitReminderServiceQuery() throws IOException {
    return metadataSQLScriptRunner.getQueryFromResource(SERVICES_BASE_PATH + "VisitReminder/VisitReminder.sql",
        getClass().getClassLoader());
  }

  private String getVisitReminderCalendarServiceQuery() throws IOException {
    return metadataSQLScriptRunner.getQueryFromResource(
        SERVICES_BASE_PATH + "VisitReminder/VisitReminderCalendarQuery" + ".sql", getClass().getClassLoader());
  }
}
