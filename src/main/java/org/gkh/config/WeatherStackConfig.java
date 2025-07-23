package org.gkh.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "weather-stack")
public class WeatherStackConfig {

    @Getter
    private final String baseUrl;
    private final String url;
    private final String key;

    public String getWeatherUrl() {
        return url.formatted(key);
    }

    @Bean(name = "weatherStackClient")
    public RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(getBaseUrl())
                .build();
    }
}
