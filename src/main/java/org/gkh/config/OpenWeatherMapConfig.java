package org.gkh.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "open-weather-map")
public class OpenWeatherMapConfig {

    @Getter
    private final String baseUrl;
    private final String url;
    private final String key;
    private final String lat;
    private final String lon;

    public String getWeatherUrl() {
        return url.formatted(lat, lon, key);
    }

    @Bean(name = "openWeatherMapClient")
    public RestClient getRestClient() {
        return RestClient.builder()
                .baseUrl(getBaseUrl())
                .build();
    }
}