package com.example.flyway.Repository;

import com.example.flyway.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepo extends JpaRepository<Customer, Long> {

}
