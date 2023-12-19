package com.rentSystem.service;

import com.rentSystem.model.Payment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaymentService {
    List<Payment> getAllPayments();
    void savePayment(Payment payment);
    Payment getPaymentById(long id);
    void deletePaymentById(long id);
    Page<Payment> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
