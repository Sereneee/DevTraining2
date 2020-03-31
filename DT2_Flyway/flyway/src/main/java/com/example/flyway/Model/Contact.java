package com.example.flyway.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore // keeps the endpoint, that we will create, from serializing the customer details multiple times
    @ManyToOne // indicates to JPA that Many contacts To One customer can exist
    @JoinColumn (name = "customer_id") // indicates to JPA which table column will be used as a foreign key
    private Customer customer;
    private String name;
    private String email;
    private String phone;
}
