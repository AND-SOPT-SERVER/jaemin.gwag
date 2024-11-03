package org.sopt.diary.service;

public class User {
    private String email;
    private String name;
    private String password;
    private String nickname;
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User(String email, String name, String password, String nickname) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
