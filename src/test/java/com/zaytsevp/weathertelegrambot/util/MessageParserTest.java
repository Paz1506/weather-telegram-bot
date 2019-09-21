package com.zaytsevp.weathertelegrambot.util;

import com.zaytsevp.weathertelegrambot.model.telegram.MessageParameterType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageParserTest {

    private final String city = "Хабаровск";

    private final String param = "завтра";

    @Test
    void getWeatherParams() {
        // Actual
        Map<MessageParameterType, String> actualResult = MessageParser.getWeatherParams(city + " " + param);

        // Assertion
        Assertions.assertThat(actualResult.size()).isEqualTo(2);
        Assertions.assertThat(actualResult.get(MessageParameterType.CITY)).isEqualTo(city);
        Assertions.assertThat(actualResult.get(MessageParameterType.WEATHER_TYPE)).isEqualTo(param);
    }

    @Test
    void getWeatherParamsOnlyCity() {
        // Actual
        Map<MessageParameterType, String> actualResult = MessageParser.getWeatherParams(city);

        // Assertion
        Assertions.assertThat(actualResult.size()).isEqualTo(1);
        Assertions.assertThat(actualResult.get(MessageParameterType.CITY)).isEqualTo(city);
    }

    @Test
    void getWeatherWithInputParamIsNull() {
        // Actual
        Executable exec = () -> MessageParser.getWeatherParams(null);

        // Assertion
        assertThrows(IllegalArgumentException.class, exec);
    }
}