package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity //JPA(Java Persistence API)가 해당 클래스를 엔티티로 인식하게 하기 위해 클래스 선언 위에 어노테이션이 붙어야함
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String name;
    @Column
    public String content;
    @Column
    public LocalDate date;

    public DiaryEntity(){

    }

    public void setContent(String content) {
        this.content = content;
    }

    public  DiaryEntity(String name, String content, LocalDate date){
        this.content = content;
        this.name = name;
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public String getContent(){
        return this.content;
    }
    public LocalDate getDate(){
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
