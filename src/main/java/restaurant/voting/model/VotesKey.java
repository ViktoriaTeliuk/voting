package restaurant.voting.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class VotesKey implements Serializable {
    private Integer userId;
    private Integer restaurantId;
    private LocalDate voteDay;

    public VotesKey() {
    }

    public VotesKey(Integer userId, Integer restaurantId, LocalDate voteDay) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.voteDay = voteDay;
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

    public void setVoteDay(LocalDate voteDay) {
        this.voteDay = voteDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotesKey votesKey = (VotesKey) o;
        return Objects.equals(userId, votesKey.userId) &&
                Objects.equals(restaurantId, votesKey.restaurantId) &&
                Objects.equals(voteDay, votesKey.voteDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restaurantId, voteDay);
    }
}
