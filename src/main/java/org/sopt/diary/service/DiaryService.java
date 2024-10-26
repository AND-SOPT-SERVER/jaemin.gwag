package org.sopt.diary.service;

import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository){
        this.diaryRepository = diaryRepository;

    }

    public void createDiary(String title, String content) {
        diaryRepository.save(new DiaryEntity(title, content, LocalDate.now()));
    }

    public void deleteDiary(Long id){
        diaryRepository.deleteById(id);
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
