package com.kebana.api.v1.handler.rest;

import com.kebana.api.v1.domain.Log;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final SimpMessagingTemplate template;

    /**
     * Returns logs for a specific search term and service.
     * @param service name of the service, if empty, considers all services.
     * @param searchStr string to be searched in the logs, if empty searching is not done.
     * @return List of applicable logs.
     */
    @GetMapping("/logs")
    public ResponseEntity<List<Log>> getLogs(@RequestParam String service, @RequestParam String searchStr) {
        template.convertAndSend("/log-message", "test");
        return ResponseEntity.ok(Collections.EMPTY_LIST);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/log-messages")
    public Message sendMessage(@Payload Message message) {
        return message;
    }
}
