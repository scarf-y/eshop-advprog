package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validCODData;
    private Map<String, String> invalidCODData;

    @BeforeEach
    void setUp() {
        // Data valid untuk Voucher Code
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        // Data invalid untuk Voucher Code
        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDVOUCHER");

        // Data valid untuk Cash On Delivery
        validCODData = new HashMap<>();
        validCODData.put("address", "Jl. Bintara No.1");
        validCODData.put("deliveryFee", "10000");

        // Data invalid untuk Cash On Delivery (kosong/null)
        invalidCODData = new HashMap<>();
        invalidCODData.put("address", "");
        invalidCODData.put("deliveryFee", null);
    }

    @Test
    void testCreatePaymentWithValidVoucher() {
        Payment payment = new Payment("1", "VOUCHER", validVoucherData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        Payment payment = new Payment("2", "VOUCHER", invalidVoucherData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithValidCOD() {
        Payment payment = new Payment("3", "CASH_ON_DELIVERY", validCODData);
        assertEquals("PENDING", payment.getStatus()); // Karena belum ada aturan SUCCESS
    }

    @Test
    void testCreatePaymentWithInvalidCOD() {
        Payment payment = new Payment("4", "CASH_ON_DELIVERY", invalidCODData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithNullMethod() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("5", null, validVoucherData));
    }

    @Test
    void testCreatePaymentWithEmptyMethod() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("6", "", validVoucherData));
    }

    @Test
    void testCreatePaymentWithNullPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("7", "VOUCHER", null));
    }
}

