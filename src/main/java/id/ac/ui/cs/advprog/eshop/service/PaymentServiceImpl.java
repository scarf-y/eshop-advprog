package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null");
        }

        Payment payment = new Payment(order.getId(), method, paymentData);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());
        if (existingPayment == null) {
            throw new IllegalArgumentException("Payment not found");
        }

        existingPayment.setStatus(status);
        Order order = orderService.findById(payment.getId());
        String newStatus = OrderStatus.FAILED.getValue();
        if ("SUCCESS".equals(status)) {
            newStatus = OrderStatus.SUCCESS.getValue();
        } else if ("REJECTED".equals(status)) {
            newStatus = OrderStatus.FAILED.getValue();
        }

        paymentRepository.save(existingPayment);
        orderService.updateStatus(order.getId(), newStatus);

        return existingPayment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
