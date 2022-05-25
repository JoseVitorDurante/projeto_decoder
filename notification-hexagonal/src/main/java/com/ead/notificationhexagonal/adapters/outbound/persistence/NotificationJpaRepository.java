package com.ead.notificationhexagonal.adapters.outbound.persistence;

import com.ead.notificationhexagonal.adapters.outbound.persistence.entities.NotificationEntity;
import com.ead.notificationhexagonal.core.domain.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, UUID> {

    Page<NotificationEntity> findAllByUserIdAndAndNotificationStatus(UUID userId, NotificationStatus notificationStatus, Pageable pageable);

    Optional<NotificationEntity> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
}
