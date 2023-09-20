package cours.vinci.amazing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class AmazingController {

    private final ConcurrentHashMap<Integer, Product> products = new ConcurrentHashMap<>();
    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return products.values();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> creatNewProduct(@RequestBody Product product){
        if (products.contains(product)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        products.put(product.getId(), product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        if(!products.contains(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(product.getId() != id)  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Product old = products.put(id, product);
        return new ResponseEntity<>(old, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        Product deleted = products.remove(id);
        if (deleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        }
    }
}
