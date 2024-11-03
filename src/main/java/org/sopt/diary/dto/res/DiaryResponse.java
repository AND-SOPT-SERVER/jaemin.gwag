package org.sopt.diary.dto.res;

import java.time.LocalDate;

public class DiaryResponse {
    private long id;
    private String name;
    private String content;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public DiaryResponse(long id, String name, String content, LocalDate date){
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public DiaryResponse(long id, String name){
        this.name = name;
        this.id = id;
    }

    public DiaryResponse(String name, String content){
        this.name = name;
        this.content = content;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
