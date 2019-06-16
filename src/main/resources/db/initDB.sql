DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS day_menu;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS global_seq;

/*ALTER SEQUENCE global_seq RESTART WITH 100000;*/

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id         INTEGER   DEFAULT nextval('global_seq') PRIMARY KEY,
  name       VARCHAR                 NOT NULL,
  email      VARCHAR                 NOT NULL,
  password   VARCHAR                 NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL,
  enabled    BOOL      DEFAULT TRUE  NOT NULL,
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id   INTEGER NOT NULL DEFAULT nextval('global_seq') PRIMARY KEY,
  name VARCHAR NOT NULL
);

CREATE TABLE MENU
(
  restaurant_id INTEGER NOT NULL,
  id       INTEGER          DEFAULT nextval('global_seq') PRIMARY KEY,
  name     VARCHAR NOT NULL,
  price    BIGINT  NOT NULL,
  enabled        BOOLEAN NOT NULL DEFAULT true,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants on DELETE CASCADE
);
CREATE UNIQUE INDEX menu_restaurant_meal_name ON menu (restaurant_id, name);

CREATE TABLE day_menu
(
  id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
  restaurant_id INTEGER                 NOT NULL,
  menu_day      DATE    DEFAULT today() NOT NULL,
  meal_id       INTEGER                 NOT NULL,
  price    BIGINT                  NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants on DELETE CASCADE,
  FOREIGN KEY (meal_id) REFERENCES menu on DELETE CASCADE
);
CREATE UNIQUE INDEX day_menu_unique_restaurant_day_meal_idx ON day_menu (restaurant_id, menu_day, meal_id);

CREATE TABLE votes
(
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  vote_day      DATE    NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_day_user_restaurant_idx ON votes (vote_day, user_id, restaurant_id);