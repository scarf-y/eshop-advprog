package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (paymentData == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = determineStatus();
    }

    private String determineStatus() {
        if ("VOUCHER".equals(method)) {
            return validateVoucher() ? "SUCCESS" : "REJECTED";
        } else if ("CASH_ON_DELIVERY".equals(method)) {
            return validateCOD() ? "SUCCESS" : "REJECTED";
        }
        return "REJECTED";
    }

    private boolean validateVoucher() {
        String voucher = paymentData.get("voucherCode");
        return voucher != null && voucher.length() == 16 && voucher.startsWith("ESHOP") && voucher.replaceAll("\\D", "").length() == 8;
    }

    private boolean validateCOD() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        return address != null && !address.isEmpty() && deliveryFee != null && !deliveryFee.isEmpty();
    }

}
