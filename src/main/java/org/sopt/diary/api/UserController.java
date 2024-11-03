package org.sopt.diary.api;

import jakarta.validation.Valid;
import org.sopt.diary.dto.req.LoginRequest;
import org.sopt.diary.dto.req.SignupRequest;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/user/signup")
    ResponseEntity<String> signUp(@Valid @RequestBody SignupRequest signupRequest, BindingResult result){
        if(result.hasErrors()){
            List<String> errors = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(String.join(", ", errors));

        }
        long userId = userService.signUp(signupRequest);
        return ResponseEntity.created(URI.create(String.format("/user/signup/%d", userId)))
                .build();

    }

    @PostMapping("user/login")
    ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
        UserEntity userEntity = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok()
                .header("UserId", String.valueOf(userEntity.getUserId()))
                .build();
    }
}
