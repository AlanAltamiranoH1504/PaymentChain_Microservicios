package altamirano.hernandez.microservicios_customer.controllers;

import altamirano.hernandez.microservicios_customer.dto.CustomerDto;
import altamirano.hernandez.microservicios_customer.models.Customer;
import altamirano.hernandez.microservicios_customer.services.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<?> findAllCustomers() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Customer> customers = customerService.findAllCustomers();
            if (customers.isEmpty()) {
                json.put("message", "Customers not found");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(customers);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Customer> customer = customerService.findCustomerById(id);
            if (!customer.isPresent()) {
                json.put("error", "Customer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(customer.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Customer> customer = customerService.findCustomerByEmail(email);
            if (!customer.isPresent()) {
                json.put("error", "Customer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(customer.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            try {
                customerService.saveCustomer(customer);
                json.put("success", "Customer saved");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CustomerDto customerDto, @PathVariable int id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            Optional<Customer> customer = customerService.findCustomerById(id);
            if (!customer.isPresent()) {
                json.put("error", "Customer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            Customer customerToUpdate = customer.get();
            customerToUpdate.setNombre(customerDto.getNombre());
            customerToUpdate.setApellidos(customerDto.getApellido());
            customerToUpdate.setEmail(customerDto.getEmail());
            customerToUpdate.setTelefono(customerDto.getTelefono());
            customerService.saveCustomer(customerToUpdate);

            json.put("success", "Customer updated");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Customer> customer = customerService.findCustomerById(id);
            if (!customer.isPresent()) {
                json.put("error", "Customer not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            customerService.deleteCustomerById(id);
            json.put("success", "Customer deleted");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
