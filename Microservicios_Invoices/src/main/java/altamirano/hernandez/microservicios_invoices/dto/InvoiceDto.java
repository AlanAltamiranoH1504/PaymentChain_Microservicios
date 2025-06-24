package altamirano.hernandez.microservicios_invoices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class InvoiceDto {
    @NotBlank(message = "El customerId es obligatorio")
    private int customerId;
    @NotBlank(message = "El numero es obligatorio")
    private String number;
    @NotBlank(message = "Los detalles son obligatorios")
    private String details;
    @Positive(message = "El monto debe ser mayor a 0")
    private double amount;

    public InvoiceDto(int customerId, String number, String details, double amount) {
        this.customerId = customerId;
        this.number = number;
        this.details = details;
        this.amount = amount;
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
}
