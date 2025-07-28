package org.gkh.controller;

import org.gkh.model.MelbourneWeatherDTO;
import org.gkh.service.OpenWeatherMapService;
import org.gkh.service.WeatherStackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherStackService weatherstackService;

    @MockitoBean
    private OpenWeatherMapService openweathermapService;

    @Test
    void pingWeather_shouldReturnWeatherStackResponse() throws Exception {
        MelbourneWeatherDTO expected = MelbourneWeatherDTO.builder()
                .temperatureDegrees("20")
                .windSpeed("10")
                .build();
        when(weatherstackService.getWeather()).thenReturn(expected);
        this.mockMvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.temperature_degrees").value(expected.getTemperatureDegrees()));
    }

    @Test
    void pingWeather_shouldReturnOpenWeatherMapResponse() throws Exception {
        MelbourneWeatherDTO expected = MelbourneWeatherDTO.builder()
                .temperatureDegrees("20")
                .windSpeed("10")
                .build();
        when(weatherstackService.getWeather()).thenReturn(null);
        when(openweathermapService.getWeather()).thenReturn(expected);
        this.mockMvc.perform(get("/ping"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.temperature_degrees").value(expected.getTemperatureDegrees()));
    }
}
