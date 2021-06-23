package com.chibuisi.springcloudfunction.repository;

import com.chibuisi.springcloudfunction.entity.PaymentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentReportRepository extends JpaRepository<PaymentReport, UUID> {
}
