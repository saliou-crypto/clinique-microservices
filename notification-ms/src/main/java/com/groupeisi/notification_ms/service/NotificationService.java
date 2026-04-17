package com.groupeisi.notification_ms.service;

import com.groupeisi.common_ms.dto.NotificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public void traiterNotification(NotificationDTO notificationDTO) {
        log.info("===== NOTIFICATION =====");
        log.info("Type    : {}", notificationDTO.getType());
        log.info("Email   : {}", notificationDTO.getEmail());
        log.info("Message : {}", notificationDTO.getMessage());
        log.info("========================");
    }
}