package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private PaymentService paymentService;

    List<Order> orders;
    List<Product> products;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("aaa123");
        product1.setProductName("Minyak telon");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("bbb123");
        product2.setProductName("Gajah guling Pak Prabowo");
        product2.setProductQuantity(1);

        products.add(product1);
        products.add(product2);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(
                "13655", products, 1000L, "Safira Sudrajat"
        );
        orders.add(order1);
        Order order2 = new Order(
                "25533", products, 1000L, "Safira Sudrajat"
        );
        orders.add(order2);
    }

    @Test
    void testAddPayment_Success() {
        Order order = orders.get(1);
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(order.getId(), "VOUCHER", paymentData);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, "VOUCHER", paymentData);

        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
        assertEquals("VOUCHER", result.getMethod());
        assertEquals(paymentData, result.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPayment_Fail_EmptyData() {
        Order order = orders.get(2);

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(order, "VOUCHER", null);
        });

        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testSetStatus_Success() {
        Order order = orders.get(1);
        Payment payment = new Payment("13655", "VOUCHER", new HashMap<>());

        when(paymentRepository.findById("13655")).thenReturn(payment);
        when(orderService.getOrderById("13655")).thenReturn(order);

        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());

        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateOrder(order);
    }

    @Test
    void testSetStatus_Rejected() {
        Payment payment = new Payment("1", "CASH_ON_DELIVERY", new HashMap<>());
        Order order = new Order("1", null, null, "user", OrderStatus.WAITING_PAYMENT.getValue());

        when(paymentRepository.findById("1")).thenReturn(payment);
        when(orderService.getOrderById("1")).thenReturn(order);

        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());

        verify(paymentRepository, times(1)).save(payment);
        verify(orderService, times(1)).updateOrder(order);
    }

    @Test
    void testSetStatus_Fail_NonExistentPayment() {
        when(paymentRepository.findById("99")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(new Payment("99", "VOUCHER", new HashMap<>()), "SUCCESS");
        });

        assertEquals("Payment not found", exception.getMessage());
        verify(paymentRepository, never()).save(any(Payment.class));
    }

    @Test
    void testGetPaymentById_Success() {
        Payment payment = new Payment("1", "BANK_TRANSFER", new HashMap<>());
        when(paymentRepository.findById("1")).thenReturn(payment);

        Payment result = paymentService.getPayment("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("BANK_TRANSFER", result.getMethod());

        verify(paymentRepository, times(1)).findById("1");
    }

    @Test
    void testGetPaymentById_NotFound() {
        when(paymentRepository.findById("99")).thenReturn(null);

        Payment result = paymentService.getPayment("99");

        assertNull(result);
        verify(paymentRepository, times(1)).findById("99");
    }

    @Test
    void testGetAllPayments_Success() {
        Payment payment1 = new Payment("1", "VOUCHER", new HashMap<>());
        Payment payment2 = new Payment("2", "CASH_ON_DELIVERY", new HashMap<>());

        when(paymentRepository.findAll()).thenReturn(List.of(payment1, payment2));

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPayments_Empty() {
        when(paymentRepository.findAll()).thenReturn(List.of());

        List<Payment> payments = paymentService.getAllPayments();

        assertTrue(payments.isEmpty());
        verify(paymentRepository, times(1)).findAll();
    }
}
