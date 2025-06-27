package altamirano.hernandez.microservicios_product.services;

import altamirano.hernandez.microservicios_product.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAllProducts();
    Optional<Product> findById(int id);
    Optional<Product> findByCodigo(String codigo);
    Optional<Product> findByNombre(String nombre);
    void saveProduct(Product product);
    void deleteById(int id);
}
