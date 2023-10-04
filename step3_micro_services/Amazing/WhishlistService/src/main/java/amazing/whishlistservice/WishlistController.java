package amazing.whishlistservice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WishlistController {
    private WishlistService ws;


}
