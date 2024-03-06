package com.karthik.cloudkitchenapplication.DTO;

public class DishDTO {
    private Long id;
    private String name;
    private Long orderId;

    // Constructors, getters, and setters

    public DishDTO() {
    }

    public DishDTO(Long id, String name, Long orderId) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
