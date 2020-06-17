package com.example.todolistapp;

public class ToDoListModel {
    int id;
    String titleToDoList, dateToDoList, descToDoList;

    public ToDoListModel(int id, String titleToDoList, String dateToDoList, String descToDoList) {
        this.id = id;
        this.titleToDoList = titleToDoList;
        this.dateToDoList = dateToDoList;
        this.descToDoList = descToDoList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleToDoList() {
        return titleToDoList;
    }

    public void setTitleToDoList(String titleToDoList) {
        this.titleToDoList = titleToDoList;
    }

    public String getDateToDoList() {
        return dateToDoList;
    }

    public void setDateToDoList(String dateToDoList) {
        this.dateToDoList = dateToDoList;
    }

    public String getDescToDoList() {
        return descToDoList;
    }

    public void setDescToDoList(String descToDoList) {
        this.descToDoList = descToDoList;
    }
}