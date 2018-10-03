CREATE TABLE  users (
  id SERIAL PRIMARY KEY NOT NULL,
  email VARCHAR (30),
  password VARCHAR (50)
);

CREATE TABLE profile(
  userId  INT NOT NULL, /* Ez itt egy fk */
  username VARCHAR (20),
  gender TEXT,
  age INT,
  room VARCHAR (20),
  picture TEXT,
  favoriteLanguage VARCHAR (20),
  fromTO TEXT, /* TODO Erre itt nincs szukség*/
  bio TEXT
);

ALTER TABLE ONLY profile
  ADD CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES users(id);