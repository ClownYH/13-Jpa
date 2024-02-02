package com.ohgiraffers.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerLifeCycleTests {
    /*
    * 엔티티 매니저 팩토리(EntityManagerFactory)란? -> DB랑 커넥션 맺은 개체
    * 엔티티매니저를 생성할 수 있는 기능을 제공하는 팩토리 클래스이다.
    * Thread-safe하기 때문에 여러 스레드가 동시에 접근해도 안전하므로 서로 다른 스레드 간 공유해서 재사용한다.
    * Thread-safe한 기능을 요청 스코프마다 생성하기에는 비용(시간, 메모리) 부담이 크므로
    * Application 스코프와 동일하게 싱글톤으로 생성해서 관리하는 것이 효율적이다.
    * 따라서 데이터베이스를 사용하는 애플리케이션 당 한 개의 EntityManagerFactory를 생성한다.
    * */

    /*
    * 엔티티 매니저(EntityManager)란? -> DB에 명령을 보내는 인스턴스
    * 엔티티 매니저는 앤티티를 저장하는 메모리 상의 데이터베이스를 관리하는 인스턴스이다.
    * 앤티티를 저장하고 수정, 삭제, 조회하는 등의 앤티티와 관련된 모든 일을 수행한다.
    * 앤티티 매니저는 Thread-safe하지 않기 때문에 동시성 문제를 일으킬 수 있다.
    *
    * 따라서 스레드 간 공유하지 않고, Web의 경우 일반적으로 request scope와 일치시킨다.
    *
    * 영속성 컨텍스트(persistence context)? -> 꺼내온 정보 등을 저장
    * 앤티티 매니저를 통해 앤티티를 저장하거나 조회하면 앤티티 매니저는 영속성 컨텍스트에 앤티티를 보관하고 관리한다.
    * 영속성 컨텍스트는 앤티티를 key-value 방식으로 저장하는 저장소이다.
    * 영속성 컨텍스틑는 앤티티 매니저를 생성할 때 하나 만들어진다.
    * 그리고 앤티티 매니저를 통해서 영속성 컨텍스트에 접근할 수 있고, 또 관리할 수 있다.
    * */

    private static EntityManagerFactory entityManagerFactory; // 전역공간에 하나만 존재해서 돌려 쓰기 때문에 static

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");

    }

    @BeforeEach
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void  엔티티_매니저_팩토리와_앤티티_매니저_생명주기_확인1(){
        System.out.println("entityManagerFactory.hashCode : " + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode : " + entityManager.hashCode());
    }

    @Test
    public void  엔티티_매니저_팩토리와_앤티티_매니저_생명주기_확인2(){
        System.out.println("entityManagerFactory.hashCode : " + entityManagerFactory.hashCode());
        System.out.println("entityManager.hashCode : " + entityManager.hashCode());
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager(){
        entityManager.close();
    }

}
