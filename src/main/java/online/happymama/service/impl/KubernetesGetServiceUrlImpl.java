package online.happymama.service.impl;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import online.happymama.service.KubernetesGetServiceUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.text.MessageFormat;

/**
 * Created by admin on 11/13/16.
 */
@org.springframework.stereotype.Service
@Profile("default")
public class KubernetesGetServiceUrlImpl implements KubernetesGetServiceUrl {

    private static final Logger logger = LoggerFactory.getLogger(KubernetesGetServiceUrlImpl.class);

    /**
     * Define http or https protocol for in cluster connection between services
     */
    private final String protocol;

    private final KubernetesClient kubernetesClient;

    @Autowired
    public KubernetesGetServiceUrlImpl(KubernetesClient kubernetesClient,
                                       @Value("${deis.protocol:https}") String protocol) {

        this.kubernetesClient = kubernetesClient;
        this.protocol = protocol;

        logger.debug("Use {} as protocol", protocol);
    }

    @Override
    public String getServiceUrl(String serviceName) {
        logger.debug("Get service url for: {}", serviceName);
        Service service = getService(serviceName);

        String url = protocol + "://" + service.getSpec().getClusterIP();
        logger.debug("Service url: {}", url);

        return url;
    }

    Service getService(String serviceName) {
        Service service = kubernetesClient.services().inNamespace(serviceName).withName(serviceName).get();

        if (service == null) {
            throw new IllegalArgumentException(MessageFormat.format(
                    "There isn't any service {0} in namespace {0}", serviceName));
        }
        return service;
    }
}
