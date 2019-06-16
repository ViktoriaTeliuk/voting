package restaurant.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "day_menu")
public class DayMenu extends AbstractBaseEntity {

    @Column(name = "menu_day", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate menuDay;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1)
    private BigInteger price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "meal_id")
    private Menu menu;

    public DayMenu() {
    }

    public DayMenu(@NotNull LocalDate menuDay, @NotNull @Range(min = 1) BigInteger mealPrice, @NotNull Restaurant restaurant, @NotNull Menu menu) {
        this.menuDay = menuDay;
        this.price = mealPrice;
        this.restaurant = restaurant;
        this.menu = menu;
    }

    public DayMenu(Integer id, @NotNull LocalDate menuDay, @NotNull @Range(min = 1) BigInteger mealPrice, @NotNull Restaurant restaurant, @NotNull Menu menu) {
        super(id);
        this.menuDay = menuDay;
        this.price = mealPrice;
        this.restaurant = restaurant;
        this.menu = menu;
    }

    public DayMenu(DayMenu dayMenu) {
        this.setId(dayMenu.getId());
        this.setMenuDay(dayMenu.getMenuDay());
        this.setMealPrice(dayMenu.getMealPrice());
        this.setRestaurant(dayMenu.getRestaurant());
        this.setMenu(dayMenu.getMenu());
    }

    public LocalDate getMenuDay() {
        return menuDay;
    }

    public void setMenuDay(LocalDate menu_day) {
        this.menuDay = menu_day;
    }

    public BigInteger getMealPrice() {
        return price;
    }

    public void setMealPrice(BigInteger price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu meal) {
        this.menu = meal;
    }

}
