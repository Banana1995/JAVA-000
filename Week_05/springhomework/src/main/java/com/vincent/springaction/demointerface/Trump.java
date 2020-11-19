package com.vincent.springaction.demointerface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Service
@Component
public class Trump implements People {


    private String name;
    private int age;
    private String habit;
    private String skinColor;

    public Trump() {
        this.age = 75;
        this.name = "donald trump by @component";
        this.habit = "business by @component";
        this.skinColor = "white by @component";
    }



    @Override
    public String toString() {
        return "Trump{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", habit='" + habit + '\'' +
                ", skinColor='" + skinColor + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
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
        String res = "hello," + yourname + ", i will make china great again.";
        return res;
    }
}
