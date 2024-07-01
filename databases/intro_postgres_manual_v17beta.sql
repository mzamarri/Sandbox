CALL clear_tables();

CREATE TABLE IF NOT EXISTS weather (
    city varchar(80),
    temp_lo int,
    temp_hi int,
    prcp real,
    date date
);

CREATE TABLE IF NOT EXISTS cities (
    name varchar(80),
    location point
);

INSERT INTO weather VALUES ('San Francisco', 71, 83, 0.24, '2003-12-21');
INSERT INTO weather (city, temp_hi, temp_lo, date) VALUES ('San Diego', 80, 69, '2021-1-14'); 
INSERT INTO weather (city, temp_hi, temp_lo, date, prcp) VALUES ('New York', 70, 59, '2011-11-24', 0.6);
INSERT INTO weather VALUES ('San Francisco', 70, 80, 0.22, '2013-2-12');
INSERT INTO weather VALUES ('Mars', -50, -74, 0.00, '2022-4-07');

INSERT INTO cities VALUES ('San Francisco', '(-194.0, 55.4)');
INSERT INTO cities (name) VALUES ('Moon');
INSERT INTO cities VALUES ('San Diego', '(104.6, -69.0)');
INSERT INTO cities VALUES ('New York', '(4024.6, -420.69)');

-- How to Query

SELECT * FROM weather;
SELECT * FROM weather ORDER BY city;
SELECT * FROM weather ORDER BY temp_lo;
SELECT DISTINCT city FROM weather; 
SELECT DISTINCT city FROM weather ORDER BY city;
SELECT city, (temp_hi + temp_lo)/2 AS temp_avg, date FROM weather;
SELECT * FROM weather WHERE city = 'San Francisco' AND 0.23 > prcp;

SELECT * FROM cities;

-- Joining tables

SELECT * FROM weather JOIN cities ON city = name;
SELECT city, temp_hi, temp_lo, prcp, date, location FROM weather JOIN cities ON name=city; -- can fail if duplicate columns are added
SELECT weather.city, weather.temp_hi, weather.temp_lo, weather.prcp, weather.date, cities.location FROM weather
    JOIN cities ON weather.city = cities.name; -- qualifies columns name (weahter.city, ...) to prevents errors from column duplication
SELECT * FROM weather, cities WHERE name=city;
SELECT * FROM weather LEFT OUTER JOIN cities ON weather.city = cities.name;
SELECT * FROM weather RIGHT OUTER JOIN cities ON weather.city = cities.name;
SELECT * FROM weather FULL OUTER JOIN cities ON weather.city = cities.name;
SELECT w1.city, w1.temp_hi, w1.temp_lo, w2.city, w2.temp_hi, w2.temp_lo FROM weather w1 JOIN weather w2 
    ON w1.temp_lo < w2.temp_lo;

-- Aggregate functions
SELECT max(temp_lo) FROM weather;
SELECT * FROM weather WHERE temp_lo = (SELECT max(temp_lo) FROM weather); -- 'SELECT max(temp_lo) FROM weather' is a subquery!
SELECT city, count(*), max(temp_lo) FROM weather GROUP BY city;
SELECT city, count(*), max(temp_lo) FROM weather GROUP BY city HAVING max(temp_lo) < 60;
SELECT city, count(*), max(temp_lo) FROM weather GROUP BY city HAVING city LIKE 'S%'; -- LIKE operator does pattern matching
SELECT city, count(*) FILTER (WHERE temp_lo < 70), max(temp_lo) FROM weather GROUP BY city;

-- Update command

UPDATE weather SET temp_hi = temp_hi-2, temp_lo = temp_lo - 2
    WHERE date > '2011-12-1';
SELECT * FROM weather;