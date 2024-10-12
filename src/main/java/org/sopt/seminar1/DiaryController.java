package org.sopt.seminar1;
// controller에서 예외처리 해주기
//println은 거의 안 씀 -> 동기적으로 작동 -> 속도가 느려짐 -> 시스템으로 해야할 건 예외처리 로깅(?)
//그냥 상수 사용하지말고 enum으로 빼고 static final을 사용하여 상수로 빼서 변수에 의미를 넣는것
//코드 사이의 스페이스들이 불규칙적 -> 하면 좋다 이쁜게 좋다 ㅇㅇ
//
import java.util.ArrayList;
import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
        if(body.length() > 30){
            throw new Main.UI.InvalidInputException();
        } //글자수 제한

        //글자수 제한 구현 -> 이모지가 들어가면 어떻게 되는가?
        diaryService.wirteDiary(body);
    }

    final void delete(final String id) {
        long longId = Long.parseLong(id);//pareLong : string -> Long
        if(longId < 1){
            throw new Main.UI.InvalidInputException(); //throw : 예외를 강제로 발생시킴
        }
        diaryService.deleteDiary(longId);
    }

    final void patch(final String id, final String body) {
        long longId = Long.parseLong(id);
        if(longId < 1){
            throw new Main.UI.InvalidInputException(); //throw : 예외를 강제로 발생시킴
        }
        diaryService.patchDiary(longId, body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}

