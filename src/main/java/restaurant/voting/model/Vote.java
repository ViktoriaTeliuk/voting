package restaurant.voting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes")
@IdClass(VotesKey.class)
public class Vote {

    @Id
    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userId;

    @Id
    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private Integer restaurantId;

    @Id
    @Column(name = "vote_day", nullable = false)
    @NotNull
    private LocalDate voteDay;


    public Vote() {
    }

    public Vote(@NotNull Integer userId, @NotNull Integer restaurantId, @NotNull LocalDate voteDay) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.voteDay = voteDay;
    }

    public Vote(VotesKey vk) {
        this.restaurantId = vk.getRestaurantId();
        this.userId = vk.getUserId();
        this.voteDay = vk.getVoteDay();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getVoteDay() {
        return voteDay;
    }

    public void setVoteDay(LocalDate vote_day) {
        this.voteDay = vote_day;
    }
}
