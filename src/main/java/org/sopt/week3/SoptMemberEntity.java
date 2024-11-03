package org.sopt.week3;

import jakarta.persistence.*;

@Entity //table과 매핑, 어떤 클래스를 바인딩 할지
@Table(name = "sopt_member") //어떤 table과 매팅할지 결정
public class SoptMemberEntity {
    @Id //Id임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false) //notnull, 만약 테이블에서는 notnull이지만 코드에서는 true일 때는 오류가 안남
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    public SoptMemberEntity(String name, int age){
        this.name = name;
        this.age = age;
    }

    protected SoptMemberEntity() {

    }

    @Override
    public String toString(){
        return this.name;
    }
}

