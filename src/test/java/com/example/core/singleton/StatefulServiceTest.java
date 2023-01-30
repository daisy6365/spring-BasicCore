package com.example.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA",10000);

        //ThreadB : B사용자 20000원 주문
        statefulService2.order("userB",20000);

        // StatefulService의 price필드는 공유되는 필드 인데, 특정 클라이언트가 값을 변경
        // 공유필드는 주의해야하고 스프링 빈은 항상 무상태(stateless)로 설계
        int price = statefulService1.getprice(); // 10000 but 20000원 나옴
        System.out.println("statefulService1 price = " + price);

        Assertions.assertThat(statefulService1.getprice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}