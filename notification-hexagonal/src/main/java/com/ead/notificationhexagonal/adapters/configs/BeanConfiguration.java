package com.ead.notificationhexagonal.adapters.configs;

import com.ead.notificationhexagonal.NotificationHexagonalApplication;
import com.ead.notificationhexagonal.core.ports.NotificationPersistencePort;
import com.ead.notificationhexagonal.core.services.NotificationServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NotificationHexagonalApplication.class)
public class BeanConfiguration {

    @Bean
    NotificationServicePortImpl notificationServicePortImpl(NotificationPersistencePort persistencePort) {
        return new NotificationServicePortImpl(persistencePort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
