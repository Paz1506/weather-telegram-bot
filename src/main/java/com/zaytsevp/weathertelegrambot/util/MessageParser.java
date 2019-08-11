package com.zaytsevp.weathertelegrambot.util;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.zaytsevp.weathertelegrambot.model.telegram.MessageParameterType;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

/**
 * @author Pavel Zaytsev
 */
@UtilityClass
public class MessageParser {

    public static Map<MessageParameterType, String> getWeatherParams(String inputRawMessageString) {
        List<String> params = Lists.newArrayList(Splitter.on(" ")
                                                         .trimResults()
                                                         .omitEmptyStrings()
                                                         .split(inputRawMessageString));

        if (params.size() == 0) throw new IllegalArgumentException("Не передано название города!");

        if (params.size() == 1) {
            return ImmutableMap.of(MessageParameterType.CITY, params.get(0));
        } else {
            return ImmutableMap.of(MessageParameterType.CITY, params.get(0),
                                   MessageParameterType.WEATHER_TYPE, params.get(1));
        }
    }
}
