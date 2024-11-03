package org.sopt.diary.dto.req;

import org.sopt.diary.repository.DiaryScope;

public class DiaryCreateRequest {
    public String title;
    public String content;
    public DiaryScope scope;

    public DiaryScope getScope() {
        return scope;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setScope(DiaryScope scope) {
        this.scope = scope;
    }

    public String getContent(){
        return content;
    }
    public String getTitle(){
        return title;
    }


    public DiaryCreateRequest(String content, String title, DiaryScope scope) {
        this.content = content;
        this.title = title;
        this.scope = scope;
    }
}
