DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menu;
DELETE FROM day_menu;
DELETE FROM votes;
DELETE FROM restaurants;

INSERT INTO users (id, name, email, password)
VALUES (5, 'User1', 'user1@yandex.ru', '{noop}password'),
       (6, 'User2', 'user2@yandex.ru', '{noop}password'),
       (7, 'User3', 'user3@yandex.ru', '{noop}password'),
       (8, 'User4', 'user4@yandex.ru', '{noop}password'),
       (9, 'User5', 'user5@yandex.ru', '{noop}password'),
       (10, 'User6', 'user6@yandex.ru', '{noop}password'),
       (11, 'User7', 'user7@yandex.ru', '{noop}password'),
       (12, 'Admin', 'admin@gmail.com', '{noop}admin'),
       (13, 'Admin2', 'admin2@gmail.com', '{noop}psw');

INSERT INTO user_roles (user_id, role)
VALUES (5, 'ROLE_USER'),
       (6, 'ROLE_USER'),
       (7, 'ROLE_USER'),
       (8, 'ROLE_USER'),
       (9, 'ROLE_USER'),
       (10, 'ROLE_USER'),
       (11, 'ROLE_USER'),
       (12, 'ROLE_ADMIN'),
       (13, 'ROLE_ADMIN');

INSERT INTO restaurants (id, name)
VALUES (20, 'Fish'),
       (21, 'Time'),
       (22, 'Trattoria'),
       (23, 'Valday');

INSERT INTO menu(restaurant_id, id, name, price)
VALUES  (20, 100, 'soup', 800),
        (20, 101, 'fish', 1500),
        (20, 102, 'steak', 2000),
        (20, 103, 'water', 100),
        (20, 104, 'potato', 800),
        (21, 105, 'salad', 600),
        (21, 106, 'soup', 1200),
        (21, 107, 'cheese', 1000),
        (21, 108, 'meat', 1200),
        (21, 109, 'tea', 1200),
        (21, 110, 'cake', 500),
        (22, 111, 'pizza', 900),
        (22, 112, 'fish soup', 1270),
        (22, 113, 'beans', 700),
        (22, 114, 'lobster', 8888),
        (22, 115, 'green salad', 700),
        (23, 116, 'steak', 1200),
        (23, 117, 'garlic carrots', 1000),
        (23, 118, 'potato', 1250),
        (23, 119, 'burger', 1250);

INSERT INTO day_menu (id, restaurant_id, menu_day, meal_id, price)
VALUES (1008, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 100, 1000),
       (1009, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 101, 1500),
       (1010, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 102, 1650),
       (1011, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 103, 100),
       (1012, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 104, 775),
       (1013, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 105, 1200),
       (1014, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 106, 900),
       (1015, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 107, 1500),
       (1016, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 108, 1700),
       (1017, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 109, 200),
       (1018, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 110, 1000),
       (1019, 22, parsedatetime('04-03-2019', 'dd-MM-yyyy'), 111, 500),
       (1020, 22, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 112, 1250),
       (1021, 22, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 113, 1250),
       (1022, 22, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 114, 9999),
       (1023, 22, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 115, 1250),
       (1024, 23, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 116, 1250),
       (1025, 23, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 117, 1050),
       (1026, 23, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 118, 1250),
       (1027, 23, parsedatetime('03-03-2019', 'dd-MM-yyyy'), 119, 1250),
       (1028, 20, now(), 100, 1000),
       (1029, 20, now(), 101, 1500);

INSERT INTO VOTES (user_id, restaurant_id, vote_day)
VALUES (5, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy')),
        (6, 22, parsedatetime('04-03-2019', 'dd-MM-yyyy')),
         (7, 20, parsedatetime('04-03-2019', 'dd-MM-yyyy')),
          (9, 21, parsedatetime('04-03-2019', 'dd-MM-yyyy'));

