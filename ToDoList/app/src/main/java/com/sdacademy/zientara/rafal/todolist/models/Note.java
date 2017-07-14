package com.sdacademy.zientara.rafal.todolist.models;

/**
 * Created by Evil on 13.07.2017.
 */

public class Note {
    private String name;
    private String url;

    public Note() {
    }

    public Note(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
