package restaurant.voting.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import restaurant.voting.model.Menu;
import restaurant.voting.model.Restaurant;
import restaurant.voting.repository.MenuRepository;
import restaurant.voting.repository.RestaurantRepository;
import restaurant.voting.service.RestaurantService;
import restaurant.voting.to.MenuItemTo;
import restaurant.voting.to.NamedTo;
import restaurant.voting.to.RestaurantTo;
import restaurant.voting.util.RestaurantMapper;
import restaurant.voting.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.voting.TestData.*;
import static restaurant.voting.util.TestUtil.*;

public class AdminRestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantController.REST_URL + '/';

    @Autowired
    RestaurantService service;

    @Autowired
    RestaurantRepository repository;

    @Autowired
    RestaurantMapper mapper;

    @Autowired
    MenuRepository menuRepository;


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID_1)
                .param("day", DAY_MENU_DATE_STR)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, RestaurantTo.class), RESTAURANT_TO_1));
    }

    @Test
    public void testAdd() throws Exception {
        RestaurantTo expected = new RestaurantTo(null, "New restaurant", LocalDate.now(), MENU_TO_FOR_NEW_RESTAURANT);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());
        Restaurant saved = repository.get(returned.getId(), LocalDate.now());
        assertMatch(mapper.toRestaurantTo(saved), expected, "meals.id", "menu.id");
    }

    @Test
    public void testUpdate() throws Exception {
        RestaurantTo expected = new RestaurantTo(RESTAURANT_ID_1, "Edited restaurant", DAY_MENU_DATE,
                List.of(new MenuItemTo(null, "another new soup", 11.11), new MenuItemTo(null, "another new dish", 20.05),
                        MEAL_TO_2, MEAL_TO_3));
        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isNoContent());

        Restaurant restaurant = repository.get(expected.getId(), DAY_MENU_DATE);
        assertMatch(mapper.toRestaurantTo(restaurant), expected, "id", "menu.id");
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

    @Test
    public void testGetAllWithoutMenu() throws Exception {
        mockMvc.perform(get(REST_URL + "list")
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(NamedTo.class, RESTAURANT_1_NAMED_TO, RESTAURANT_2_NAMED_TO, RESTAURANT_3_NAMED_TO, RESTAURANT_4_NAMED_TO));
    }

    @Test
    public void testGetMenu() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID_1 + "/menu")
                .param("day", DAY_MENU_DATE_STR)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(Menu.class, MEAL_0, MEAL_1, MEAL_2, MEAL_3, MEAL_4));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_ID_1)
                .param("day", DAY_MENU_DATE_STR)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isNoContent());

        Assertions.assertNull(repository.get(RESTAURANT_ID_1, DAY_MENU_DATE));
    }

}
