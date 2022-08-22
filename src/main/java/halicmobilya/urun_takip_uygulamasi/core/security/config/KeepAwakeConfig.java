package halicmobilya.urun_takip_uygulamasi.core.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class KeepAwakeConfig {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String pingUrl = "http://localhost:8080/ping";
    // private final String pingUrl = "https://api-first-java-backend-project.herokuapp.com/ping";

    @Scheduled(fixedDelay = 30 * 59 * 1000)
    public void keepAwake() {
        String result = restTemplate.getForObject(pingUrl, String.class);
    }
}
