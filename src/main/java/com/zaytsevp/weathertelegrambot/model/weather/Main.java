package com.zaytsevp.weathertelegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Общая информация о температуре
 *
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    /** текущая температура */
    private Double temp;

    /** давление */
    private Integer pressure;

    /** минимальная температура */
    @JsonProperty(value = "temp_min")
    private Double tempMin;

    /** максимальная температура */
    @JsonProperty(value = "temp_max")
    private Double tempMax;

    /** давление */
    private Integer humidity;
}
