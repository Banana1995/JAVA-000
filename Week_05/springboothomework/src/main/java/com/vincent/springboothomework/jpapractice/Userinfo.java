package com.vincent.springboothomework.jpapractice;


import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
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
