package altamirano.hernandez.microservicios_product.services.impl;

import altamirano.hernandez.microservicios_product.models.Product;
import altamirano.hernandez.microservicios_product.repositories.IProductRepository;
import altamirano.hernandez.microservicios_product.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        Optional<Product> producto = iProductRepository.findById(id);
        return producto;
    }

    @Override
    public Optional<Product> findByCodigo(String codigo) {
        Optional<Product> producto = iProductRepository.findByCodigo(codigo);
        return producto;
    }

    @Override
    public Optional<Product> findByNombre(String nombre) {
        Optional<Product> producto = iProductRepository.findByNombre(nombre);
        return producto;
    }

    @Override
    public void saveProduct(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        iProductRepository.deleteById(id);
    }
}
