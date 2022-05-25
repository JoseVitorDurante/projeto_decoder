package com.ead.notificationhexagonal.adapters.inbound.controllers;

import com.ead.notificationhexagonal.adapters.dtos.NotificationDto;
import com.ead.notificationhexagonal.core.domain.NotificationDomain;
import com.ead.notificationhexagonal.core.domain.PageInfo;
import com.ead.notificationhexagonal.core.ports.NotificationServicePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserNotificationController {

    final NotificationServicePort notificationService;

    public UserNotificationController(NotificationServicePort notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<Page<NotificationDomain>> getAllNotificationsByUser(@PathVariable(value = "userId") UUID userId,
                                                                              @PageableDefault(page = 0, size = 10, sort = "notificationId", direction = Sort.Direction.DESC) Pageable pageable,
                                                                              Authentication authentication) {

        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageable.getPageSize());
        pageInfo.setPageNumber(pageInfo.getPageNumber());

        List<NotificationDomain> allNotificationByUser = notificationService.findAllNotificationByUser(userId, pageInfo);
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(allNotificationByUser));
    }


    @PutMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> updateNotification(@PathVariable(value = "userId") UUID userId,
                                                     @PathVariable(value = "notificationId") UUID notificationId,
                                                     @RequestBody @Valid NotificationDto notificationDto) {
        Optional<NotificationDomain> notificationModelOptional = notificationService.findByNotificationIdAndUserId(notificationId, userId);

        if (!notificationModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found!");
        }

        notificationModelOptional.get().setNotificationStatus(notificationDto.getNotificationStatus());
        notificationService.saveNotification(notificationModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(notificationModelOptional.get());

    }

}
