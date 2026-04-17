package com.groupeisi.notification_ms.kafka;

import com.groupeisi.common_ms.dto.NotificationDTO;
import com.groupeisi.notification_ms.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationKafkaConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeNotification(NotificationDTO notificationDTO) {
        log.info("Notification reçue via Kafka : {}", notificationDTO);
        notificationService.traiterNotification(notificationDTO);
    }
}