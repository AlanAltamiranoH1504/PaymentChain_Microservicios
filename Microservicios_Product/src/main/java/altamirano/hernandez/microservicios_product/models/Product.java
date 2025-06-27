package altamirano.hernandez.microservicios_product.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    public Product() {
    }

    public Product(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Product(int id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    //G y S
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
