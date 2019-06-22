package restaurant.voting.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;
import restaurant.voting.repository.VotesRepository;
import restaurant.voting.to.RestaurantTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.voting.TestData.*;
import static restaurant.voting.util.TestUtil.userHttpBasic;

public class UserRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestaurantController.REST_URL + '/';

    @Autowired
    VotesRepository repository;

    @Test
    @Transactional(propagation = Propagation.NEVER)
    public void votedAlready() throws Exception {
        mockMvc.perform(post(REST_URL + "vote")
                .param("restaurantId", RESTAURANT_ID_2.toString())
                .with(userHttpBasic(USER_2)))
                .andExpect(status().isNoContent())
                .andDo(print());

        if (LocalTime.now().isBefore(LocalTime.of(11, 0))) {
            mockMvc.perform(post(REST_URL + "vote")
                    .param("restaurantId", RESTAURANT_ID_1.toString())
                    .with(userHttpBasic(USER_2)))
                    .andExpect(status().isNoContent())
                    .andDo(print());
        } else {
            Assertions.assertThrows(NestedServletException.class, () ->
                    mockMvc.perform(post(REST_URL + "vote")
                            .param("restaurantId", RESTAURANT_ID_1.toString())
                            .with(userHttpBasic(USER_2))));
        }
    }

    @Test
    public void vote() throws Exception {
        mockMvc.perform(post(REST_URL + "vote")
                .param("restaurantId", RESTAURANT_ID_2.toString())
                .with(userHttpBasic(USER_6)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(repository.getByUserIdAndRestaurantIdAndVoteDay(USER_ID_6, RESTAURANT_ID_2, LocalDate.now()), VOTE_NEW);
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_5)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RestaurantTo.class, RESTAURANT_TO_1_NOW));
    }

    @Test
    public void testGetAllByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "onDate")
                .param("day", DAY_MENU_DATE_STR)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RestaurantTo.class, RESTAURANT_TO_1, RESTAURANT_TO_2, RESTAURANT_TO_3));
    }
}
