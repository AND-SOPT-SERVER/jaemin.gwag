package org.sopt.diary.service;

import org.sopt.diary.exception.CustomException;
import org.sopt.diary.exception.ErrorCode;
import org.sopt.diary.dto.req.SignupRequest;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public long signUp(SignupRequest req) {
        Optional<UserEntity> userEntityOptional =
                userRepository.findByEmail(req.getEmail());
        if(userEntityOptional.isPresent()){
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        String name = req.getName();
        String password = req.getPassword();
        String email = req.getEmail();
        String nickname = req.getNickname();
        UserEntity newUser = new UserEntity(email, name, nickname, password);

        userRepository.save(newUser);
        return newUser.getUserId();

    }

    public UserEntity login(String email, String password){
        return userRepository.findByEmail(email)
                .filter(userEntity -> password.equals(userEntity.getPassword()))
                .orElseThrow(()-> new CustomException(ErrorCode.INVALID_USER));
    }

    public UserEntity findById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new CustomException(ErrorCode.NON_AUTHORIZED));
    }





}
