package org.gkh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gkh.model.MelbourneWeatherDTO;
import org.gkh.service.MelbourneWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final MelbourneWeatherService weatherStackService;
    private final MelbourneWeatherService openWeatherMapService;

    @GetMapping("ping")
    public @ResponseBody MelbourneWeatherDTO pingWeather() {
        return Optional.ofNullable(weatherStackService.getWeather())
                .orElseGet(openWeatherMapService::getWeather);
    }
}
