package amazing.productservice;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository pr;
    public ProductService(ProductRepository pr){
        this.pr = pr;
    }

    public Optional<Product> getOneById(int id){
        return pr.findById(id);
    }

    public Iterable<Product> getAllProducts(){
        return pr.findAll();
    }

    public Product creatNewProduct(Product product){
        return pr.save(product);
    }

    public Product updateProduct(Product product){
        pr.deleteById(String.valueOf(product.getId()));
        return pr.save(product);
    }

    public Optional<Product> deleteProduct(int id){
        Optional<Product> deletedProduct = pr.findById(String.valueOf(id));
        pr.deleteById(String.valueOf(id));
        return deletedProduct;
    }

    public boolean isProductCorrect(Product product){
        if(product.getCategory() == null ||
                product.getName() == null ||
                product.getName().isBlank() ||
                product.getPrice() < 0 )
            return false;
        return true;
    }

}
