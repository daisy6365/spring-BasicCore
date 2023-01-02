package com.example.core.beanfind;

import com.example.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    /**
     * name = org.springframework.context.annotation.internalConfigurationAnnotationProcessorObject = org.springframework.context.annotation.ConfigurationClassPostProcessor@29e6eb25
     * name = org.springframework.context.annotation.internalAutowiredAnnotationProcessorObject = org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@62435e70
     * name = org.springframework.context.annotation.internalCommonAnnotationProcessorObject = org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@339bf286
     * name = org.springframework.context.event.internalEventListenerProcessorObject = org.springframework.context.event.EventListenerMethodProcessor@38be305c
     * name = org.springframework.context.event.internalEventListenerFactoryObject = org.springframework.context.event.DefaultEventListenerFactory@269f4bad
     * name = appConfigObject = com.example.core.AppConfig$$EnhancerBySpringCGLIB$$c28bfc51@5ed731d0
     * name = memberServiceObject = com.example.core.member.MemberServiceImpl@3234f74e
     * name = memberRepositoryObject = com.example.core.member.MemoryMemberRepository@7bc10d84
     * name = orderServiceObject = com.example.core.order.OrderServiceImpl@275fe372
     * name = discountPolicyObject = com.example.core.discount.RateDiscountPolicy@40e10ff8
     * */
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 직접 등록한 것, 내부에서 사용하는 빈 모두 출력
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "Object = " + bean);
            
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    /**
     * name = appConfigObject = com.example.core.AppConfig$$EnhancerBySpringCGLIB$$9e97565b@73ff4fae
     * name = memberServiceObject = com.example.core.member.MemberServiceImpl@21aa6d6c
     * name = memberRepositoryObject = com.example.core.member.MemoryMemberRepository@b968a76
     * name = orderServiceObject = com.example.core.order.OrderServiceImpl@2f9a01c1
     * name = discountPolicyObject = com.example.core.discount.RateDiscountPolicy@2611b9a3
     * */
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//빈에 대한 정보

            // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                //우리가 등록한 빈만 출력
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "Object = " + bean);
            }
        }
    }

}
