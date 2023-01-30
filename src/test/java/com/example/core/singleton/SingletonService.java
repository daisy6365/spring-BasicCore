package com.example.core.singleton;

public class SingletonService {

    // 자기자신을 내부의 private으로 static으로 가짐 -> class 레벨에 딱 하나만 가지고 있음
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
