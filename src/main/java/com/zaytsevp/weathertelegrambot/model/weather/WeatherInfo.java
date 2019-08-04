package com.zaytsevp.weathertelegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Составная информация о погоде
 *
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    /** общая информация о температуре */
//    @JsonProperty(value = "main")
    private Main main;

    /** общая информация о погоде */
//    @JsonProperty(value = "weather")
    private List<Weather> weather;
}
