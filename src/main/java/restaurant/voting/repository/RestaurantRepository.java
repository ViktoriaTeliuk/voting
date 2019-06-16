package restaurant.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurant.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Override
    void deleteById(Integer id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Query("select distinct r from DayMenu dm join Restaurant r on r.id = dm.restaurant.id join fetch r.dayMenus m join fetch m.menu where dm.menuDay = :day")
    List<Restaurant> getAll(@Param("day") LocalDate day);

    @Query("select distinct r from DayMenu dm join Restaurant r on r.id = dm.restaurant.id join fetch r.dayMenus m join fetch m.menu where dm.restaurant.id = :restaurantId and dm.menuDay = :day")
    Restaurant get(@Param("restaurantId") int restaurantId, @Param("day") LocalDate day);

    @Query("select r from Restaurant r")
    List<Restaurant> getAllWithoutMenu();
}
