package org.gkh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gkh.model.MelbourneWeatherDTO;
import org.gkh.service.MelbourneWeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final MelbourneWeatherService weatherStackService;
    private final MelbourneWeatherService openWeatherMapService;

    @GetMapping("ping")
    ResponseEntity<MelbourneWeatherDTO> pingWeather() {
        return Optional.ofNullable(weatherStackService.getWeather())
                .map(ResponseEntity::ok)
                .orElseGet(() -> Optional.ofNullable(openWeatherMapService.getWeather())
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }
}
