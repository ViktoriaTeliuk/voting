package restaurant.voting;


import org.springframework.test.web.servlet.ResultMatcher;
import restaurant.voting.model.*;
import restaurant.voting.to.MenuItemTo;
import restaurant.voting.to.NamedTo;
import restaurant.voting.to.RestaurantTo;
import restaurant.voting.web.json.JsonUtil;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static restaurant.voting.util.RestaurantMapper.CENT_FACTOR;
import static restaurant.voting.util.TestUtil.readListFromJsonMvcResult;

public class TestData {

    public static Integer RESTAURANT_ID_1 = 20;
    public static Integer RESTAURANT_ID_2 = 21;
    private static Integer RESTAURANT_ID_3 = 22;
    private static Integer RESTAURANT_ID_4 = 23;

    private static String RESTAURANT_NAME_1 = "Fish";
    private static String RESTAURANT_NAME_2 = "Time";
    private static String RESTAURANT_NAME_3 = "Trattoria";
    private static String RESTAURANT_NAME_4 = "Valday";

    public static LocalDate DAY_MENU_DATE = LocalDate.of(2019, 3, 4);
    public static String DAY_MENU_DATE_STR = "2019-03-04";

    private static Integer MEAL_ID_0 = 100;
    private static Integer MEAL_ID_1 = 101;
    private static Integer MEAL_ID_2 = 102;
    private static Integer MEAL_ID_3 = 103;
    private static Integer MEAL_ID_4 = 104;
    private static Integer MEAL_ID_5 = 105;
    private static Integer MEAL_ID_6 = 106;
    private static Integer MEAL_ID_7 = 107;
    private static Integer MEAL_ID_8 = 108;
    private static Integer MEAL_ID_9 = 109;
    private static Integer MEAL_ID_10 = 110;
    private static Integer MEAL_ID_11 = 111;
    private static Integer MEAL_ID_12 = 112;
    private static Integer MEAL_ID_13 = 113;
    private static Integer MEAL_ID_14 = 114;
    private static Integer MEAL_ID_15 = 115;

