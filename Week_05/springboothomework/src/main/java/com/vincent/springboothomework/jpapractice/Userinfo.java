package com.vincent.springboothomework.jpapractice;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@Getter
public class Userinfo {
    @Id
    private String peopleName;
    private int age;
    private String habit;
    private String skinColor;

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    @Override
    public String toString() {
        return "Frank{" +
                "name='" + peopleName + '\'' +
                ", age=" + age +
                ", habit='" + habit + '\'' +
                ", skinColor='" + skinColor + '\'' +
                '}';
    }

    public String getPeopleName() {
        return peopleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }




}
