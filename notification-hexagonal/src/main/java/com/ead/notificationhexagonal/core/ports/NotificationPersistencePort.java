package com.ead.notificationhexagonal.core.ports;

import com.ead.notificationhexagonal.core.domain.NotificationDomain;
import com.ead.notificationhexagonal.core.domain.PageInfo;
import com.ead.notificationhexagonal.core.domain.enums.NotificationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationPersistencePort {

    NotificationDomain save(NotificationDomain notificationDomain);

    List<NotificationDomain> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus notificationStatus, PageInfo pageInfo);

    Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
