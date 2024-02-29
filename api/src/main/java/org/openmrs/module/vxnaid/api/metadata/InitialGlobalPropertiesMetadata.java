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

import org.openmrs.module.cflcore.CFLConstants;
import org.openmrs.module.cflcore.api.util.GlobalPropertiesConstants;
import org.openmrs.module.metadatadeploy.bundle.VersionedMetadataBundle;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.globalProperty;

/**
 * Sets initial values to various Global Properties.
 */
public class InitialGlobalPropertiesMetadata extends VersionedMetadataBundle {
  private static final String BIOMETRIC_ENABLE_BIOMETRIC_FEATURE_NAME = "biometric.enable.biometric.feature";
  private static final String BIOMETRIC_ENABLE_BIOMETRIC_FEATURE_DESC = "Specifies whether the biometric feature is " +
      "enables to store and match participant's biometrics.";
  private static final String BIOMETRIC_ENABLE_BIOMETRIC_FEATURE_INIT_VALUE = "false";

  private static final String BIOMETRIC_ENABLE_MFA_NAME = "biometric.enable.mfa";
  private static final String BIOMETRIC_ENABLE_MFA_DESC = "Search the  biometric database with participantId along with iris template";
  private static final String BIOMETRIC_ENABLE_MFA_INIT_VALUE = "true";

  private static final String BIOMETRIC_SERVER_URL_NAME = "biometric.server.url";
  private static final String BIOMETRIC_SERVER_URL_DESC = "Specifies the biometric server url";
  private static final String BIOMETRIC_SERVER_URL_INIT_VALUE  = "Specifies the biometric server admin port";

  private static final String BIOMETRIC_ADMIN_PORT_NAME = "biometric.admin.port";
  private static final String BIOMETRIC_ADMIN_PORT_DESC = "Specifies the biometric server admin port";
  private static final String BIOMETRIC_ADMIN_PORT_INIT_VALUE = "24932";

  private static final String BIOMETRIC_CLIENT_PORT_NAME = "biometric.client.port";
  private static final String BIOMETRIC_CLIENT_PORT_DESC = "Specifies the biometric server client port";
  private static final String BIOMETRIC_CLIENT_PORT_INIT_VALUE  = "25452";

  private static final String BIOMETRIC_EXTRACTOR_LICENSE_NAME = "biometric.extractor.license";
  private static final String BIOMETRIC_EXTRACTOR_LICENSE_DESC = "";
  private static final String BIOMETRIC_EXTRACTOR_LICENSE_INIT_VALUE = "";

  private static final String BIOMETRIC_IMPLEMENTATION_CONFIG_NAME = "biometric.implementation.config";
  private static final String BIOMETRIC_IMPLEMENTATION_CONFIG_DESC = "Specifies the config uuids required for this module";
  private static final String BIOMETRIC_IMPLEMENTATION_CONFIG_INIT_VALUE = "";

  private static final String BIOMETRIC_MATCHING_THRESHOLD_NAME = "biometric.matching.threshold";
  private static final String BIOMETRIC_MATCHING_THRESHOLD_DESC = "Specifies the biometric iris scan matching threshold";
  private static final String BIOMETRIC_MATCHING_THRESHOLD_INIT_VALUE= "60";

  private static final String BIOMETRIC_API_CONFIG_LOCALIZATION_NAME = "biometric.api.config.localization";
  private static final String BIOMETRIC_API_CONFIG_LOCALIZATION_DESC = "Specifies the biometric localization.";
  private static final String BIOMETRIC_API_CONFIG_LOCALIZATION_INIT_VALUE = "{\n" +
      "  \"localization\": {\n" +
      "    \"fr\": {\n" +
      "      \"State\": \"État\",\n" +
      "      \"City\": \"Ville\",\n" +
      "      \"Postal Code\": \"code postal\",\n" +
      "      \"Province\": \"Province\",\n" +
      "      \"District\": \"Quartier\",\n" +
      "      \"ZIP Code\": \"Code postal\",\n" +
      "      \"Greater area\": \"Grande zone\",\n" +
      "      \"Street\": \"rue\",\n" +
      "      \"Manufacturer\": \"Fabricant\",\n" +
      "      \"Covid 1D vaccine\": \"Vaccin Covid 1D\",\n" +
      "      \"Covid 2D vaccine\": \"Vaccin Covid 2D\",\n" +
      "      \"Covid 3D vaccine\": \"Vaccin Covid 3D\",\n" +
      "      \"Antwerpen\": \"Anvers\",\n" +
      "      \"Belgium\": \"Belgique\",\n" +
      "      \"Brussel\": \"Bruxelles\",\n" +
      "      \"English\": \"anglais\",\n" +
      "      \"French\": \"français\",\n" +
      "      \"Dutch\": \"néerlandais\",\n" +
      "      \"Bangalore clinic\": \"Hôpital de Bangalore\",\n" +
      "      \"Bogota General Hospital\": \"Hôpital Général de Bogota\"\n" +
      "    },\n" +
      "    \"nl\": {\n" +
      "      \"State\": \"Staat\",\n" +
      "      \"City\": \"stad\",\n" +
      "      \"Postal Code\": \"Postcode\",\n" +
      "      \"Province\": \"Provincie\",\n" +
      "      \"District\": \"Wijk\",\n" +
      "      \"ZIP Code\": \"Postcode\",\n" +
      "      \"Greater area\": \"Groter gebied\",\n" +
      "      \"Street\": \"Straat\",\n" +
      "      \"Manufacturer\": \"Fabrikant\",\n" +
      "      \"Covid 1D vaccine\": \"Covid 1D-vaccin\",\n" +
      "      \"Covid 2D vaccine\": \"Covid 2D-vaccin\",\n" +
      "      \"Covid 3D vaccine\": \"Covid 3D-vaccin\"\n" +
      "    },\n" +
      "    \"hi\": {\n" +
      "      \"State\": \"राज्य\",\n" +
      "      \"City\": \"शहर\",\n" +
      "      \"Postal Code\": \"डाक कोड\",\n" +
      "      \"Province\": \"प्रांत\",\n" +
      "      \"District\": \"जिला\",\n" +
      "      \"ZIP Code\": \"ज़िप कोड\",\n" +
      "      \"Greater area\": \"value2\",\n" +
      "      \"Street\": \"मार्ग\",\n" +
      "      \"Manufacturer\": \"उत्पादक\",\n" +
      "      \"Covid 1D vaccine\": \"कोविद 1 डी वैक्सीन\",\n" +
      "      \"Covid 2D vaccine\": \"कोविद 2 डी वैक्सीन\",\n" +
      "      \"Covid 3D vaccine\": \"कोविद 3 डी वैक्सीन\"\n" +
      "    }\n" +
      "  }\n" +
      "}";

