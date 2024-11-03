package org.sopt.diary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class DiaryApplication {
    public static void main(String[] args){
        SpringApplication.run(DiaryApplication.class, args);
    }
}

//reqbin.com
//