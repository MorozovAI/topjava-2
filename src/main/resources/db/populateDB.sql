DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals(date_time, description, calories, user_id)
VALUES ('2022-06-13 10:00:00', 'завтрак', '605', 100000),
       ('2022-06-13 16:00:00', 'обед', '700', 100000),
       ('2022-06-13 20:00:00', 'ужин', '700', 100000),
       ('2022-06-14 10:00:00', 'завтрак', '500', 100000),
       ('2022-06-14 16:00:00', 'обед', '700', 100000),
       ('2022-06-14 20:00:00', 'ужин', '700', 100000),
       ('2022-06-21 00:00:00', 'граничное значение', '605', 100000),
       ('2022-06-14 16:00:00', 'обед админа', '500', 100001),
       ('2022-06-14 20:00:00', 'admin`s supper', '500', 100001)

