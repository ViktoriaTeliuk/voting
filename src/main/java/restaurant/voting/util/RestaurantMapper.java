package restaurant.voting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restaurant.voting.model.DayMenu;
import restaurant.voting.model.Menu;
import restaurant.voting.model.Restaurant;
import restaurant.voting.repository.DayMenuRepository;
import restaurant.voting.repository.MenuRepository;
import restaurant.voting.repository.RestaurantRepository;
import restaurant.voting.to.MenuItemTo;
import restaurant.voting.to.NamedTo;
import restaurant.voting.to.RestaurantTo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {
    public static final int CENT_FACTOR = 100;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    DayMenuRepository dayMenuRepository;

    public RestaurantTo toRestaurantTo(Restaurant restaurant) {
        RestaurantTo restaurantTo = new RestaurantTo(restaurant.getId(), restaurant.getName(), null, null);
        List<MenuItemTo> mealsTo = new ArrayList<>();
        if (restaurant.getDayMenus().size() > 0) {
            restaurantTo.setDate(restaurant.getDayMenus().stream().findFirst().get().getMenuDay());
            restaurant.getDayMenus().forEach(dayMenu -> mealsTo.add(new MenuItemTo(dayMenu.getMenu().getId(), dayMenu.getMenu().getName(),
                    (double) dayMenu.getMealPrice().doubleValue() / CENT_FACTOR)));
            restaurantTo.setMeals(mealsTo);
        }
        return restaurantTo;
    }

    public Restaurant toRestaurantEntity(RestaurantTo restaurantTo) {
        Restaurant restaurant = new Restaurant(restaurantTo.getId(), restaurantTo.getName());
        List<DayMenu> dayMenus = new ArrayList<>();
        restaurantTo.getMenu().forEach(meal -> dayMenus.add(new DayMenu(
                meal.getId() == null ? null : dayMenuRepository.findByRestaurantAndMenuDayAndMenu(restaurant, restaurantTo.getDate(), menuRepository.getOne(meal.getId())).getId(),
                restaurantTo.getDate(),
                BigInteger.valueOf((long) (meal.getPrice() * CENT_FACTOR)),
                restaurant,
                meal.getId() == null ? new Menu(restaurant, meal.getId(), meal.getName(), BigInteger.valueOf((long) (meal.getPrice() * CENT_FACTOR)), true)
                        : menuRepository.getOne(meal.getId()))));
        restaurant.setDayMenus(dayMenus);
        return restaurant;
    }

    public List<RestaurantTo> toRestaurantsTo(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::toRestaurantTo).collect(Collectors.toList());
    }

    public NamedTo toNamedTo(Restaurant restaurant) {
        return new NamedTo(restaurant.getId(), restaurant.getName());
    }

    public List<NamedTo> toNamedTo(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::toNamedTo).collect(Collectors.toList());
    }
}
