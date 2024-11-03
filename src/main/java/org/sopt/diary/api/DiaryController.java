package org.sopt.diary.api;

import org.sopt.diary.exception.DiaryLengthException;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/diaries{id}")
    ResponseEntity<DiaryResponse> getDiary(@PathVariable final Long id){
        Diary diary = diaryService.getDiary(id);
        return ResponseEntity.ok(new DiaryResponse(diary.getId(), diary.getName(), diary.getContent(), diary.getDate()));
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> getDiaries(){
        return ResponseEntity.ok(
                new DiaryListResponse(diaryService.getList().stream().map(diary-> new DiaryResponse(diary.getId(), diary.getName())).toList()));
    }

    @DeleteMapping("/diaries/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/diaries/{id}")
    ResponseEntity<Void> patchDiary(@PathVariable final Long id, @RequestBody DiaryPatchRequest diaryPatchRequest){
        return ResponseEntity.ok().build();
    }

}
