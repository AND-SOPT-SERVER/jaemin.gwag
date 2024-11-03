package org.sopt.diary.service;

import java.time.LocalDate;

public class Diary {
    private final Long id;
    private final String name;
    private String content;
    private LocalDate date;
    private String scope;
    private int userId;




    public Diary(Long id, String name, String content, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.content = content;

    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public Diary(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
