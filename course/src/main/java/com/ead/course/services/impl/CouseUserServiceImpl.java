package com.ead.course.services.impl;

import com.ead.course.repositories.CourseUserRepository;
import com.ead.course.services.CouseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouseUserServiceImpl implements CouseUserService {

    @Autowired
    CourseUserRepository courseUserRepository;
}
