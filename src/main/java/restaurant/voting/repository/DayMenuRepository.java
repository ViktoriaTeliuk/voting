package restaurant.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurant.voting.model.DayMenu;
import restaurant.voting.model.Menu;
import restaurant.voting.model.Restaurant;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface DayMenuRepository extends JpaRepository<DayMenu, Integer> {
    @Override
    @Transactional
    DayMenu save(DayMenu dayMenu);

    @Transactional
    @Override
    void deleteById(Integer id);

    @Query("select dm from DayMenu dm join fetch dm.restaurant join fetch dm.menu where dm.id = :id")
    DayMenu get(@Param("id") int id);

    DayMenu findByRestaurantAndMenuDayAndMenu(Restaurant restaurant, LocalDate menuDay, Menu menu);

    @Transactional
    @Modifying
    @Query("DELETE FROM DayMenu dm WHERE dm.restaurant.id=:id and dm.menuDay=:day")
    void delete(@Param("id" ) int restaurantId, @Param("day") LocalDate day);
}
