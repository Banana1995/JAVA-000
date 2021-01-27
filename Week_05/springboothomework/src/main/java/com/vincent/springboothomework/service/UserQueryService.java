package com.vincent.springboothomework.service;

import com.vincent.springboothomework.jpapractice.PeopleDao;
import com.vincent.springboothomework.jpapractice.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserQueryService {

    @Autowired
    private PeopleDao peopleDao;


    public List<Userinfo> getUserByAge(int age) {
        List<Userinfo> res = peopleDao.findPropleByAge(age);
        return res;
    }
}
