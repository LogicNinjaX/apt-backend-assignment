package com.nitish.apt_assignment.service;

import com.nitish.apt_assignment.dto.response.NotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public <T> void sendOrderUpdate(NotificationResponse<T> response) {
        messagingTemplate.
                convertAndSend(
                        "/topic/orders",
                        response
                );

        logger.info("Order updates sent successfully");
    }
}
