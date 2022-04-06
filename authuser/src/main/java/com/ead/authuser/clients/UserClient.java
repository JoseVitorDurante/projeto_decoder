package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
public class UserClient {

    @Autowired
    RestTemplate restTemplate;

    String REQUEST_URI = "http://localhost:8082";

    public Page<CourseDto> getAllCourseByUser(UUID userId, Pageable pageable) {

        List<CourseDto> searchResult = null;

        String url = REQUEST_URI + "/couses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replace(":", ",");

        try {

        } catch (HttpStatusCodeException e) {
            log.error("Error request / courses {} ", e);
        }

        return ;
    }
}
