package restaurant.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @Range(min = 1)
    private BigInteger price;

    @Column(name = "enabled", columnDefinition = "bool default true")
    private Boolean enabled;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private List<DayMenu> dayMenuList;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger mealPrice) {
        this.price = mealPrice;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Menu() {
    }

    public Menu(Restaurant restaurant, Integer id, String name, BigInteger price, boolean enabled) {
        this.restaurant = restaurant;
        this.id = id;
        this.name = name;
        this.price = price;
        this.enabled = enabled;
    }

    public Menu(Menu menu) {
        this.id = menu.id;
        this.enabled = menu.enabled;
        this.price = menu.price;
        this.name = menu.name;
        this.restaurant = menu.restaurant;
    }
}
