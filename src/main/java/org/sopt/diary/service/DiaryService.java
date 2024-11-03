package org.sopt.diary.service;

import org.sopt.diary.exception.CustomException;
import org.sopt.diary.exception.ErrorCode;
import org.sopt.diary.dto.req.DiaryPatchRequest;
import org.sopt.diary.repository.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public DiaryService(DiaryRepository diaryRepository, UserRepository userRepository){
        this.diaryRepository = diaryRepository;
        this.userRepository=userRepository;

    }

    public Diary getDiary(final Long id){
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(()-> new RuntimeException("해당하는 일기가 없습니다"));
        return new Diary(diaryEntity.getId(), diaryEntity.getName(),diaryEntity.getContent(),diaryEntity.getDate());
    }


    public DiaryEntity createDiary(UserEntity userEntity, String title, String content, DiaryScope scope) {
        DiaryEntity diaryEntity = new DiaryEntity(title, content, scope, LocalDate.now(), userEntity );
        diaryRepository.save(diaryEntity);
        return diaryEntity;
    }

    public void deleteDiary(Long userId, Long id){
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(()-> new RuntimeException("존재하지 않습니다"));
        if(userRepository.findById(userId).isEmpty()){
            throw new CustomException(ErrorCode.INVALID_USER);
        } else if(!diaryEntity.getUser().getUserId().equals(userId)){
            throw new CustomException(ErrorCode.NON_AUTHORIZED);
        } else {
            diaryRepository.deleteById(id);
        }
    }


    public void patchDiary(final long id, DiaryPatchRequest diaryPatchRequest){
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(()-> new RuntimeException("해당하는 일기가 없습니다"));
        String name = diaryPatchRequest.getName();
        String content = diaryPatchRequest.getContent();
        diaryEntity.setContent(content);
        diaryEntity.setName(name);
        diaryRepository.save(diaryEntity);
    }



    public List<Diary> getList(){
        //(1) repository로부터 DiaryEntity를 가져옴
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();

        final List<Diary> diaryList = new ArrayList<>();

        for(DiaryEntity diaryEntity : diaryEntityList){
            if(diaryEntity.getScope().equals(DiaryScope.PRIVATE)){
                continue;
            }
            diaryList.add(
                    new Diary(diaryEntity.getId(),diaryEntity.getName())
            );
        }
        return new ArrayList();
    }

    public DiaryEntity findDiaryById(Long id){
        return diaryRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.NON_AUTHORIZED));
    }
}
