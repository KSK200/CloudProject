package com.karthik.cloudkitchenapplication.DTO;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private LocalDate deliveryDate;
    private int numberOfPeople;
    private Long customerId;
    private Long catererId;
    private List<String> dishNames;

    public  OrderDTO() {}
    public OrderDTO(LocalDate deliveryDate, int numberOfPeople, Long customerId, Long catererId,
            List<String> dishNames) {
        this.deliveryDate = deliveryDate;
        this.numberOfPeople = numberOfPeople;
        this.customerId = customerId;
        this.catererId = catererId;
        this.dishNames = dishNames;
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
    public List<String> getDishNames() {
        return dishNames;
    }
    public void setDishNames(List<String> dishNames) {
        this.dishNames = dishNames;
    }
    @Override
    public String toString() {
        return "OrderDTO [deliveryDate=" + deliveryDate + ", numberOfPeople=" + numberOfPeople + ", customerId="
                + customerId + ", catererId=" + catererId + ", dishNames=" + dishNames + "]";
    }

    

    // Constructors, getters, and setters
}
