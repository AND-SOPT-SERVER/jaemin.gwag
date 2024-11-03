package org.sopt.diary.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;

    @Column(name = "email")
    public String email;
    @Column(name = "name")
    public String name;
    @Column(name = "password")
    public String password;
    @Column(name  = "nickname")
    public String nickname;

    public UserEntity(){

    }



    public UserEntity(String email, String name, String nickname, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
}
