package dev.kelvin.cdc.adapters.in.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DebeziumCdcListener {
    @KafkaListener(topics = "appserver.public.orders", groupId = "cdc-demo")
    public void onMessage(@Payload String json) {
        log.info("[CDC][orders] {}", json);
    }
}
