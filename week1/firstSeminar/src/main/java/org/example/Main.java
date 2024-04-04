package org.example;

import org.example.classes.Person;

public class Main {
    public static void main(String[] args) {
        Person.run();
        //Person 클래스의 객체 생성
        Person person = new Person("김채원", 23, "female");
        System.out.println(person.getName());
        person.setName("서버 파트원");
        System.out.println(person.getName());
        //Person 클래스 내부 메소드 호출
        person.walk();
    }
}