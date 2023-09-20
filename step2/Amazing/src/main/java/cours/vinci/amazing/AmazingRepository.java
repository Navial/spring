package cours.vinci.amazing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazingRepository extends CrudRepository<Product, String> {



}
