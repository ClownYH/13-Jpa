package com.ohgiraffers.springjpa.order.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_payments")
public class Payments {

    @Id
    @Column(name = "payments_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentCode;

    @JoinColumn(name = "order_code")
    @ManyToOne
    private MenuOrder menuOrder;

    @Column(name = "payments_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentsDate;

    public Payments() {
    }

    public Payments(int paymentCode, MenuOrder menuOrder, Date paymentsDate) {
        this.paymentCode = paymentCode;
        this.menuOrder = menuOrder;
        this.paymentsDate = paymentsDate;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public MenuOrder getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(MenuOrder menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Date getPaymentsDate() {
        return paymentsDate;
    }

    public void setPaymentsDate(Date paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "paymentCode=" + paymentCode +
                ", paymentsDate=" + paymentsDate +
                '}';
    }
}
