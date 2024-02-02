package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager(){
        entityManager.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트(){

        // given
        int menuCode = 2;

        // when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        //then
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        System.out.println("foundMenu : " + foundMenu);
    }

    @Test
    public void 새로운_메뉴_추가_테스트(){

        // given
        Menu menu = new Menu();
        menu.setMenuName("jpa테스트 메뉴");
        menu.setMenuPrice(50000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        // 트랜잭션 : 데이터베이스에 변화를 일으키는 변화 - 등록, 수정, 삭제 -를 일으키는 명령
        entityTransaction.begin();

        try {
            entityManager.persist(menu); // persist -> 영속성 컨텍스트에 집어넣겠다. 영속화시킨다.
            entityTransaction.commit(); // commit -> db에 데이터를 넣겠다.
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Assertions.assertTrue(entityManager.contains(menu));

    }

    @Test
    public void 메뉴_이름_수정_테스트(){

        // given
        Menu menu = entityManager.find(Menu.class, 2); // pk로 조회를 안하면 업데이트가 아니라 인서트로 받아들인다.
        System.out.println("menu = " + menu);

        String menuNameToChange = "갈치스무디";

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            menu.setMenuName(menuNameToChange);
            entityTransaction.commit(); // 최신된 정보를 이미 가지고 있다면 새로 조회를 하지 않고 가지고 있는 데이터를 이용한다.
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        // then
        Assertions.assertEquals(menuNameToChange, entityManager.find(Menu.class, 3).getMenuName());
    }

    @Test
    public void 메뉴_삭제하기_테스트(){

        // given
        Menu menuToRemove = entityManager.find(Menu.class, 1);

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try{
            entityManager.remove(menuToRemove); // 비영속화시킨다(영속화 컨텍스트에서 삭제). 커밋을 날리지 않으면 비영속화만 시킨다.
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Menu removedMenu = entityManager.find(Menu.class, 1);
        Assertions.assertNull(removedMenu);
    }
}
