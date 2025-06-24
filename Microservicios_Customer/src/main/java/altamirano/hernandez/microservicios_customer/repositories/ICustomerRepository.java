package altamirano.hernandez.microservicios_customer.repositories;

import altamirano.hernandez.microservicios_customer.models.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICustomerRepository extends CrudRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.email =: email")
    Optional<Customer> findByEmail(@Param("email") String email);
}
