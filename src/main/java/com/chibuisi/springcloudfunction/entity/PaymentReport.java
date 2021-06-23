package com.chibuisi.springcloudfunction.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PaymentReport {
    @Id
    private UUID uuid;
    private String user;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private String status;
}
