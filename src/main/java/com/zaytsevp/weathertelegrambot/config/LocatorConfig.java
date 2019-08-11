package com.zaytsevp.weathertelegrambot.config;

import com.zaytsevp.weathertelegrambot.model.telegram.WeatherCodeEmojiLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфиг. локатора соотвествия кода погоды и смайлика.
 *
 * @author Pavel Zaytsev
 */
@Configuration
public class LocatorConfig {

    @Bean
    public WeatherCodeEmojiLocator getWeatherCodeEmojiLocator() {
        Map<Integer, String> emojiMap = new HashMap<>();
        // Гроза
        emojiMap.put(200, "\u26A1");
        emojiMap.put(201, "\u26A1");
        emojiMap.put(202, "\u26A1");
        emojiMap.put(210, "\u26A1");
        emojiMap.put(211, "\u26A1");
        emojiMap.put(212, "\u26A1");
        emojiMap.put(221, "\u26A1");
        emojiMap.put(230, "\u26A1");
        emojiMap.put(231, "\u26A1");
        emojiMap.put(232, "\u26A1");
        // Мелкий дождь
        emojiMap.put(300, "\uD83D\uDCA7");
        emojiMap.put(301, "\uD83D\uDCA7");
        emojiMap.put(302, "\uD83D\uDCA7");
        emojiMap.put(310, "\uD83D\uDCA7");
        emojiMap.put(311, "\uD83D\uDCA7");
        emojiMap.put(312, "\uD83D\uDCA7");
        emojiMap.put(313, "\uD83D\uDCA7");
        emojiMap.put(314, "\uD83D\uDCA7");
        emojiMap.put(321, "\uD83D\uDCA7");
        // Долждь
        emojiMap.put(500, "\u2614");
        emojiMap.put(501, "\u2614");
        emojiMap.put(502, "\u2614");
        emojiMap.put(503, "\u2614");
        emojiMap.put(504, "\u2614");
        emojiMap.put(511, "\u2614");
        emojiMap.put(520, "\u2614");
        emojiMap.put(521, "\u2614");
        emojiMap.put(522, "\u2614");
        emojiMap.put(531, "\u2614");
        // Снег
        emojiMap.put(600, "\u2744");
        emojiMap.put(601, "\u2744");
        emojiMap.put(602, "\u2744");
        emojiMap.put(611, "\u2744");
        emojiMap.put(612, "\u2744");
        emojiMap.put(613, "\u2744");
        emojiMap.put(615, "\u2744");
        emojiMap.put(616, "\u2744");
        emojiMap.put(620, "\u2744");
        emojiMap.put(621, "\u2744");
        emojiMap.put(622, "\u2744");
        // Атмосферные явления
        emojiMap.put(701, "\uD83D\uDCA8");
        emojiMap.put(711, "\uD83D\uDCA8");
        emojiMap.put(721, "\uD83D\uDCA8");
        emojiMap.put(731, "\uD83D\uDCA8");
        emojiMap.put(741, "\uD83D\uDCA8");
        emojiMap.put(751, "\uD83D\uDCA8");
        emojiMap.put(761, "\uD83D\uDCA8");
        emojiMap.put(762, "\uD83D\uDCA8");
        emojiMap.put(771, "\uD83D\uDCA8");
        emojiMap.put(781, "\uD83D\uDCA8");
        // Чистое небо
        emojiMap.put(800, "\uD83C\uDF1D");
        // Облачно
        emojiMap.put(801, "\u2601");
        emojiMap.put(802, "\u2601");
        emojiMap.put(803, "\u2601");
        emojiMap.put(804, "\u2601");
        //default
        emojiMap.put(1000, "\u2753");

        return new WeatherCodeEmojiLocator(emojiMap);
    }
}
