package altamirano.hernandez.microservicios_invoices.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;


@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El customerId es obligatorio")
    private int customerId;
    @NotBlank(message = "El numero es obligatorio")
    private String number;
    @NotBlank(message = "Los detalles son obligatorios")
    private String details;
    @Positive(message = "El monto debe ser mayor a 0")
    private double amount;

    public Invoice() {

    }

    public Invoice(int customerId, String number, String details, double amount) {
        this.customerId = customerId;
        this.number = number;
        this.details = details;
        this.amount = amount;
    }

    public Invoice(int id, int customerId, String number, String details, double amount) {
        this.id = id;
        this.customerId = customerId;
        this.number = number;
        this.details = details;
        this.amount = amount;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && customerId == invoice.customerId && Double.compare(amount, invoice.amount) == 0 && Objects.equals(number, invoice.number) && Objects.equals(details, invoice.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, number, details, amount);
    }
}
