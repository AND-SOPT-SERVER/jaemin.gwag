package org.sopt.diary.dto.req;

public class DiaryPatchRequest {
    public String name;
    public String content;

    public DiaryPatchRequest(String content, String name) {
        this.content = content;
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
