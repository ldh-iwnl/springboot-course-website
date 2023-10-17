package com.mayikt.edu.service;

import com.mayikt.edu.entity.EduUser;

public interface EduUserService {
    EduUser findByUserNameEduUser(String userName);

    EduUser getUserbyId(Integer userId);
}
