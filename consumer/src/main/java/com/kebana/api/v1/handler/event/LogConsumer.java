package com.kebana.api.v1.handler.event;

import com.kebana.api.v1.domain.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogConsumer {
    private final SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.KAFKA_GROUP_ID
    )
    public void eventHandler(Message message) {
        log.info("sending message {}", message);
        template.convertAndSend("/log-message", message);
    }
}
