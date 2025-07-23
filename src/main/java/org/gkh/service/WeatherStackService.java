package org.gkh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gkh.config.WeatherStackConfig;
import org.gkh.model.MelbourneWeatherDTO;
import org.gkh.model.WeatherStackDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class WeatherStackService implements MelbourneWeatherService {

    private final WeatherStackConfig weatherStackConfig;
    private final RestClient weatherStackClient;

    @Override
    @Cacheable("melbourneWeather")
    public MelbourneWeatherDTO getWeather() {
        WeatherStackDTO responseBody = null;
        try {
            responseBody = weatherStackClient.get()
                    .uri(weatherStackConfig.getWeatherUrl())
                    .retrieve()
                    .body(WeatherStackDTO.class);
        } catch (RestClientResponseException e) {
            log.error("Error: {}", e.getStatusCode().value());
        }
        log.debug("weatherstack response: {}", responseBody != null ? responseBody.toString() : "null response!");

        return Optional.ofNullable(responseBody)
                .map(response -> MelbourneWeatherDTO.builder()
                        .temperatureDegrees(response.getCurrent().getTemperature())
                        .windSpeed(response.getCurrent().getWindSpeed())
                        .build())
                .orElse(null);
    }
}
