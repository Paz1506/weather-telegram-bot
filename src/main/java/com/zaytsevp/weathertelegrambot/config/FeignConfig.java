package com.zaytsevp.weathertelegrambot.config;

import com.zaytsevp.weathertelegrambot.feign.JacksonDecoder;
import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
