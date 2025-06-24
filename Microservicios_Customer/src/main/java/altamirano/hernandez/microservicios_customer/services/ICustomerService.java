package altamirano.hernandez.microservicios_customer.services;

import altamirano.hernandez.microservicios_customer.models.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findAllCustomers();
    Optional<Customer> findCustomerById(int id);
    Optional<Customer> findCustomerByEmail(String email);
    void saveCustomer(Customer customer);
    void deleteCustomerById(int id);
}
