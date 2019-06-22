package restaurant.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restaurant.voting.repository.RestaurantRepository;
import restaurant.voting.to.RestaurantTo;
import restaurant.voting.util.RestaurantMapper;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractRestaurantController {
    @Autowired
    RestaurantRepository repository;

    @Autowired
    RestaurantMapper restaurantMapper;

    @GetMapping("onDate")
    public List<RestaurantTo> getAll(@RequestParam LocalDate day) {
        return restaurantMapper.toRestaurantsTo(repository.getAll(day));
    }

    @GetMapping
    public List<RestaurantTo> getAllForToday() {
        return getAll(LocalDate.now());
    }
}
