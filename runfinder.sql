DROP TABLE IF EXISTS run_group_sign_ups CASCADE;
DROP TABLE IF EXISTS app_users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS run_groups CASCADE;
DROP TABLE IF EXISTS zipcodes CASCADE;

CREATE TABLE zipcodes (
    zipcode VARCHAR NOT NULL PRIMARY KEY,
    city VARCHAR NOT NULL);

INSERT INTO zipcodes (zipcode, city) VALUES
    ('00100', 'Helsinki'),
    ('00200', 'Helsinki'),
    ('00300', 'Helsinki'),
    ('00400', 'Helsinki'),
    ('00500', 'Helsinki'),
    ('00600', 'Helsinki'),
    ('00700', 'Helsinki'),
    ('00800', 'Helsinki'),
    ('00900', 'Helsinki'),
    ('02100', 'Espoo'),
    ('02200', 'Espoo'),
    ('02300', 'Espoo'),
    ('02400', 'Kirkkonummi'),
    ('02600', 'Espoo'),
    ('03100', 'Nummela'),
    ('04400', 'Järvenpää'),
    ('04500', 'Kellokoski'),
    ('04600', 'Mäntsälä'),
    ('05800', 'Hyvinkää'),
    ('06100', 'Porvoo');

CREATE TABLE roles (
    role_id BIGSERIAL NOT NULL PRIMARY KEY,
    role_name VARCHAR NOT NULL);

INSERT INTO roles (role_name) VALUES
    ('USER'),
    ('CONTRIBUTOR'),
    ('ADMIN');

CREATE TABLE app_users (
    app_user_id BIGSERIAL NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    role_id BIGINT NOT NULL,
    password_hash VARCHAR NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(role_id));

INSERT INTO app_users (username, first_name, last_name, email, role_id, password_hash) VALUES
    ('user', 'userFirstname', 'userLastname', 'user@runfinder.com', 1,'$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy'),
    ('contributor', 'contributorFirstname', 'contributorLastName', 'contributor@runfinder.com', 2,'$2a$10$HFh46mkLcOGwS1GJZtPXqutsOrFlYvyODeQKMobBzjLuvGnTiOr3u'),
    ('admin', 'adminFirstname', 'adminLastname', 'admin@runfinder.com', 3,'$2a$10$/X5g8wxjMXw1pIDnJq7cL.WqJbg.LKQltNP8wXYpXjy/1Ha16lpKq');

CREATE TABLE run_groups(
    run_group_id BIGSERIAL NOT NULL PRIMARY KEY,
    run_group_name VARCHAR NOT NULL,
    run_start_date DATE NOT NULL,
    run_start_time TIME NOT NULL,
    start_address VARCHAR NOT NULL,
    zipcode VARCHAR NOT NULL,
    deleted_at TIMESTAMP,
    app_user_id BIGINT,  
    FOREIGN KEY (zipcode) REFERENCES zipcodes(zipcode),
    FOREIGN KEY (app_user_id) REFERENCES app_users(app_user_id));

INSERT INTO run_groups (run_group_name, run_start_date, run_start_time, start_address, zipcode, deleted_at, app_user_id) VALUES
    ('runGroup1', '2024-12-30', '12:00:00', 'Juoksukatu 1', '00100', null, 2),
    ('runGroup2', '2024-12-30', '12:00:00', 'Juoksutie 2', '02200', null, 2),
    ('runGroup3', '2024-12-30', '12:00:00', 'Juoksukuja 3', '02300', null, 2),
    ('runGroup4', '2024-12-30', '12:00:00', 'Juoksupolku 4', '04400', null, 2),
    ('runGroup5', '2022-12-30', '12:00:00', 'Juoksurinne 5', '05800', null, 2);

CREATE TABLE run_group_sign_ups(
    run_group_sign_up_id BIGSERIAL NOT NULL PRIMARY KEY,
    app_user BIGINT NOT NULL, 
    run_group BIGINT NOT NULL,
    FOREIGN KEY (app_user) REFERENCES app_users(app_user_id), 
    FOREIGN KEY (run_group) REFERENCES run_groups(run_group_id));

SELECT * FROM zipcodes;
SELECT * FROM roles;
SELECT * FROM app_users;
SELECT * FROM run_groups;
SELECT * FROM run_group_sign_ups;