package altamirano.hernandez.microservicios_customer.services.impl;

import altamirano.hernandez.microservicios_customer.models.Customer;
import altamirano.hernandez.microservicios_customer.repositories.ICustomerRepository;
import altamirano.hernandez.microservicios_customer.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customers = (List<Customer>) iCustomerRepository.findAll();
        return customers;
    }

    @Override
    public Optional<Customer> findCustomerById(int id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return iCustomerRepository.findByEmail(email);
    }

    @Override
    public void saveCustomer(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(int id) {
        iCustomerRepository.deleteById(id);
    }
}
