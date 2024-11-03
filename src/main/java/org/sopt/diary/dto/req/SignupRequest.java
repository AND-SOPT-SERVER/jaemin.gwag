package org.sopt.diary.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



public class SignupRequest {


    @NotBlank(message = "이메일은 필수 입력값입니다")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입력값입니다")
    @Size(max = 10, message = "이름은 최대 10자 이내로 입력해주세요")
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Size(max = 10, message = "닉네임은 10자 이내로 입력해주세요")
    private String nickname;


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

    public SignupRequest(String email, String name, String nickname, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }
}
