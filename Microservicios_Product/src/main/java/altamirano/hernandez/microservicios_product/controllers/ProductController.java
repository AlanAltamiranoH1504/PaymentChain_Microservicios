package altamirano.hernandez.microservicios_product.controllers;

import altamirano.hernandez.microservicios_product.dto.ProductDto;
import altamirano.hernandez.microservicios_product.models.Product;
import altamirano.hernandez.microservicios_product.services.impl.ProductService;
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
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    public ResponseEntity<?> findAllCustomers() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Product> products = productService.findAllProducts();
            if (products.isEmpty()) {
                json.put("message", "Products not found");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Product> product = productService.findById(id);
            if (!product.isPresent()) {
                json.put("error", "Product not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable String codigo) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Product> product = productService.findByCodigo(codigo);
            if (!product.isPresent()) {
                json.put("error", "Product not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> findByNombre(@PathVariable String nombre) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Product> product = productService.findByNombre(nombre);
            if (!product.isPresent()) {
                json.put("error", "Product not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            return ResponseEntity.status(HttpStatus.OK).body(product.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            try {
                productService.saveProduct(product);
                json.put("success", "Product saved");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDto productDto, @PathVariable int id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            Optional<Product> product = productService.findById(id);
            if (!product.isPresent()) {
                json.put("error", "Product not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            Product productToUpdate = product.get();
            productToUpdate.setNombre(productDto.getNombre());
            productToUpdate.setCodigo(productDto.getCodigo());
            productService.saveProduct(productToUpdate);

            json.put("success", "Product updated");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Product> product = productService.findById(id);
            if (!product.isPresent()) {
                json.put("error", "Product not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            productService.deleteById(id);
            json.put("success", "Product deleted");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
