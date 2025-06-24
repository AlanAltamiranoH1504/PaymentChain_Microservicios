package altamirano.hernandez.microservicios_invoices.services;

import altamirano.hernandez.microservicios_invoices.models.Invoice;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    List<Invoice> findAllInvoices();
    Optional<Invoice> findById(int id);
    Optional<Invoice> findByCustomerId(int customerId);
    Optional<Invoice> findByNumber(String number);
    void saveInvoice(Invoice invoice);
    void deleteById(int id);
}
