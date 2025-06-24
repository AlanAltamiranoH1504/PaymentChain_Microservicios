package altamirano.hernandez.microservicios_invoices.services.impl;

import altamirano.hernandez.microservicios_invoices.models.Invoice;
import altamirano.hernandez.microservicios_invoices.repositories.IInvoiceRepository;
import altamirano.hernandez.microservicios_invoices.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private IInvoiceRepository iInvoiceRepository;

    @Override
    public List<Invoice> findAllInvoices() {
        List<Invoice> invoices = (List<Invoice>) iInvoiceRepository.findAll();
        return invoices;
    }

    @Override
    public Optional<Invoice> findById(int id) {
        return iInvoiceRepository.findById(id);
    }

    @Override
    public Optional<Invoice> findByCustomerId(int customerId) {
        return iInvoiceRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<Invoice> findByNumber(String number) {
        return iInvoiceRepository.findByNumber(number);
    }

    @Override
    public void saveInvoice(Invoice invoice) {
        iInvoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(int id) {
        iInvoiceRepository.deleteById(id);
    }
}
