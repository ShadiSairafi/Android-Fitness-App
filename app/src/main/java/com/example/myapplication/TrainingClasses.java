package com.example.myapplication;

public class TrainingClasses {
    private int imageResId;  // Resource ID for the image
    private String name;
    private int time;
    private classess[] classes;

    // Constructor with image resource ID
    public TrainingClasses(int imageResId, String name, int time, classess[] classes) {
        this.imageResId = imageResId;
        this.name = name;
        this.time = time;
        this.classes = classes;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }



    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public classess[] getClasses() {
        return classes;
    }

    public void setClasses(classess[] classes) {
        this.classes = classes;
    }
}
