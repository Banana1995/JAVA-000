package com.vincent.springboothomework.controller;

import com.vincent.springboothomework.jdbcpractice.UserHandle;
import com.vincent.springboothomework.jpapractice.PeopleDao;
import com.vincent.springboothomework.model.Frank;
import com.vincent.springboothomework.jpapractice.Userinfo;
import com.vincent.springboothomework.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/homemade/")
public class UserController {

    @Autowired
    private UserHandle userHandle;

    @Autowired
    private PeopleDao peopleDao;

    @RequestMapping("adduser")
    public String addUser() throws SQLException {
        Frank frank = new Frank(4);
        frank.setAge(123);
        frank.setHabit("rest controller ");
        frank.setSkinColor("white");
        userHandle.insert(frank);
        return "add user success";
    }

    @RequestMapping("adduser2")
    public String addUser2() throws SQLException {
        Userinfo frank = new Userinfo();
        frank.setAge(123);
        frank.setPeopleName("save by jpa");
        frank.setHabit("rest controller ");
        frank.setSkinColor("white");
//        userHandle.insert(frank);
        peopleDao.save(frank);

        return "add user success";
    }

    @RequestMapping("queryuser/{age}/{id}")
    public String queryPropleByAge(@PathVariable("age") int agge, @PathVariable("id") int uddd) {
        List<Userinfo> res = peopleDao.findPropleByAge(agge);
        StringBuilder resStr = new StringBuilder();
        for (Userinfo re : res) {
            resStr.append(re.toString()).append("/n");
        }
        return resStr.toString();
    }
    @Autowired
    private UserUpdateService updService;

    @RequestMapping("update")
    public String updateUser(){
        try {
            updService.updateUserByAge(121);
        } catch (IllegalAccessException e) {
            return "update false";
        }
        return "success update";
    }
}
