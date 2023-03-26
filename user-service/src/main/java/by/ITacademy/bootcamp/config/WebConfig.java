package by.ITacademy.bootcamp.config;

import by.ITacademy.bootcamp.dao.converters.UserConverter;
import by.ITacademy.bootcamp.dao.converters.UserEntityConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new UserConverter());
        registry.addConverter(new UserEntityConverter());
    }
}
