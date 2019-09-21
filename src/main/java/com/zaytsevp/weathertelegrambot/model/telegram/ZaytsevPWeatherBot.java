package com.zaytsevp.weathertelegrambot.model.telegram;

import com.zaytsevp.weathertelegrambot.action.GetWeatherAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * @author Pavel Zaytsev
 */
@Slf4j
@Component
public class ZaytsevPWeatherBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.name}")
    private String botUsername;

    @Value("${telegram.bot.apikey}")
    private String botToken;

    private final GetWeatherAction getWeatherAction;

    @Autowired
    public ZaytsevPWeatherBot(GetWeatherAction getWeatherAction) {this.getWeatherAction = getWeatherAction;}

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String weatherInfo = getWeatherInfo(update);

            SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
                                                   .setText(weatherInfo);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private String getWeatherInfo(Update update) {
        try {
            return getWeatherAction.executeImpl(update.getMessage().getText(), update);
        } catch (Exception e) {
            log.error("Ошибка получения информации о погоде: ", e.getMessage());
            return "Ошибка получения информации о погоде";
        }
    }
}
