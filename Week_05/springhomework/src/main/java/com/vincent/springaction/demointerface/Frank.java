package com.vincent.springaction.demointerface;

public class Frank implements People {
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
        return null;
    }

    @Override
    public String getSkinColor() {
        return null;
    }

    @Override
    public String shakeHands(String yourname) {
        return null;
    }
}
