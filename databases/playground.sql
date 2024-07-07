CREATE TABLE IF NOT EXISTS catalog (
    catalog_id SERIAL PRIMARY KEY,
    name varchar(255),
    price float
);

CREATE TABLE IF NOT EXISTS orders (
    order_id SERIAL PRIMARY KEY,
    total_price float,
    address varchar(255)
);

CREATE TABLE IF NOT EXISTS order_items (
    order_id int,
    catalog_id int,
    quantity int,
    PRIMARY KEY (order_id, catalog_id),
    FOREIGN KEY (order_id) REFERENCES orders,
    FOREIGN KEY (catalog_id) REFERENCES catalog
);

CREATE OR REPLACE PROCEDURE add_to_catalog(name varchar(255), price float, total float, address varchar(255)) AS $$
    INSERT INTO catalog ("name", "price") VALUES (name, price);
    INSERT INTO orders (total_price, address) VALUES (total, address) RETURNING total_price;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION create_table_and_return() RETURNS TABLE (name varchar(255), price float) AS $$
BEGIN
    RETURN QUERY SELECT c.name, c.price FROM catalog c;
END $$ LANGUAGE plpgsql;