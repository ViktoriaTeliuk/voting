package restaurant.voting.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;
import restaurant.voting.model.Role;
import restaurant.voting.model.User;
import restaurant.voting.service.UserService;
import restaurant.voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.voting.TestData.*;
import static restaurant.voting.util.TestUtil.*;

public class AdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminController.REST_URL + '/';

    @Autowired
    UserService service;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER_ID_1)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, User.class), USER_1, "password", "registered"));
    }

    @Test
    void testGetNotFound() throws Exception {
        assertThrows(NestedServletException.class, () -> mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print()));
    }

    @Test
    void testGetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + ADMIN_1.getEmail())
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, User.class), ADMIN_1, "password", "registered"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID_1)
                .with(userHttpBasic(ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), List.of(ADMIN_1, ADMIN_2, USER_2, USER_3, USER_4, USER_5, USER_6, USER_7));
    }

    @Test
    void testDeleteNotFound() throws Exception {
        assertThrows(NestedServletException.class, () -> mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print()));
    }

    @Test
    void testGetUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdate() throws Exception {
        User updated = new User(USER_1);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        mockMvc.perform(put(REST_URL + USER_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(USER_ID_1), updated, "password", "registered");
    }

    @Test
    void testCreate() throws Exception {
        User expected = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN_1))
                .content(jsonWithPassword(expected, "newPass")))
                .andExpect(status().isCreated());

        User returned = readFromJson(action, User.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected, "password", "registered");
        assertMatch(service.getAll(), ADMIN_1, ADMIN_2, expected, USER_1, USER_2, USER_3, USER_4, USER_5, USER_6, USER_7);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN_1)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(User.class, ADMIN_1, ADMIN_2, USER_1, USER_2, USER_3, USER_4, USER_5, USER_6, USER_7));
    }
}
