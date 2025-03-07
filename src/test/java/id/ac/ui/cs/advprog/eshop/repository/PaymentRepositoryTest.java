package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSavePaymentAndRetrieveById() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("1", "VOUCHER", paymentData);
        paymentRepository.save(payment);

        Payment retrievedPayment = paymentRepository.findById("1");

        assertNotNull(retrievedPayment);
        assertEquals("1", retrievedPayment.getId());
        assertEquals("VOUCHER", retrievedPayment.getMethod());
        assertEquals(paymentData, retrievedPayment.getPaymentData());
    }

    @Test
    void testFindPaymentById_NotFound() {
        assertNull(paymentRepository.findById("999"));
    }

    @Test
    void testFindAllPayments() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jl. Merdeka No. 1");
        paymentData2.put("deliveryFee", "5000");

        Payment payment1 = new Payment("1", "VOUCHER", paymentData1);
        Payment payment2 = new Payment("2", "CASH_ON_DELIVERY", paymentData2);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(2, allPayments.size());
        assertTrue(allPayments.contains(payment1));
        assertTrue(allPayments.contains(payment2));
    }

    @Test
    void testSaveMultiplePaymentsWithSameId_ShouldUpdate() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("voucherCode", "ESHOP1234ABC5678");

        Payment initialPayment = new Payment("1", "VOUCHER", initialData);
        paymentRepository.save(initialPayment);

        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("voucherCode", "ESHOP8765DEF4321");

        Payment updatedPayment = new Payment("1", "VOUCHER", updatedData);
        paymentRepository.save(updatedPayment);

        Payment retrievedPayment = paymentRepository.findById("1");

        assertNotNull(retrievedPayment);
        assertEquals("1", retrievedPayment.getId());
        assertEquals("VOUCHER", retrievedPayment.getMethod());
        assertEquals(updatedData, retrievedPayment.getPaymentData());
    }

    @Test
    void testFindAllPayments_EmptyRepository() {
        List<Payment> allPayments = paymentRepository.findAll();
        assertTrue(allPayments.isEmpty());
    }
}
