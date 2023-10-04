package amazing.whishlistservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "wishlists")
public class Wishlist {
    @Id
    private int clientId;
    private int productId;

}
