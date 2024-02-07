package com.ohgiraffers.springjpa.order.service;

import com.ohgiraffers.springjpa.order.entity.Payments;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentsService {

    public Payments orderPayments(int value){

        Payments payments = new Payments();
        payments.setPaymentsDate(new Date());

        // 실제 결제 로직은 전혀 다르기 때문에 대체(외부 api 등을 사용한다.)

        return payments;
    }
}
