package restaurant.voting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import restaurant.voting.model.Menu;
import restaurant.voting.model.Restaurant;
import restaurant.voting.repository.MenuRepository;
import restaurant.voting.service.RestaurantService;
import restaurant.voting.to.NamedTo;
import restaurant.voting.to.RestaurantTo;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController extends AbstractRestaurantController {

    public static final String REST_URL = "/api/admin/restaurants";

    @Autowired
    RestaurantService service;

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id, @RequestParam LocalDate day) {
        return restaurantMapper.toRestaurantTo(repository.get(id, day));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant add(@Valid @RequestBody RestaurantTo to) {
        return service.add(to);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody RestaurantTo to) {
        service.update(to);
    }

    @GetMapping("/{id}/menu") //for combobox for choosing meal of day
    public List<Menu> getMenu(@PathVariable int id) {
        return menuRepository.getMenuByRestaurantId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam LocalDate day) {
        service.delete(id, day);
    }

    @GetMapping("list")
    public List<NamedTo> getAllWithoutMenu() {
        return restaurantMapper.toNamedTo(repository.getAllWithoutMenu());
    }

}
