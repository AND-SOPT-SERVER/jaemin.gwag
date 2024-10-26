
package org.sopt.diary.api;
import org.sopt.diary.api.DiaryResponse;

import java.util.ArrayList;
import java.util.List;

public class DiaryListResponse {
    private List<DiaryResponse> diaryList;

    public DiaryListResponse(List<DiaryResponse> diaryList) {
        this.diaryList = diaryList;
    }

    public List<DiaryResponse> getDiaryList() {
        return diaryList;
    }
}
