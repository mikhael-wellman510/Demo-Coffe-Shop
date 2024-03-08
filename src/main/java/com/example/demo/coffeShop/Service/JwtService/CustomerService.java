package com.example.demo.coffeShop.Service.JwtService;

import com.example.demo.coffeShop.Entity.JWT.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer getCustomerById(String id);

    List<Customer> getALlCostumer();

    Customer editCustomer(Customer customer);

    Customer findByUserCredentialId(String id);

    Boolean deleteCustomer(String id);
}
