package online.happymama.service.impl;

import online.happymama.service.KubernetesGetServiceUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Service provides in cluster url for service by service name.<br>
 * It's used for service to service in cluster communication without going thought load balancer.
 *
 * Created by admin on 11/13/16.
 */
@Service
@Profile("dev")
public class KubernetesGetServiceUrlDevImpl implements KubernetesGetServiceUrl {

    private static final Logger logger = LoggerFactory.getLogger(KubernetesGetServiceUrlDevImpl.class);

    /**
     * Host or domain of load balancer
     */
    private final String domain;

    /**
     * Define http or https protocol for in cluster connection between services
     */
    private final String protocol;

    public KubernetesGetServiceUrlDevImpl(@Value("${deis.domain}") String domain,
                                          @Value("${deis.protocol:https}") String protocol) {

        if ("${deis.domain}".equals(domain)) {
            throw new IllegalArgumentException("deis.domain is empty. Provide domain or host in the deis.domain" +
                    " environments property. For example: test.org or 192.214.12.12");
        }

        this.domain = domain;
        logger.debug("Use {} as domain or host", domain);

        this.protocol = protocol;
        logger.debug("Use {} as protocol", protocol);
    }

    @Override
    public String getServiceUrl(String serviceName) {
        logger.debug("Get service url for: {}", serviceName);
        String url = protocol + "://" + serviceName + "." + domain;
        logger.debug("Service url: {}", url);

        return url;
    }
}
