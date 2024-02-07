package com.ohgiraffers.springjpa.menu.dto;

public class MenuDTO {

    private String MenuName;

    private int menuPrice;

    private int categoryCode;

    public MenuDTO() {
    }

    public MenuDTO(String menuName, int menuPrice, int categoryCode) {
        MenuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "MenuName='" + MenuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                '}';
    }
}
