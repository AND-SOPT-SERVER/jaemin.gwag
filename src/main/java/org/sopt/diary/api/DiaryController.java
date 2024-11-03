package org.sopt.diary.api;

import org.sopt.diary.exception.CustomException;
import org.sopt.diary.exception.ErrorCode;
import org.sopt.diary.dto.req.DiaryCreateRequest;
import org.sopt.diary.dto.res.DiaryListResponse;
import org.sopt.diary.dto.req.DiaryPatchRequest;
import org.sopt.diary.dto.res.DiaryResponse;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryService;
import org.sopt.diary.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")  // 이 컨트롤러의 모든 요청은 "/api"로 시작
public class DiaryController {
    private final DiaryService diaryService;
    private final UserService userService;


    public DiaryController(DiaryService diaryService, UserService userService){

        this.diaryService = diaryService;
        this.userService = userService;
    }

    @PostMapping("/diaries")
    ResponseEntity<DiaryResponse> post(@RequestHeader("User-Id") String userId,
              @RequestBody DiaryCreateRequest diaryCreateRequest) {
        //글자수 30자 제한
        long parsedUserId =  Long.parseLong(userId);
        UserEntity user = userService.findById(parsedUserId);

        if (diaryCreateRequest.getContent().length() > 30) {
            throw new CustomException(ErrorCode.INVALID_SIZE);
        } else {
            DiaryEntity savedDia = diaryService.createDiary(user, diaryCreateRequest.getTitle(), diaryCreateRequest.getContent(), diaryCreateRequest.getScope());
            return ResponseEntity
                    .created(URI.create("/diaries" + savedDia.getId()))
                    .build();
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
    ResponseEntity<Void> delete(@RequestHeader("User-Id") String userId, @PathVariable Long id){
        Long parsedUserId =  Long.parseLong(userId);
        diaryService.deleteDiary(parsedUserId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/diaries/{id}")
    ResponseEntity<Void> patchDiary( @RequestHeader("User-Id") String userId,
            @PathVariable final Long id, @RequestBody DiaryPatchRequest diaryPatchRequest){

        Long parsedUserId =  Long.parseLong(userId);
        DiaryEntity diaryById = diaryService.findDiaryById(id);
        UserEntity user = userService.findById(parsedUserId);
        diaryService.patchDiary(id, diaryPatchRequest);

        return ResponseEntity.ok().build();
    }


}
