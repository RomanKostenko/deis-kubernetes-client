package online.happymama.service.impl;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import online.happymama.autoconfigure.KubernetesGetServiceUrlAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by admin on 11/13/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"deis.protocol=https"})
@ContextConfiguration(classes = KubernetesGetServiceUrlAutoConfiguration.class)
public class KubernetesGetServiceUrlImplTest {

    @Mock
    private Service service;

    @Mock
    private ServiceSpec serviceSpec;

    @Autowired
    private KubernetesGetServiceUrlImpl kubernetesGetServiceUrl;

    @Test
    public void testKubernetesGetServiceUrl() {
        kubernetesGetServiceUrl = spy(kubernetesGetServiceUrl);

        willReturn(service).given(kubernetesGetServiceUrl).getService("test.org");
        given(service.getSpec()).willReturn(serviceSpec);
        given(serviceSpec.getClusterIP()).willReturn("127.0.0.1");

        String serviceUrl = kubernetesGetServiceUrl.getServiceUrl("test.org");

        assertEquals("https://127.0.0.1", serviceUrl);
    }

}
