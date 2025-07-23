package org.gkh.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WeatherStackDTO implements Serializable {

    private Current current;

    @Data
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Current {
        private String observationTime;
        private String temperature;
        private String windSpeed;
    }
}
