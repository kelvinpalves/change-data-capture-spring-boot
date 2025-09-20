package dev.kelvin.cdc.config;

import dev.kelvin.cdc.adapters.out.persistence.OrderPersistenceAdapter;
import dev.kelvin.cdc.adapters.out.persistence.SpringDataOrderJpaRepository;
import dev.kelvin.cdc.application.service.OrderService;
import dev.kelvin.cdc.domain.ports.outbound.OrderRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    OrderRepositoryPort orderRepositoryPort(SpringDataOrderJpaRepository jpa) {
        return new OrderPersistenceAdapter(jpa);
    }

    @Bean
    OrderService orderService(OrderRepositoryPort repo) {
        return new OrderService(repo);
    }
}
