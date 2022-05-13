package com.ead.authuser.controllers;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)//permitindo o acesso de todas as origem
public class UserCoursesController {

    @Autowired
    private UserService userService;
    @Autowired
    CourseClient courseClient;


    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Object> getAllCourseByUser(@PathVariable(value = "userId") UUID userId,
                                                     @RequestHeader("Authorization") String token,
                                                     @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable) {

        Optional<UserModel> optionalUserModel = userService.findById(userId);

        if (!optionalUserModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllCourseByUser(userId, pageable, token));
    }
}
