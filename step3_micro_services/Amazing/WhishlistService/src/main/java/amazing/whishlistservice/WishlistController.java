package amazing.whishlistservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class WishlistController {
    // ENDPOINTS
    private WishlistService ws;


    // PUT /wishlists/{pseudo}/{productId} – Ajout du produit productId à la wishlist de l’utilisateur pseudo
    @PostMapping("/wishlists/{pseudo}/{productId}")
    public ResponseEntity<Wishlist> addWishlist(@PathVariable String pseudo, @PathVariable int productId){
        // TODO
        return ws.addToWishList(pseudo, productId) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/wishlists/{pseudo}/{productId}")
    public ResponseEntity<Wishlist> deleteWish(@PathVariable String pseudo, @PathVariable int productId){
        return ws.deleteWishlist(pseudo, productId).isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK) ;
    }
    // GET /wishlists/user/{pseudo} – Récupération de la liste des produits (avec toutes leurs informations, venant du service de produits) dans la wishlist de l’utilisateur pseudo
    @GetMapping("/wishlists/user/{pseudo}")
    public List<Product> getProductsFromWishlist(@PathVariable String pseudo){
        return ws.getProductsFromWishlist(pseudo);
    }

    //DELETE /wishlists/user/{pseudo} – Suppression de la wishlist de l’utilisateur pseudo
    @DeleteMapping("/products/user/{pseudo}")
    public ResponseEntity<Wishlist> deleteWish(@PathVariable String pseudo){
        return ws.deleteUsersWishlist(pseudo).isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK) ;
    }
    // DELETE /wishlists/product/{productId} – Suppression du produit productId des wishlists des utilisateurs


}
