CREATE TABLE  users (
  id SERIAL PRIMARY KEY NOT NULL,
  email VARCHAR (30),
  password VARCHAR (50)
);

CREATE TABLE profile(
  userId  INT NOT NULL, /* Ez itt egy fk */
  username VARCHAR (20),
  gender VARCHAR(30),
  age INT,
  room VARCHAR (20),
  picture TEXT,
  favoriteLanguage VARCHAR (20),
  bio VARCHAR(50)
);

ALTER TABLE ONLY profile
  ADD CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES users(id);