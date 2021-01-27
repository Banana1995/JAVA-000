package com.vincent.springboothomework.service;

import com.vincent.springboothomework.jpapractice.PeopleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUpdateService {

    @Autowired
    private PeopleDao peopleDao;

    @Transactional(rollbackForClassName = "IllegalAccessException")
    public void updateUserByAge(int age) throws IllegalAccessException {
        peopleDao.updatePeopleNameByAge("updName", age);
        throw new IllegalAccessException();
    }

}
