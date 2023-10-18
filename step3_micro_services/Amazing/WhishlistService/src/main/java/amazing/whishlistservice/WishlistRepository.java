package amazing.whishlistservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, String> {
    List<Wishlist> findAllByPseudo(String pseudo);
    Optional<Wishlist> findByPseudoAndProductId(String pseudo, int productId);
    Optional<Wishlist> deleteByPseudo(String pseudo);
    Optional<Wishlist> deleteByPseudoAndProductId(String pseudo, int productId);
}