package restaurant.voting.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, mappedBy = "restaurant")
    private List<DayMenu> dayMenus;

    public List<DayMenu> getDayMenus() {
        return dayMenus;
    }

    public void setDayMenus(List<DayMenu> dayMenus) {
        this.dayMenus = dayMenus;
    }

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.id, restaurant.name);
        this.setDayMenus(restaurant.getDayMenus());
    }

    public void addDayMenu(DayMenu dayMenu) {
        dayMenus.add(dayMenu);
        dayMenu.setRestaurant(this);
    }

    public void removeDayMenu(DayMenu dayMenu) {
        dayMenus.remove(dayMenu);
        dayMenu.setRestaurant(null);
    }
}
