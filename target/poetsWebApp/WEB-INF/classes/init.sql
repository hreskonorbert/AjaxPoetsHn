/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS poets;

CREATE TABLE poets (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
	CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT password_not_empty CHECK (password <> '')
);

CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    poet_id INTEGER NOT NULL,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    written TEXT NOT NULL,
    FOREIGN KEY (poet_id) REFERENCES poets(id)
);

INSERT INTO poets (name, password) VALUES
	('Agatha Christie', 'AgChIe123'),
	('Edgar Allan Poe', 'EdAlPo321');


INSERT INTO works(poet_id,title,content,written) VALUES
  (1,'Ten Little Negros','Some dummy text','1945 August 17'),
  (1,'Murder On The Orient Express','This is a really good book','1949 June 8'),
  (1,'The ABC Murders','A good read','1935 April 25'),
  (2,'The Raven','dummy text raven','1845 April 5'),
  (2,'Annabel Lee','dummy text annabelle','1849 October 9'),
  (2,'The Black Cat','dummy text black cat','1843 August 19'),
  (2,'The Gold-Bug','dummy text gold bug','1843 June 22');




