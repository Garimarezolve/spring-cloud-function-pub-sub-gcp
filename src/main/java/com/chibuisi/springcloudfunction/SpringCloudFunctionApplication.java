package com.chibuisi.springcloudfunction;

import com.chibuisi.springcloudfunction.entity.Payment;
import com.chibuisi.springcloudfunction.entity.PaymentReport;
import com.chibuisi.springcloudfunction.service.PaymentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.function.Function;

@SpringBootApplication
public class SpringCloudFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFunctionApplication.class, args);
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PaymentReportService paymentReportService;

    @Bean
    public Function<Payment, PaymentReport> uppercase() {
        return (input) -> {
            PaymentReport paymentReport = PaymentReport.builder()
                    .user(input.getUser())
                    .amount(input.getAmount())
                    .dateTime(LocalDateTime.now())
                    .status("success")
                    .build();
            System.out.println("Data processed: -> "+paymentReport);
            PaymentReport report = paymentReportService.save(paymentReport);
            sendEmail(report);
            return report;
        };
    }

    public void sendEmail(PaymentReport paymentReport){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try{
            mimeMessageHelper.setTo(paymentReport.getUser());
            mimeMessageHelper.setSubject("Your payment has been received and processed successfully");
            mimeMessageHelper.setText("Payment for account: " + paymentReport.getUser()
                    + "\nAmount " + paymentReport.getAmount()
                    + "has been processed successfully on " + paymentReport.getDateTime()
                    + "\n\nThank you for your continued support \n\n");
        }
        catch (MessagingException messagingException){
            messagingException.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }

}
