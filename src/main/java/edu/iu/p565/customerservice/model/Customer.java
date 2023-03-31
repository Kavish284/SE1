package edu.iu.p565.customerservice.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
public class Customer {
    private int id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "email cannot be empty")
    private String email;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer customer)) {
            return false;
        }
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
    
    
}
