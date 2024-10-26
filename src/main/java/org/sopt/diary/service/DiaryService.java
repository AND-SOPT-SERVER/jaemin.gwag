package org.sopt.diary.service;

import org.sopt.diary.api.DiaryResponse;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository){
        this.diaryRepository = diaryRepository;

    }

    public Diary getDiary(final Long id){
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(()-> new RuntimeException("해당하는 일기가 없습니다"));
        return new Diary(diaryEntity.getId(), diaryEntity.getName(),diaryEntity.getContent(),diaryEntity.getDate());
    }


    public void createDiary(String title, String content) {
        diaryRepository.save(new DiaryEntity(title, content, LocalDate.now()));
    }

    public void deleteDiary(Long id){
        diaryRepository.deleteById(id);
    }

    public DiaryEntity patchDiary(final long id, String name, String content){
        DiaryEntity diaryEntity = diaryRepository.findById(id).orElseThrow(()-> new RuntimeException("해당하는 일기가 없습니다"));
        diaryEntity.setContent(content);
        diaryEntity.setName(name);
        diaryRepository.save(diaryEntity);
        return diaryEntity;

    }



    public List<Diary> getList(){
        //(1) repository로부터 DiaryEntity를 가져옴
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        //
        final List<Diary> diaryList = new ArrayList<>();

        for(DiaryEntity diaryEntity : diaryEntityList){
            diaryList.add(
                    new Diary(diaryEntity.getId(),diaryEntity.getName())
            );
        }
        return new ArrayList();
    }
}
