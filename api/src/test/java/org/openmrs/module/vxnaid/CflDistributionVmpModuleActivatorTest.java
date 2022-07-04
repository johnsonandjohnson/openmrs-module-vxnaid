package org.openmrs.module.vxnaid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openmrs.module.emrapi.utils.MetadataUtil;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MetadataUtil.class)
public class CflDistributionVmpModuleActivatorTest {
  @Test
  public void started_shouldSetupStandardMetadata() throws Exception {
    PowerMockito.mockStatic(MetadataUtil.class);

    final CflDistributionVmpModuleActivator activator = new CflDistributionVmpModuleActivator();
    activator.started();

    PowerMockito.verifyStatic();
    MetadataUtil.setupStandardMetadata(Mockito.any(ClassLoader.class));
  }
}
