CREATE TABLE IF NOT EXISTS account (
    account_id INT GENERATED ALWAYS AS IDENTITY,
    username TEXT,
    email TEXT,
    password TEXT
);

CREATE TABLE IF NOT EXISTS address (
    address_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    building_number INT NOT NULL,
    street TEXT NOT NULL,
    unit_no INT NOT NULL,
    city TEXT,
    state char(2),
    postal_code INT NOT NULL
);