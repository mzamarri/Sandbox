CREATE TABLE IF NOT EXISTS t1(id SERIAL UNIQUE);

CREATE TABLE IF NOT EXISTS t2(id INT GENERATED ALWAYS AS IDENTITY);