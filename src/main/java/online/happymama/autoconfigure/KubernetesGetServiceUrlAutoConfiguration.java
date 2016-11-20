package online.happymama.autoconfigure;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 11/13/16.
 */
@Configuration
@ComponentScan("online.happymama.service")
public class KubernetesGetServiceUrlAutoConfiguration {

    @Bean
    public KubernetesClient getKubernetesClient() {
        return new DefaultKubernetesClient();
    }
}
