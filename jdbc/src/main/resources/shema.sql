-- Create schema and table
CREATE SCHEMA IF NOT EXISTS products;

CREATE TABLE IF NOT EXISTS products.product (
id SERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
price DECIMAL NOT NULL
);

