package com.ead.notificationhexagonal.adapters.outbound.persistence;

import com.ead.notificationhexagonal.adapters.outbound.persistence.entities.NotificationEntity;
import com.ead.notificationhexagonal.core.domain.NotificationDomain;
import com.ead.notificationhexagonal.core.domain.PageInfo;
import com.ead.notificationhexagonal.core.domain.enums.NotificationStatus;
import com.ead.notificationhexagonal.core.ports.NotificationPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class NotificationPersistencePortImpl implements NotificationPersistencePort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final ModelMapper modelMapper;

    public NotificationPersistencePortImpl(NotificationJpaRepository notificationJpaRepository, ModelMapper modelMapper) {
        this.notificationJpaRepository = notificationJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NotificationDomain save(NotificationDomain notificationDomain) {
        NotificationEntity notificationEntity = notificationJpaRepository.save(modelMapper.map(notificationDomain, NotificationEntity.class));
        return modelMapper.map(notificationEntity, NotificationDomain.class);
    }

    @Override
    public List<NotificationDomain> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus notificationStatus, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());

        return notificationJpaRepository.findAllByUserIdAndAndNotificationStatus(userId, notificationStatus, pageable)
                .stream().map(notificationEntity -> modelMapper.map(notificationEntity, NotificationDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        Optional<NotificationEntity> byNotificationIdAndUserId = notificationJpaRepository.findByNotificationIdAndUserId(notificationId, userId);

        if (byNotificationIdAndUserId.isPresent())
            return Optional.of(modelMapper.map(byNotificationIdAndUserId.get(), NotificationDomain.class));
        else
            return Optional.empty();

    }
}
