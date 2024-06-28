CREATE TABLE IF NOT EXISTS catalog (
    id INT PRIMARY KEY,
    name TEXT,
    price float
);

DO $$
DECLARE
    n INT := 10;
BEGIN
    DELETE FROM catalog;
    FOR i IN 1..n LOOP
        INSERT INTO catalog VALUES (i, 'name' || i, i * 100.00);
    END LOOP;
END;
$$;

WITH cat AS (
    SELECT name, price FROM catalog
)
SELECT * FROM cat;