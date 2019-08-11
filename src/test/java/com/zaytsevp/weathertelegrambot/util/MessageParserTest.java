package com.zaytsevp.weathertelegrambot.util;

import com.zaytsevp.weathertelegrambot.model.telegram.MessageParameterType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Map;

public class MessageParserTest {

    private final String city = "Хабаровск";

    private final String param = "завтра";

    @Test
    public void getWeatherParams() {
        // Actual
        Map<MessageParameterType, String> actualResult = MessageParser.getWeatherParams(city + " " + param);

        // Assertion
        Assertions.assertThat(actualResult.size()).isEqualTo(2);
        Assertions.assertThat(actualResult.get(MessageParameterType.CITY)).isEqualTo(city);
        Assertions.assertThat(actualResult.get(MessageParameterType.WEATHER_TYPE)).isEqualTo(param);
    }

    @Test
    public void getWeatherParamsOnlyCity() {
        // Actual
        Map<MessageParameterType, String> actualResult = MessageParser.getWeatherParams(city);

        // Assertion
        Assertions.assertThat(actualResult.size()).isEqualTo(1);
        Assertions.assertThat(actualResult.get(MessageParameterType.CITY)).isEqualTo(city);
    }

    //TODO Тут тест на исключение + JUnit5
}