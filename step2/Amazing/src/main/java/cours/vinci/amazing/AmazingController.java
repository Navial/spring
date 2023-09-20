package cours.vinci.amazing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AmazingController {
    private AmazingService as;
    public AmazingController(AmazingService as){
        this.as = as;
    }
    @GetMapping("/products")
    public Iterable<Product> getAllProducts(){
        return as.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<Product> creatNewProduct(@RequestBody Product product){
        if(!as.isProductCorrect(product))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        return as.creatNewProduct(product) == null ? new ResponseEntity<>(HttpStatus.CONFLICT) : new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        if(as.isProductCorrect(product) || id < 0 )
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return as.updateProduct(product) == null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        return as.deleteProduct(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK) ;
    }
}