    private static Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, RESTAURANT_NAME_1);

    private static String MEAL_NAME_1 = "soup";
    private static String MEAL_NAME_2 = "fish";
    private static String MEAL_NAME_3 = "steak";
    private static String MEAL_NAME_4 = "water";
    private static String MEAL_NAME_5 = "potato";
    private static String MEAL_NAME_6 = "salad";
    private static String MEAL_NAME_7 = "soup";
    private static String MEAL_NAME_8 = "cheese";
    private static String MEAL_NAME_9 = "meat";
    private static String MEAL_NAME_10 = "tea";
    private static String MEAL_NAME_11 = "cake";
    private static String MEAL_NAME_12 = "pizza";
    private static String MEAL_NAME_13 = "fish soup";
    private static String MEAL_NAME_14 = "beans";
    private static String MEAL_NAME_15 = "lobster";
    private static String MEAL_NAME_16 = "green salad";

    private static BigInteger MEAL_PRICE_1 = BigInteger.valueOf(800);
    private static BigInteger MEAL_PRICE_2 = BigInteger.valueOf(1500);
    private static BigInteger MEAL_PRICE_3 = BigInteger.valueOf(2000);
    private static BigInteger MEAL_PRICE_4 = BigInteger.valueOf(100);
    private static BigInteger MEAL_PRICE_5 = BigInteger.valueOf(800);

    public static Menu MEAL_0 = new Menu(RESTAURANT_1, MEAL_ID_0, MEAL_NAME_1, MEAL_PRICE_1, true);
    public static Menu MEAL_1 = new Menu(RESTAURANT_1, 101, MEAL_NAME_2, MEAL_PRICE_2, true);
    public static Menu MEAL_2 = new Menu(RESTAURANT_1, 102, MEAL_NAME_3, MEAL_PRICE_3, true);
    public static Menu MEAL_3 = new Menu(RESTAURANT_1, 103, MEAL_NAME_4, MEAL_PRICE_4, true);
    public static Menu MEAL_4 = new Menu(RESTAURANT_1, 104, MEAL_NAME_5, MEAL_PRICE_5, true);
    public static List<MenuItemTo> MENU_TO_FOR_NEW_RESTAURANT = List.of(new MenuItemTo(null, "new soup", 11.11), new MenuItemTo(null, "new dish", 20.05));

    private static MenuItemTo MEAL_TO_1 = new MenuItemTo(MEAL_ID_0, MEAL_NAME_1, (double) 1000 / CENT_FACTOR);
    public static MenuItemTo MEAL_TO_2 = new MenuItemTo(MEAL_ID_1, MEAL_NAME_2, (double) 1500 / CENT_FACTOR);
    public static MenuItemTo MEAL_TO_3 = new MenuItemTo(MEAL_ID_2, MEAL_NAME_3, (double) 1650 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_4 = new MenuItemTo(MEAL_ID_3, MEAL_NAME_4, (double) 100 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_5 = new MenuItemTo(MEAL_ID_4, MEAL_NAME_5, (double) 775 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_6 = new MenuItemTo(MEAL_ID_5, MEAL_NAME_6, (double) 1200 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_7 = new MenuItemTo(MEAL_ID_6, MEAL_NAME_7, (double) 900 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_8 = new MenuItemTo(MEAL_ID_7, MEAL_NAME_8, (double) 1500 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_9 = new MenuItemTo(MEAL_ID_8, MEAL_NAME_9, (double) 1700 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_10 = new MenuItemTo(MEAL_ID_9, MEAL_NAME_10, (double) 200 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_11 = new MenuItemTo(MEAL_ID_10, MEAL_NAME_11, (double) 1000 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_12 = new MenuItemTo(MEAL_ID_11, MEAL_NAME_12, (double) 500 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_13 = new MenuItemTo(MEAL_ID_12, MEAL_NAME_13, (double) 1250 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_14 = new MenuItemTo(MEAL_ID_13, MEAL_NAME_14, (double) 1250 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_15 = new MenuItemTo(MEAL_ID_14, MEAL_NAME_15, (double) 9999 / CENT_FACTOR);
    private static MenuItemTo MEAL_TO_16 = new MenuItemTo(MEAL_ID_15, MEAL_NAME_16, (double) 1250 / CENT_FACTOR);

    public static RestaurantTo RESTAURANT_TO_1 = new RestaurantTo(RESTAURANT_ID_1, RESTAURANT_NAME_1, DAY_MENU_DATE,
            List.of(MEAL_TO_1, MEAL_TO_2, MEAL_TO_3, MEAL_TO_4, MEAL_TO_5));
    public static RestaurantTo RESTAURANT_TO_1_NOW = new RestaurantTo(RESTAURANT_ID_1, RESTAURANT_NAME_1, LocalDate.now(),
            List.of(MEAL_TO_1, MEAL_TO_2));
    public static RestaurantTo RESTAURANT_TO_2 = new RestaurantTo(RESTAURANT_ID_2, RESTAURANT_NAME_2, DAY_MENU_DATE,
            List.of(MEAL_TO_6, MEAL_TO_7, MEAL_TO_8, MEAL_TO_9, MEAL_TO_10, MEAL_TO_11));
    public static RestaurantTo RESTAURANT_TO_3 = new RestaurantTo(RESTAURANT_ID_3, RESTAURANT_NAME_3, DAY_MENU_DATE,
            List.of(MEAL_TO_12));
    public static RestaurantTo RESTAURANT_TO_4 = new RestaurantTo(RESTAURANT_ID_4, RESTAURANT_NAME_4, DAY_MENU_DATE,
            List.of());

    public static NamedTo RESTAURANT_1_NAMED_TO = new NamedTo(RESTAURANT_ID_1, RESTAURANT_NAME_1);
    public static NamedTo RESTAURANT_2_NAMED_TO = new NamedTo(RESTAURANT_ID_2, RESTAURANT_NAME_2);
    public static NamedTo RESTAURANT_3_NAMED_TO = new NamedTo(RESTAURANT_ID_3, RESTAURANT_NAME_3);
    public static NamedTo RESTAURANT_4_NAMED_TO = new NamedTo(RESTAURANT_ID_4, RESTAURANT_NAME_4);

    private static Integer USER_ADMIN_ID_1 = 12;
    private static Integer USER_ADMIN_ID_2 = 13;
    public static Integer USER_ID_1 = 5;
    private static Integer USER_ID_2 = 6;
    private static Integer USER_ID_3 = 7;
    private static Integer USER_ID_4 = 8;
    private static Integer USER_ID_5 = 9;
    public static Integer USER_ID_6 = 10;
    private static Integer USER_ID_7 = 11;

    public static User ADMIN_1 = new User(USER_ADMIN_ID_1, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static User ADMIN_2 = new User(USER_ADMIN_ID_2, "Admin2", "admin2@gmail.com", "psw", Role.ROLE_ADMIN);
    public static User USER_1 = new User(USER_ID_1, "User1", "user1@yandex.ru", "password", Role.ROLE_USER);
    public static User USER_2 = new User(USER_ID_2, "User2", "user2@yandex.ru", "password", Role.ROLE_USER);
    public static User USER_3 = new User(USER_ID_3, "User3", "user3@yandex.ru", "password", Role.ROLE_USER);
    public static User USER_4 = new User(USER_ID_4, "User4", "user4@yandex.ru", "password", Role.ROLE_USER);
    public static User USER_5 = new User(USER_ID_5, "User5", "user5@yandex.ru", "password", Role.ROLE_USER);
    public static User USER_6 = new User(USER_ID_6, "User6", "user6@yandex.ru", "password", Role.ROLE_USER);
    public static User USER_7 = new User(USER_ID_7, "User7", "user7@yandex.ru", "password", Role.ROLE_USER);
    public static Vote VOTE_NEW = new Vote(new VotesKey(USER_ID_6, RESTAURANT_ID_2, LocalDate.now()));

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static <T> void assertMatch(T actual, T expected, String... ignore) {
        assertThat(actual).usingComparatorForFields((x, y) -> 0, ignore).isEqualToComparingFieldByFieldRecursively(expected);
    }

    public static <T> ResultMatcher contentJson(Class clazz, T... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, clazz), List.of(expected));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

}