  private static final String BIOMETRIC_API_CONFIG_NAME = "biometric.api.config.main";
  private static final String BIOMETRIC_API_CONFIG_DESC = "";
  private static final String BIOMETRIC_API_CONFIG_INIT_VALUE = "{\n" +
      "\"syncScope\":\"site\",\n" +
      "   \"operatorCredentialsRetentionTime\":86400000,\n" +
      "   \"operatorOfflineSessionTimeout\":86400000,\n" +
      "   \"vaccine\":[\n" +
      "      {\n" +
      "         \"name\":\"Vacc1\",\n" +
      "         \"manufacturers\":[\n" +
      "            \"JNJ\"\n" +
      "         ]\n" +
      "      }\n" +
      "   ],\n" +
      "   \"canUseDifferentManufacturers\":true,\n" +
      "   \"manufacturers\":[\n" +
      "      {\n" +
      "         \"name\":\"JNJ\",\n" +
      "         \"barcodeRegex\":\".*\"\n" +
      "      }\n" +
      "   ],\n" +
      "   \"personLanguages\":[\n" +
      "      {\n" +
      "         \"name\":\"English\"\n" +
      "      }\n" +
      "   ],\n" +
      "   \"authSteps\":[\n" +
      "      {\n" +
      "         \"mandatory\":false,\n" +
      "         \"type\":\"id_card\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"mandatory\":false,\n" +
      "         \"type\":\"phone\"\n" +
      "      },\n" +
      "      {\n" +
      "         \"mandatory\":false,\n" +
      "         \"type\":\"iris_scan\"\n" +
      "      }\n" +
      "   ],\n" +
      "   \"irisScore\":50,\n" +
      "   \"addressFields\":{\n" +
      "      \"Belgium\":[\n" +
      "         {\n" +
      "            \"field\":\"cityVillage\",\n" +
      "            \"type\":\"DROPDOWN\",\n" +
      "            \"name\":\"City\",\n" +
      "            \"displayOrder\":1\n" +
      "         },\n" +
      "         {\n" +
      "            \"field\":\"stateProvince\",\n" +
      "            \"type\":\"DROPDOWN\",\n" +
      "            \"name\":\"Postalcode\",\n" +
      "            \"displayOrder\":2\n" +
      "         },\n" +
      "         {\n" +
      "            \"field\":\"address1\",\n" +
      "            \"type\":\"FREE_INPUT\",\n" +
      "            \"name\":\"Street\",\n" +
      "            \"displayOrder\":3\n" +
      "         },\n" +
      "         {\n" +
      "            \"field\":\"address2\",\n" +
      "            \"type\":\"FREE_INPUT\",\n" +
      "            \"name\":\"Number\",\n" +
      "            \"displayOrder\":4\n" +
      "         }\n" +
      "      ],\n" +
      "      \"United States of America\":[\n" +
      "         {\n" +
      "            \"field\":\"cityVillage\",\n" +
      "            \"type\":\"DROPDOWN\",\n" +
      "            \"name\":\"State\",\n" +
      "            \"displayOrder\":1\n" +
      "         },\n" +
      "         {\n" +
      "            \"field\":\"stateProvince\",\n" +
      "            \"type\":\"DROPDOWN\",\n" +
      "            \"name\":\"City\",\n" +
      "            \"displayOrder\":2\n" +
      "         },\n" +
      "         {\n" +
      "            \"field\":\"address1\",\n" +
      "            \"type\":\"FREE_INPUT\",\n" +
      "            \"name\":\"Postcode\",\n" +
      "            \"displayOrder\":3\n" +
      "         },\n" +
      "         {\n" +
      "            \"field\":\"address2\",\n" +
      "            \"type\":\"FREE_INPUT\",\n" +
      "            \"name\":\"Street\",\n" +
      "            \"displayOrder\":4\n" +
      "         }\n" +
      "      ]\n" +
      "   },\n" +
      "   \"allowManualParticipantIDEntry\":true,\n" +
      "   \"participantIDRegex\":\"\"\n" +
      "}";

