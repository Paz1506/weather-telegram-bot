package com.zaytsevp.weathertelegrambot.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Общая информация о погоде
 *
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    /** идентификатор погоды */
    private Integer id;

    /** общая краткая информация */
    private String main;

    /** описание */
    private String description;
}
