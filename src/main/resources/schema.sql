CREATE TABLE coffee (
    name VARCHAR(128) NOT NULL,
    price DECIMAL NOT NULL,
    PRIMARY KEY (name)
);
CREATE TABLE coffee_order(
 name VARCHAR(128),
 quantity INTEGER,
 PRIMARY KEY (name)
 );