  private static final String BIOMETRIC_API_CONFIG_VERSION_NAME = "biometric.api.config.version";
  private static final String BIOMETRIC_API_CONFIG_VERSION_DESC = "Specifies the app version details";
  private static final String BIOMETRIC_API_CONFIG_VERSION_INIT_VALUE =
      "{\n" + "  \"version\": \"\",\n" + "  \"url\": \"\"\n" + "}";

  private static final String VACCINATION_INFORMATION_ENABLED_VMP_VALUE = "true";

  @Override
  public int getVersion() {
    return 1;
  }

  @Override
  protected void installEveryTime() {
    // Ensures the Vxnaid app works correctly with CFL BE
    install(globalProperty(GlobalPropertiesConstants.SHOULD_CREATE_FIRST_VISIT.getKey(),
        GlobalPropertiesConstants.SHOULD_CREATE_FIRST_VISIT.getDescription(), "false"));
    install(globalProperty(GlobalPropertiesConstants.SHOULD_CREATE_FUTURE_VISITS.getKey(),
        GlobalPropertiesConstants.SHOULD_CREATE_FUTURE_VISITS.getDescription(), "true"));
  }

  @Override
  protected void installNewVersion() {
    install(globalProperty(BIOMETRIC_ENABLE_BIOMETRIC_FEATURE_NAME, BIOMETRIC_ENABLE_BIOMETRIC_FEATURE_DESC,
        BIOMETRIC_ENABLE_BIOMETRIC_FEATURE_INIT_VALUE));
    install(globalProperty(BIOMETRIC_ENABLE_MFA_NAME, BIOMETRIC_ENABLE_MFA_DESC, BIOMETRIC_ENABLE_MFA_INIT_VALUE));
    install(globalProperty(BIOMETRIC_SERVER_URL_NAME, BIOMETRIC_SERVER_URL_DESC, BIOMETRIC_SERVER_URL_INIT_VALUE));
    install(globalProperty(BIOMETRIC_ADMIN_PORT_NAME, BIOMETRIC_ADMIN_PORT_DESC, BIOMETRIC_ADMIN_PORT_INIT_VALUE));
    install(globalProperty(BIOMETRIC_CLIENT_PORT_NAME, BIOMETRIC_CLIENT_PORT_DESC, BIOMETRIC_CLIENT_PORT_INIT_VALUE));
    install(globalProperty(BIOMETRIC_EXTRACTOR_LICENSE_NAME, BIOMETRIC_EXTRACTOR_LICENSE_DESC,
        BIOMETRIC_EXTRACTOR_LICENSE_INIT_VALUE));
    install(globalProperty(BIOMETRIC_IMPLEMENTATION_CONFIG_NAME, BIOMETRIC_IMPLEMENTATION_CONFIG_DESC,
        BIOMETRIC_IMPLEMENTATION_CONFIG_INIT_VALUE));
    install(globalProperty(BIOMETRIC_MATCHING_THRESHOLD_NAME, BIOMETRIC_MATCHING_THRESHOLD_DESC,
        BIOMETRIC_MATCHING_THRESHOLD_INIT_VALUE));
    install(globalProperty(BIOMETRIC_API_CONFIG_LOCALIZATION_NAME, BIOMETRIC_API_CONFIG_LOCALIZATION_DESC,
        BIOMETRIC_API_CONFIG_LOCALIZATION_INIT_VALUE));
    install(globalProperty(BIOMETRIC_API_CONFIG_NAME, BIOMETRIC_API_CONFIG_DESC, BIOMETRIC_API_CONFIG_INIT_VALUE));
    install(globalProperty(BIOMETRIC_API_CONFIG_VERSION_NAME, BIOMETRIC_API_CONFIG_VERSION_DESC,
        BIOMETRIC_API_CONFIG_VERSION_INIT_VALUE));
    install(globalProperty(CFLConstants.VACCINATION_LISTENER_KEY, CFLConstants.VACCINATION_LISTENER_DESCRIPTION,
        CFLConstants.VACCINATION_VISIT_LISTENER_NAME));
    install(globalProperty(CFLConstants.VACCINATION_INFORMATION_ENABLED_KEY,
        CFLConstants.VACCINATION_INFORMATION_ENABLED_KEY_DESCRIPTION, VACCINATION_INFORMATION_ENABLED_VMP_VALUE));
  }
}
