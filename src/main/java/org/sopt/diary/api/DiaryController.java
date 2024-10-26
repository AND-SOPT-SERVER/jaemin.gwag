package org.sopt.diary.api;

import org.sopt.diary.exception.DiaryLengthException;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService){
        this.diaryService = diaryService;
    }
    @PostMapping("/diaries")
    void post(@RequestBody DiaryCreateRequest diaryCreateRequest) {
        //글자수 30자 제한
        if (diaryCreateRequest.getContent().length() > 30) {
            throw new DiaryLengthException();
        }
        else {
            diaryService.createDiary(diaryCreateRequest.getName(), diaryCreateRequest.getContent());
        }

    }


    @DeleteMapping("/diaries/{id}")
    public void delete(@PathVariable Long id){
        diaryService.deleteDiary(id);
    }

}
