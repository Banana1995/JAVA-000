package com.vincent.springboothomework.jpapractice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PeopleDao extends JpaRepository<Userinfo, String> {

    List<Userinfo> findPropleByAge(int age);

    @Modifying
    @Query("update Userinfo u set u.peopleName = ?1 where u.age = ?2")
//    @Transactional
    void updatePeopleNameByAge(String peopleName, int age);
}
