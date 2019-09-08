package com.zaytsevp.weathertelegrambot.model.geocoder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pavel Zaytsev
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoObject {

    @JsonProperty("name")
    private String name;

    @JsonProperty("metaDataProperty")
    private MetaDataProperty metaDataProperty;

    @JsonProperty("Point")
    private Point point;
}
