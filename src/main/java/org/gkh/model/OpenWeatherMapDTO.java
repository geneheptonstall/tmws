package org.gkh.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OpenWeatherMapDTO {

    private Main main;
    private Wind wind;

    @Data
    @ToString
    public static class Main {
        private Float temp;
    }

    @Data
    @ToString
    public static class Wind {
        private String speed;
    }
}
