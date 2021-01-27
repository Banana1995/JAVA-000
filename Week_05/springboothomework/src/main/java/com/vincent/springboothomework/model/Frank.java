package com.vincent.springboothomework.model;

import com.vincent.springboothomework.jpapractice.Userinfo;


public class Frank  extends Userinfo implements People {

    private String name;
    private int age;
    private String habit;
    private String skinColor;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Frank{" +
                "name='" + name + '\'' +
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

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getSkinColor() {
        return skinColor;
    }

    @Override
    public String shakeHands(String yourname) {
        return null;
    }
}
