package com.THBS.cloudkitchenapplication.DTO;

public class PaymentStatusDTO {

    private Long id;
    private Long orderId;
    private String status = "not completed";

    public PaymentStatusDTO() {}

    public PaymentStatusDTO(Long id, Long orderId, String status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
