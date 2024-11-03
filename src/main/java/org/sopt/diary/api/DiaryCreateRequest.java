package org.sopt.diary.api;

public class DiaryCreateRequest {
    public String name;
    public String content;

    public String getContent(){
        return content;
    }
    public String getName(){
        return name;
    }

    public void setDiary(String title, String content){
        this.name = name;
        this.content = content;
    }
}
