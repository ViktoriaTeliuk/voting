package restaurant.voting.to;

import java.time.LocalDate;
import java.util.List;

public class RestaurantTo {
    private Integer id;
    private String name;
    private LocalDate date;
    private List<MenuItemTo> menu;

    public RestaurantTo(Integer id, String name, LocalDate date, List<MenuItemTo> menu) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.menu = menu;
    }

    public RestaurantTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<MenuItemTo> getMenu() {
        return menu;
    }

    public void setMeals(List<MenuItemTo> menu) {
        this.menu = menu;
    }
}
