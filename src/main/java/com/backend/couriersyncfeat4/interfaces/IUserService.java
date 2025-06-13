package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.CustomResponseEntity;
import com.backend.couriersyncfeat4.entity.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserEntity> findAllUsers();
    UserEntity findUserById(Long id);
    UserEntity addUser(UserEntity user);
    UserEntity findUserByEmail(String email);
    CustomResponseEntity deleteUser(Long id);

}
