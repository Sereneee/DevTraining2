package com.example.flyway.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore /* keeps the existing endpoint, /customers/, from serializing contacts of customers,
                   which could be expensive as there are no limits to the number of contacts on a customer */
    @OneToMany(mappedBy = "customer") // indicates to JPA that can exist One customer To Many contacts
    private List<Contact> contacts;
}
