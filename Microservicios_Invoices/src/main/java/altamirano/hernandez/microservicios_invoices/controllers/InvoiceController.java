package altamirano.hernandez.microservicios_invoices.controllers;

import altamirano.hernandez.microservicios_invoices.dto.InvoiceDto;
import altamirano.hernandez.microservicios_invoices.models.Invoice;
import altamirano.hernandez.microservicios_invoices.services.IInvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    private IInvoiceService iInvoiceService;

    @GetMapping("")
    public ResponseEntity<?> findAllCustomers() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Invoice> customers = iInvoiceService.findAllInvoices();
            if (customers.isEmpty()) {
                json.put("message", "Invoices not found");
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
            Optional<Invoice> invoice = iInvoiceService.findById(id);
            if (!invoice.isPresent()) {
                json.put("error", "Invoice not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(invoice.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customerId/{customerId}")
    public ResponseEntity<?> findByCustomerId(@PathVariable int customerId) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Invoice> invoice = iInvoiceService.findByCustomerId(customerId);
            if (!invoice.isPresent()) {
                json.put("error", "Invoice not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(invoice.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<?> findByNumber(@PathVariable String number) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Invoice> invoice = iInvoiceService.findByNumber(number);
            if (!invoice.isPresent()) {
                json.put("error", "Invoice not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(invoice.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Invoice invoice, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            try {
                iInvoiceService.saveInvoice(invoice);
                json.put("success", "Invoice saved");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody InvoiceDto invoiceDto, @PathVariable int id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            Optional<Invoice> invoice = iInvoiceService.findById(id);
            if (!invoice.isPresent()) {
                json.put("error", "Invoice not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            Invoice invoiceToUpdate = invoice.get();
            invoiceToUpdate.setCustomerId(invoiceDto.getCustomerId());
            invoiceToUpdate.setNumber(invoiceDto.getNumber());
            invoiceToUpdate.setDetails(invoiceDto.getDetails());
            invoiceToUpdate.setAmount(invoiceDto.getAmount());
            iInvoiceService.saveInvoice(invoiceToUpdate);

            json.put("success", "Invoice updated");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Invoice> customer = iInvoiceService.findById(id);
            if (!customer.isPresent()) {
                json.put("error", "Invoice not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            iInvoiceService.deleteById(id);
            json.put("success", "Invoice deleted");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
