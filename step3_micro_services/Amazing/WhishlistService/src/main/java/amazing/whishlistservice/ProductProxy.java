package amazing.whishlistservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "products")
public interface ProductProxy {
    @GetMapping("/products")
    Iterable<Product> getAllProducts();

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getOneById(@PathVariable int id);

    @PostMapping("/products")
    ResponseEntity<Product> creatNewProduct(@RequestBody Product product);

    @PutMapping("/products/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product);

    @DeleteMapping("/products/{id}")
    ResponseEntity<Product> deleteProduct(@PathVariable int id);
}
