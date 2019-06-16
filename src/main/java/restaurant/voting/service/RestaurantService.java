package restaurant.voting.service;

import restaurant.voting.model.Restaurant;
import restaurant.voting.to.RestaurantTo;

import java.time.LocalDate;

public interface RestaurantService {

    Restaurant add(RestaurantTo to);

    void update(RestaurantTo to);

    void vote(int userId, int restaurantId, LocalDate day);

    void delete(int restaurantId, LocalDate day);
}
