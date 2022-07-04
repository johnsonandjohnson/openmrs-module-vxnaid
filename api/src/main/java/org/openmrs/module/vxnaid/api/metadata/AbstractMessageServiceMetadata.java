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

import org.apache.commons.lang.StringUtils;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.cfl.api.util.MetadataSQLScriptRunner;
import org.openmrs.module.messages.api.model.Template;
import org.openmrs.module.messages.api.service.TemplateFieldService;
import org.openmrs.module.messages.api.service.TemplateService;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import java.io.IOException;
import java.util.Optional;

public abstract class AbstractMessageServiceMetadata extends VersionedMetadataBundle {

  protected static final String SERVICES_BASE_PATH = "mysql/services/";

  protected static final String SQL_QUERY_TYPE = "SQL";

  protected final MetadataSQLScriptRunner metadataSQLScriptRunner;
  protected final String templateUuid;
  private final int bundleVersion;

  protected AbstractMessageServiceMetadata(DbSessionFactory dbSessionFactory, int bundleVersion, String templateUuid) {
    this.metadataSQLScriptRunner = new MetadataSQLScriptRunner(dbSessionFactory);
    this.bundleVersion = bundleVersion;
    this.templateUuid = templateUuid;
  }

  @Override
  public int getVersion() {
    return bundleVersion;
  }

  @Override
  protected void installEveryTime() {
    // nothing to do
  }

  @Override
  protected void installNewVersion() throws Exception {
    final Template template = createOrUpdateTemplate();
    createAndSaveTemplateFields(template);
    performAdditionalUpdate();
  }

  protected Template createOrUpdateTemplate() throws IOException {
    final Optional<Template> currentTemplate = getCurrentTemplate();

    final Template template;

    if (currentTemplate.isPresent()) {
      template = currentTemplate.get();
      updateTemplate(template);
    } else {
      template = createTemplate();
    }

    return getTemplateService().saveOrUpdate(template);
  }

  protected abstract Template createTemplate() throws IOException;

  protected abstract void updateTemplate(Template template) throws IOException;

  protected abstract void createAndSaveTemplateFields(Template template);

  protected abstract void performAdditionalUpdate() throws IOException;

  protected boolean isTemplateFieldNotExist(String fieldUuid) {
    return getTemplateFieldService().getByUuid(fieldUuid) == null;
  }

  protected TemplateService getTemplateService() {
    return Context.getRegisteredComponent("messages.templateService", TemplateService.class);
  }

  protected TemplateFieldService getTemplateFieldService() {
    return Context.getRegisteredComponent("messages.templateFieldService", TemplateFieldService.class);
  }

  private Optional<Template> getCurrentTemplate() {
    return getTemplateService()
        .getAll(true)
        .stream()
        .filter(t -> StringUtils.equalsIgnoreCase(t.getUuid(), templateUuid))
        .findFirst();
  }
}
