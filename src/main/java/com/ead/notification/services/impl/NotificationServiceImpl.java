package com.ead.notification.services.impl;

import com.ead.notification.enums.NotificationStatus;
import com.ead.notification.models.NotificationModel;
import com.ead.notification.repositories.NotificationRespository;
import com.ead.notification.services.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    final
    NotificationRespository notificationRespository;


    public NotificationServiceImpl(NotificationRespository notificationRespository) {
        this.notificationRespository = notificationRespository;
    }

    @Override
    public NotificationModel saveNotification(NotificationModel notificationModel) {
        return notificationRespository.save(notificationModel);
    }

    @Override
    public Page<NotificationModel> findAllNotificationByUser(UUID userId, Pageable pageable) {
        return notificationRespository.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageable);
    }

    @Override
    public Optional<NotificationModel> findByNotificationAndUserId(UUID notificationId, UUID userid) {
        return notificationRespository.findByNotificationIdAndUserId(notificationId, userid);
    }
}
