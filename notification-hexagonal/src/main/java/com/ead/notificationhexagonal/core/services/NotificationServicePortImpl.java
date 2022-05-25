package com.ead.notificationhexagonal.core.services;

import com.ead.notificationhexagonal.core.domain.NotificationDomain;
import com.ead.notificationhexagonal.core.domain.PageInfo;
import com.ead.notificationhexagonal.core.domain.enums.NotificationStatus;
import com.ead.notificationhexagonal.core.ports.NotificationPersistencePort;
import com.ead.notificationhexagonal.core.ports.NotificationServicePort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class NotificationServicePortImpl implements NotificationServicePort {

    final NotificationPersistencePort notificationPersistencePort;

    public NotificationServicePortImpl(NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }

    @Override
    public NotificationDomain saveNotification(NotificationDomain notificationDomain) {
        return notificationPersistencePort.save(notificationDomain);
    }

    @Override
    public List<NotificationDomain> findAllNotificationByUser(UUID userId, PageInfo pageInfo) {
        return notificationPersistencePort.findAllByUserIdAndNotificationStatus(userId, NotificationStatus.CREATED, pageInfo);
    }

    @Override
    public Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        return notificationPersistencePort.findByNotificationIdAndUserId(notificationId, userId);
    }
}
