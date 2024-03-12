package com.THBS.cloudkitchenapplication.DTO;

public class OrderStatusDTO {

    private Long id;
    private Long orderId;
    private String status = "pending";
    private double price = 0.0;

    public OrderStatusDTO() {}

    public OrderStatusDTO(Long id, Long orderId, String status, double price) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderStatusDTO [id=" + id + ", orderId=" + orderId + ", status=" + status + ", price=" + price + "]";
    }
    
}
