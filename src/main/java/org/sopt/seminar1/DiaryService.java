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

        void deleteDiary(final String id){
            final Long deleteId = (long) Integer.parseInt(id);
            diaryRepository.delete(deleteId);
        }

        void patchDiary(final String id, final String body){
            final Long patchId = (long) Integer.parseInt(id);
            diaryRepository.patch(patchId, body);
        }
    }

