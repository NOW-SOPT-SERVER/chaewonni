package org.example.classes;

public abstract class Turtle extends Animal{

    @Override
    public void walk() {
        System.out.println("거북이가 걷습니다.");
    }

    @Override
    public void eat() {
        super.eat();
    }
}
