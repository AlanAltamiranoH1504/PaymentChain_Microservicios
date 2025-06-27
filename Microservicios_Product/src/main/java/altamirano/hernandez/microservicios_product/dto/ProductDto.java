package altamirano.hernandez.microservicios_product.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductDto {
    @NotBlank(message = "El codigo es obligatorio")
    private String codigo;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    public ProductDto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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
