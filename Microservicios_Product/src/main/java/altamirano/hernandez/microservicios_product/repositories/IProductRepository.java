package altamirano.hernandez.microservicios_product.repositories;

import altamirano.hernandez.microservicios_product.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.codigo =:codigo")
    Optional<Product> findByCodigo(@Param("codigo") String codigo);

    @Query("SELECT p FROM Product p WHERE p.nombre =:nombre")
    Optional<Product> findByNombre(@Param("nombre") String nombre);
}
