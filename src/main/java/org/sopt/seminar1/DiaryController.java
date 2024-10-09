package org.sopt.seminar1;

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

        //글자수 제한 구현 -> 이모지가 들어가면 어떻게 되는가?
        diaryService.wirteDiary(body);
    }

    final void delete(final String id) {
        //제거 기능
        diaryService.deleteDiary(id);
    }

    final void patch(final String id, final String body) {
        diaryService.patchDiary(id, body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}

