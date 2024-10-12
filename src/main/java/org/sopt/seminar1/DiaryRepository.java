package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
        private final Map<Long, String> storage = new ConcurrentHashMap<>();
        private final AtomicLong numbering = new AtomicLong();
        private final AtomicLong DayNumbering = new AtomicLong();

        void save(final Diary diary){
                //채번 과정
                final long id = numbering.addAndGet(1); //1추가한 값을 반환해주겠다

                //저장 과정
                storage.put(id, diary.getBody());
        }

        List<Diary> findAll(){
                //(1) diary를 담을 자료구조
                final List<Diary> diaryList = new ArrayList<>();

                //(2) 저장한 값을 불러오는 반복 구조
                for(long index = 1; index <= numbering.intValue(); index++){
                        final String body = storage.get(index);
                        //(2-1) 불러온 값을 구성한 자료구조로 이관
                        diaryList.add(new Diary(index, body));

                }

                //(3) 불러온 자료구조를 응답
                return diaryList;
        }

        void delete(final long id){
                if(id > numbering.intValue()){
                        throw new Main.UI.InvalidInputException(); //1부터 시작하니까 굳이 0을 받을 필요 없음
                } else{
                        storage.remove(id);
                        numbering.decrementAndGet();
                }
        }

        void patch(Long id, String body){
                if(DayNumbering.intValue()> 1){
                                System.out.println("하루에 2번만 수정가능합니다"); //IF문 안에 IF문을 넣는 것은 지양해야함
                        }
                else{
                        DayNumbering.addAndGet(1); //else문 -> 또한 지양 , 예외처리를 통해서
                        storage.replace(id, body);
                        }

                }


}


