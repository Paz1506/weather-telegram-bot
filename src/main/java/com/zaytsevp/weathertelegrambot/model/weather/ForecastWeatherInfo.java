package com.zaytsevp.weathertelegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Составная информация о погоде для прогноза на несколько дней
 *
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastWeatherInfo {

    @JsonProperty(value = "list")
    private List<WeatherInfo> list;
}
