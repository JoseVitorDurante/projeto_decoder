package com.ead.course.clients;

import com.ead.course.dtos.CourseUserDto;
import com.ead.course.dtos.ResponsePageDto;
import com.ead.course.services.UtilsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
public class AuthUserClient {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilsService utilsService;

    @Value("${ead.api.url.authuser}")
    String REQUEST_URI;

    public Page<UserDto> getAllUserByCourse(UUID courseId, Pageable pageable) {

        List<UserDto> searchResult = null;

        String url = REQUEST_URI + utilsService.createUrlGetAllUsersByCourse(courseId, pageable);

        try {
            ParameterizedTypeReference<ResponsePageDto<UserDto>> responseType = new ParameterizedTypeReference<ResponsePageDto<UserDto>>() {
            };

            ResponseEntity<ResponsePageDto<UserDto>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
        } catch (HttpStatusCodeException e) {
            log.error("Error request / user {} ", e);
        }

        return new PageImpl<>(searchResult);
    }

    public ResponseEntity<UserDto> getOneUserById(UUID userId) {
        String url = REQUEST_URI + "/users/" + userId;

        return restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class);
    }

    public void postSubscriptionUserInCourse(UUID courseId, UUID userId) {
        String url = REQUEST_URI + "/users/" + userId + "/courses/subscription";
        CourseUserDto courseUserDto = new CourseUserDto(userId, courseId);
        restTemplate.postForObject(url, courseUserDto, String.class);
    }

    public void deleteCourseInAuthUser(UUID courseId) {
        String url = REQUEST_URI + "/users/courses/" + courseId;
        restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
    }
}
