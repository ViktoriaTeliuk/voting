package restaurant.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurant.voting.model.Menu;

import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Override
    @Transactional
    Menu save(Menu item);

    @Override
    void deleteById(Integer id);

    @Query("select m from Menu m where m.restaurant.id = :id")
    List<Menu> getMenuByRestaurantId(@Param("id") Integer restaurantId);
}

