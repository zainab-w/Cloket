package com.example.cloket.models;

public class ModelCategory
{
    String category;
    String goal;
    String id;

    //constructor

    public ModelCategory() {
    }

    public ModelCategory(String category, String goal, String id) {
        this.category = category;
        this.goal = goal;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}