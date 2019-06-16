package restaurant.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurant.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VotesRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @Modifying
    @Query("DELETE FROM Vote v WHERE v.userId=:userId AND v.restaurantId=:restaurantId AND v.voteDay=:voteDay")
    @Transactional
    int delete(@Param("userId") int userId, @Param("restaurantId") int restaurantId, @Param("voteDay") LocalDate voteDay);

    Vote getByUserIdAndRestaurantIdAndVoteDay(int userId, int restaurantId, LocalDate voteDay);

    List<Vote> findByUserIdAndVoteDay(int userId, LocalDate voteDay);

    void deleteByUserIdAndVoteDay(int userId, LocalDate voteDay);

}
