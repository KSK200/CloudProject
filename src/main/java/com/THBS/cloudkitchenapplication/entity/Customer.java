package com.THBS.cloudkitchenapplication.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FullName;
    @Column(unique = true)
    private String Email;
    private String Address;
    private String Phonenumber;
    private String Password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    

    public Customer() {}

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((FullName == null) ? 0 : FullName.hashCode());
        result = prime * result + ((Email == null) ? 0 : Email.hashCode());
        result = prime * result + ((Address == null) ? 0 : Address.hashCode());
        result = prime * result + ((Phonenumber == null) ? 0 : Phonenumber.hashCode());
        result = prime * result + ((Password == null) ? 0 : Password.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (FullName == null) {
            if (other.FullName != null)
                return false;
        } else if (!FullName.equals(other.FullName))
            return false;
        if (Email == null) {
            if (other.Email != null)
                return false;
        } else if (!Email.equals(other.Email))
            return false;
        if (Address == null) {
            if (other.Address != null)
                return false;
        } else if (!Address.equals(other.Address))
            return false;
        if (Phonenumber == null) {
            if (other.Phonenumber != null)
                return false;
        } else if (!Phonenumber.equals(other.Phonenumber))
            return false;
        if (Password == null) {
            if (other.Password != null)
                return false;
        } else if (!Password.equals(other.Password))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", FullName=" + FullName + ", Email=" + Email + ", Address=" + Address
                + ", Phonenumber=" + Phonenumber + ", Password=" + Password + "]";
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getFullName() {
        return FullName;
    }


    public void setFullName(String fullName) {
        FullName = fullName;
    }


    public String getEmail() {
        return Email;
    }


    public void setEmail(String email) {
        Email = email;
    }


    public String getAddress() {
        return Address;
    }


    public void setAddress(String address) {
        Address = address;
    }


    public String getPhonenumber() {
        return Phonenumber;
    }


    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }


    public String getPassword() {
        return Password;
    }


    public void setPassword(String password) {
        Password = password;
    }


    public Customer(Long id, String fullName, String email, String address, String phonenumber, String password) {
        this.id = id;
        FullName = fullName;
        Email = email;
        Address = address;
        Phonenumber = phonenumber;
        Password = password;
    }
    
    
}
