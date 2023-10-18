package amazing.whishlistservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wr;
    private final ProductProxy productProxy;

    public WishlistService(final WishlistRepository wr, final ProductProxy pp) {
        this.wr = wr;
        this.productProxy = pp;
    }

    public Wishlist addToWishList(String pseudo, int productId){
        Wishlist wishlist = new Wishlist(0, pseudo, productId);
        return wr.save(wishlist);
    }
    // DELETE /wishlists/{pseudo}/{productId} – Suppression du produit productId de la wishlist de l’utilisateur pseudo
    public Optional<Wishlist> deleteWishlist(String pseudo, int productId) {
        Optional<Wishlist> deletedWish = wr.deleteByPseudoAndProductId(pseudo, productId);

        return deletedWish;
    }

    public Optional<Wishlist> deleteUsersWishlist(String pseudo) {
        Optional<Wishlist> deletedWish = wr.deleteByPseudo(pseudo);
        return deletedWish;
    }

    // GET /wishlists/user/{pseudo} – Récupération de la liste des produits (avec toutes leurs informations, venant du service de produits) dans la wishlist de l’utilisateur pseudo
    public List<Product> getProductsFromWishlist(String pseudo){
        final ArrayList<Product> products = new ArrayList<>();
        List<Wishlist> productsId = wr.findAllByPseudo(pseudo);
        for (Wishlist wl : productsId) {
            Product p = productProxy.getOneById(wl.getProductId()).getBody();
            products.add(p);
        }

        return products;
    }
    // DELETE /wishlists/product/{productId} – Suppression du produit productId des wishlists des utilisateurs


}
