package com.example.demo.coffeShop.Service.Impl.JwtImpl;

import com.example.demo.coffeShop.Entity.JWT.Customer;
import com.example.demo.coffeShop.Repositori.JWT.CustomerRepositori;
import com.example.demo.coffeShop.Service.JwtService.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepositori customerRepositori;
    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepositori.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepositori.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getALlCostumer() {
        return customerRepositori.findAll();
    }

    @Override
    public Customer editCustomer(Customer customer) {
        return customerRepositori.save(customer);
    }

    @Override
    public Customer findByUserCredentialId(String id) {



        return customerRepositori.findByUserCredentialId(id).orElse(null);
    }

    @Override
    public Boolean deleteCustomer(String id) {
        return null;
    }
}
