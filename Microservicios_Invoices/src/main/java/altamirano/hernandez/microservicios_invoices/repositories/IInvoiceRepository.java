package altamirano.hernandez.microservicios_invoices.repositories;

import altamirano.hernandez.microservicios_invoices.models.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IInvoiceRepository extends CrudRepository<Invoice, Integer> {
    @Query("SELECT i FROM Invoice i WHERE i.customerId =:customerId")
    Optional<Invoice> findByCustomerId(@Param("customerId") int customerId);

    @Query("SELECT i FROM Invoice i WHERE i.number =:number")
    Optional<Invoice> findByNumber(@Param("number") String number);
}
