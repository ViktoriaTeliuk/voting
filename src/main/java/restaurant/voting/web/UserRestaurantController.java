package restaurant.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import restaurant.voting.AuthorizedUser;
import restaurant.voting.service.RestaurantService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController extends AbstractRestaurantController{
    public static final String REST_URL = "/api/user/restaurants";

    @Autowired
    RestaurantService service;

    @PostMapping("/vote")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestParam int restaurantId, @AuthenticationPrincipal AuthorizedUser user) {
        service.vote(user.getId(), restaurantId, LocalDate.now());
    }
}
