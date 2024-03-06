package com.karthik.cloudkitchenapplication.DTO;
import java.time.LocalDate;


public class OrderDTO {
    private Long id;
    private LocalDate deliveryDate;
    private int numberOfPeople;
    private Long customerId;
    private Long catererId;

    
    @Override
    public String toString() {
        return "OrderDTO [id=" + id + ", deliveryDate=" + deliveryDate + ", numberOfPeople=" + numberOfPeople
                + ", customerId=" + customerId + ", catererId=" + catererId + "]";
    }


    public OrderDTO(){}
    
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }


    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public int getNumberOfPeople() {
        return numberOfPeople;
    }


    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }


    public Long getCustomerId() {
        return customerId;
    }


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public Long getCatererId() {
        return catererId;
    }


    public void setCatererId(Long catererId) {
        this.catererId = catererId;
    }


    public OrderDTO(Long id, LocalDate deliveryDate, int numberOfPeople, Long customerId, Long catererId) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.numberOfPeople = numberOfPeople;
        this.customerId = customerId;
        this.catererId = catererId;
    }

    

    // Constructors, getters, and setters
}
