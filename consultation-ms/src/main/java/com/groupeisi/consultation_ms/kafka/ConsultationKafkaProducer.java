package com.groupeisi.consultation_ms.kafka;

import com.groupeisi.common_ms.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsultationKafkaProducer {

    private final KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Envoi notification Kafka : {}", notificationDTO);
        kafkaTemplate.send(topic, notificationDTO);
    }
}