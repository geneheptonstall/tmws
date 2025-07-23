package org.gkh.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gkh.config.OpenWeatherMapConfig;
import org.gkh.model.MelbourneWeatherDTO;
import org.gkh.model.OpenWeatherMapDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class OpenWeatherMapService implements MelbourneWeatherService {

    private final OpenWeatherMapConfig openWeatherMapConfig;
    private final RestClient openWeatherMapClient;

    @Override
    @Cacheable("melbourneWeather")
    public MelbourneWeatherDTO getWeather() {
        OpenWeatherMapDTO responseBody = null;
        try {
            responseBody = openWeatherMapClient.get()
                    .uri(openWeatherMapConfig.getWeatherUrl())
                    .retrieve()
                    .body(OpenWeatherMapDTO.class);
        } catch (RestClientResponseException e) {
            log.error("Error: {}", e.getStatusCode().value());
        }
        log.debug("openweathermap response: {}", responseBody != null && responseBody.getMain() != null && responseBody.getWind() != null ? responseBody.toString() : "null response!");

        return Optional.of(responseBody)
                .map(response -> MelbourneWeatherDTO.builder()
                        .temperatureDegrees(kelvinToCelsius(response.getMain().getTemp()))
                        .windSpeed(response.getWind().getSpeed())
                        .build())
                .orElse(null);
    }

    private String kelvinToCelsius(Float kelvinTemp) {
        final var diff = 273.15;
        return String.valueOf(kelvinTemp - diff);
    }
}
