package com.example.flyway.Controller;

import com.example.flyway.Model.Contact;
import com.example.flyway.Model.Customer;
import com.example.flyway.Repository.CustRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustCtrler extends com.example.flyway.Model.Customer {
    @Autowired
    private CustRepo custRepo;
    private Customer cust;

    @RequestMapping(path = "/customers/", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return custRepo.findAll();
    }

    @RequestMapping(path = "/customers/{customerId}/contacts/", method = RequestMethod.GET)
    public List<Contact> getCustomerContacts(@PathVariable("customerId") Long cId) {
        return custRepo.getOne(cId).getContacts(); // not sure why findOne or findById does not work
        // checkout https://stackoverflow.com/questions/24482117/when-use-getone-and-findone-methods-spring-data-jpa
    }
}
