package com.vincent.springboothomework.controller;

import com.vincent.springboothomework.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleDao extends JpaRepository<Userinfo,String> {

    List<Userinfo> findPropleByAge(int age);

}
