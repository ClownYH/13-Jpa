package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
* 양방향 매핑에서 어느 한 쪽이 연관 관계의 주인이 되면, 주인이 아닌 쪽에서는 속성을 지정해주어야 한다.
* 이 때, 연관 관계의 주인이 아닌 객체 MappedBy를 써서 연관 관계 주인 객체의 필드명을 매핑 시켜 놓으면 양방향 관계를 적용할 수 있다.
* */
@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "categoryCode", cascade = CascadeType.PERSIST) // 나를 연관 관계로 가지고 있는 필드를 입력, 내가 주인이 아님을 의미, 일반적으로 mappedBy가 달리면 조회만 가능
    // PERSIST를 설정하면 주인이 아닌 곳에서도 주인된 곳에 변경을 줄 수 있다.
    private List<Menu> menuList = new ArrayList<>();

    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menuList = menuList;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {

        List<Menu> list = new ArrayList<>();
        for(Menu m : menuList){
            m.setCategoryCode(this);
            list.add(m);
        } // 이렇게 setter를 설정하면 트랜잭션을 하나로 묶어준다. 주로 결제 시스템에서 이런 식으로 적용된다.

        this.menuList = list;
    }

    @Override
    public String toString() {
        return "CategoryAndMenu{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menuList=" + menuList +
                '}';
    }
}
