package com.DevTraining.DemoFlyway.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="employee")
public class User {
    @Id
    private int id;
    private String username;
    private String first_name;
    private String email;
}
