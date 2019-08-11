package com.zaytsevp.weathertelegrambot.model.telegram;

import java.util.Map;
import java.util.Optional;

/**
 * Локатор для хранения кода погоды и соответсвующего ему погодного смайлика
 *
 * @author Pavel Zaytsev
 */
public class WeatherCodeEmojiLocator {

    private final Map<Integer, String> weatherEmojiByCode;

    public WeatherCodeEmojiLocator(Map<Integer, String> weatherEmojiByCode) {this.weatherEmojiByCode = weatherEmojiByCode;}

    public String getWeatherEmojiByWeatherCode(int code) {
        return Optional.ofNullable(weatherEmojiByCode.get(code))
                       .orElse(weatherEmojiByCode.get(1000));
    }
}
