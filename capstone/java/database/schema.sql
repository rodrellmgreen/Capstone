BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	streak INTEGER,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE teams(
team_id SERIAL,
team_name varchar(100) NOT NULL,
CONSTRAINT PK_team_id PRIMARY KEY (team_id)
);

 CREATE TABLE users_teams(
 user_id INTEGER NOT NULL,
 team_id INTEGER NOT NULL,
 CONSTRAINT PK_users_teams PRIMARY KEY (user_id, team_id),
 CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
 CONSTRAINT FK_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id)
 );


CREATE TABLE profile (
   profile_id SERIAL,
   user_id INTEGER NOT NULL,
   email varchar(100) NOT NULL,
   age INTEGER NOT NULL,
   height_feet INTEGER NOT NULL,
   height_inches INTEGER NOT NULL,
   current_weight NUMERIC(5,2) NOT NULL,
   desired_weight NUMERIC(5,2) NOT NULL,
   profile_picture varchar(1000),
   display_name varchar(100),
   public_private BOOLEAN NOT NULL,
   phone_number varchar(15),
   CONSTRAINT PK_profile PRIMARY KEY (profile_id),
   CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
 );

 CREATE TABLE meal(
 meal_id SERIAL,
 user_id INTEGER NOT NULL,
 meal_type varchar(100),
 CONSTRAINT PK_meal PRIMARY KEY (meal_id),
 CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
 );

 CREATE TABLE food(
 food_id SERIAL,
 user_id INTEGER,
 date_entered DATE NOT NULL,
 food_name VARCHAR(100) NOT NULL,
 calories NUMERIC(10,2) NOT NULL,
 carbs NUMERIC(10,2) NOT NULL,
 protein NUMERIC(10,2) NOT NULL,
 fats NUMERIC(10,2) NOT NULL,
 fiber NUMERIC(10,2) NOT NULL,
 serving_size NUMERIC(10,2) NOT NULL,
 quick_add BOOLEAN NOT NULL,
 CONSTRAINT PK_food PRIMARY KEY (food_id),
 CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (user_id)
 );

 CREATE TABLE meal_food(
 meal_id INTEGER NOT NULL,
 food_name VARCHAR(100) NOT NULL,
 calories NUMERIC(10,2) NOT NULL,
 protein NUMERIC(10,2) NOT NULL,
 carbs NUMERIC(10,2) NOT NULL,
 fiber NUMERIC(10,2) NOT NULL,
 fats NUMERIC(10,2) NOT NULL,
 serving_size NUMERIC(10,2) NOT NULL,
 CONSTRAINT PK_meal_food PRIMARY KEY (meal_id, food_name),
 CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id)
 );

 CREATE TABLE community_posts(
 post_id SERIAL,
 team_id INTEGER NOT NULL,
 user_id INTEGER NOT NULL,
 post varchar(2000),
 CONSTRAINT PK_post_id PRIMARY KEY (post_id),
 CONSTRAINT FK_team_id FOREIGN KEY (team_id) REFERENCES teams(team_id),
 CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
 );

-- CREATE TABLE text_time(
-- text_id SERIAL,
-- user_id INTEGER NOT NULL,
-- time_stamp TIME NOT NULL,
-- CONSTRAINT PK_text_id PRIMARY KEY (text_id),
-- CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
-- );

COMMIT TRANSACTION;
