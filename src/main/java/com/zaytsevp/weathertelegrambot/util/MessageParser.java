package com.zaytsevp.weathertelegrambot.util;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.zaytsevp.weathertelegrambot.model.telegram.MessageParameterType;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Pavel Zaytsev
 */
@UtilityClass
public class MessageParser {

    private static final String INCORRECT_INPUT_PARAMS = "Некорректные параметры запроса";

    public static Map<MessageParameterType, String> getWeatherParams(String inputRawMessageString) {
        // TODO: вынести в утили все базовые проверки + механизм ErrorInfo для инф. об ошибке
        if (StringUtils.isEmpty(inputRawMessageString)) {
            throw new IllegalArgumentException("Не передано название города!");
        }

        List<String> params = Lists.newArrayList(Splitter.on(" ")
                                                         .trimResults()
                                                         .omitEmptyStrings()
                                                         .split(inputRawMessageString));

        if (params.size() == 1) {
            return ImmutableMap.of(MessageParameterType.CITY, params.get(0));
        } else {
            return ImmutableMap.of(MessageParameterType.CITY, params.get(0),
                                   MessageParameterType.WEATHER_TYPE, params.get(1));
        }
    }
}
