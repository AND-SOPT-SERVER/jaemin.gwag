package org.sopt.diary.repository;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity//JPA(Java Persistence API)가 해당 클래스를 엔티티로 인식하게 하기 위해 클래스 선언 위에 어노테이션이 붙어야함
@Table(name = "diary_jpa_entity")
public class DiaryEntity {
    protected DiaryEntity(){

    }

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    public Long diaryId;

    @Column(name = "diary_title", nullable = false)
    public String name;
    @Column
    public String content;
    @Column
    public LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    public DiaryScope scope;

    public DiaryScope getScope() {
        return scope;
    }

    public DiaryEntity(String name, String content, DiaryScope scope, LocalDate createdAt, UserEntity userEntity) {
        this.name = name;
        this.content = content;
        this.createdAt = createdAt;
        this.scope = scope;
        this.user = userEntity;
    }



    public void setContent(String content) {
        this.content = content;
    }


    public String getName(){
        return name;
    }

    public Long getId(){
        return diaryId;
    }

    public String getContent(){
        return this.content;
    }
    public LocalDate getDate(){
        return createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }
}
