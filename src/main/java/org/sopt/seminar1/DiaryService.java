package org.sopt.seminar1;


import java.util.List;

public class DiaryService {
        private final DiaryRepository diaryRepository = new DiaryRepository();

        void wirteDiary(final String body){
            final Diary diary = new Diary(null, body);
            diaryRepository.save(diary);
        }

        List<Diary> getDiaryList(){
            return diaryRepository.findAll();
        }

        void deleteDiary(final Long id){
            diaryRepository.delete(id);
        }

        void patchDiary(final Long id, final String body){
            diaryRepository.patch(id, body);
        }
    }
