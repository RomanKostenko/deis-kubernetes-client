package online.happymama.autoconfigure;

import online.happymama.service.KubernetesGetServiceUrl;
import online.happymama.service.impl.KubernetesGetServiceUrlImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by admin on 11/13/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = KubernetesGetServiceUrlAutoConfiguration.class)
public class KubernetesGetServiceUrlAutoConfigurationTest {

    @Autowired
    private KubernetesGetServiceUrl kubernetesGetServiceUrl;

    @Test
    public void testKubernetesGetServiceUrl() {
        assertTrue(kubernetesGetServiceUrl instanceof KubernetesGetServiceUrlImpl);
    }
}
