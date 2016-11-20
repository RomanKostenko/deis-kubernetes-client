package online.happymama.service;

/**
 * Created by admin on 11/13/16.
 */
public interface KubernetesGetServiceUrl {

    /**
     * Return in cluster url to the service
     * @param serviceName name of service
     * @return in cluster url to the service
     */
    String getServiceUrl(String serviceName);
}