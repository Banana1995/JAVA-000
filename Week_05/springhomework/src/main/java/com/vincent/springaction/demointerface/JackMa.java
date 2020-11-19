package com.vincent.springaction.demointerface;

public class JackMa implements People {

    private String name;
    private int age;
    private String habit;
    private String skinColor;

    @Override
    public String toString() {
        return "JackMa{" +
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
        String res = "hello," + yourname + ", i don't care about money.";
        return res;
    }
}
