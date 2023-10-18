package amazing.productservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService ps;
    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return ps.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getOneById(@PathVariable int id){
        Optional<Product> product = ps.getOneById(id);
        return product
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> creatNewProduct(@RequestBody Product product){
        if(!ps.isProductCorrect(product))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        return ps.creatNewProduct(product) == null ? new ResponseEntity<>(HttpStatus.CONFLICT) : new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        if(ps.isProductCorrect(product) || id < 0 )
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ps.updateProduct(product) == null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        return ps.deleteProduct(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK) ;
    }
}
