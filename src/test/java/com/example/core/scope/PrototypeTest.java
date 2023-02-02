package com.example.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1); // prototypeBean1 = com.example.core.scope.PrototypeTest$PrototypeBean@3700ec9c
        System.out.println("prototypeBean2 = " + prototypeBean2); // prototypeBean2 = com.example.core.scope.PrototypeTest$PrototypeBean@2002348
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 실행되지 않음
        // 프로토타입 빈은 그냥 생성되자마자 삭제됨
        // 스프링 컨테이너가 더 이상 관리하지 않음
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean destroy");
        }
    }
}
