package com.chibuisi.springcloudfunction.service;

import com.chibuisi.springcloudfunction.entity.PaymentReport;
import com.chibuisi.springcloudfunction.repository.PaymentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentReportService {

    @Autowired
    private PaymentReportRepository paymentReportRepository;

    public PaymentReport save(PaymentReport paymentReport){
        return paymentReportRepository.save(paymentReport);
    }
}
