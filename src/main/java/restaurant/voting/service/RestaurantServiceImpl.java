package restaurant.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurant.voting.model.Restaurant;
import restaurant.voting.model.Vote;
import restaurant.voting.repository.DayMenuRepository;
import restaurant.voting.repository.RestaurantRepository;
import restaurant.voting.repository.VotesRepository;
import restaurant.voting.to.RestaurantTo;
import restaurant.voting.util.RestaurantMapper;
import restaurant.voting.util.exceptions.IllegalRequestDataException;
import restaurant.voting.util.exceptions.UserAlreadyHasVotedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static restaurant.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository repository;

    @Autowired
    VotesRepository votesRepository;

    @Autowired
    RestaurantMapper restaurantMapper;

    @Autowired
    DayMenuRepository dayMenuRepository;

    @Transactional
    @Override
    public Restaurant add(RestaurantTo to) {
        Restaurant restaurant = restaurantMapper.toRestaurantEntity(to);
        if (!restaurant.isNew())
            throw new IllegalRequestDataException(restaurant + " must be new (id=null)");

        return repository.save(restaurant);
    }

    @Transactional
    @Override
    public void update(RestaurantTo to) {
        Restaurant restaurant = restaurantMapper.toRestaurantEntity(to);
        checkNotFoundWithId(restaurant, restaurant.getId());

        repository.save(restaurant);
    }

    @Transactional
    @Override
    public void vote(int userId, int restaurantId, LocalDate day) {
        List<Vote> votes = votesRepository.findByUserIdAndVoteDay(userId, day);
        if (votes.size() == 0)
            votesRepository.save(new Vote(userId, restaurantId, day));
        else {
            if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
                votesRepository.deleteByUserIdAndVoteDay(userId, day);
                votesRepository.save(new Vote(userId, restaurantId, day));
            } else
                throw new UserAlreadyHasVotedException(String.format("userId = ", userId));
        }
    }

    @Transactional
    @Override
    public void delete(int restaurantId, LocalDate day) {
        dayMenuRepository.delete(restaurantId, day);
    }
}
