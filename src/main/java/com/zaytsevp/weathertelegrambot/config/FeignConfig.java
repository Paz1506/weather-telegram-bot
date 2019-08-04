package com.zaytsevp.weathertelegrambot.config;

import com.zaytsevp.weathertelegrambot.feign.JacksonDecoder;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Pavel Zaytsev
 */
@Configuration
//@Import(FeignClientsConfiguration.class)
public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }
}
