CREATE TABLE IF NOT EXISTS users
(
    user_id SERIAL NOT NULL PRIMARY KEY ,
    username VARCHAR(256) NOT NULL ,
    password VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id INT NOT NULL PRIMARY KEY ,
    role_name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS products
(
    product_id SERIAL NOT NULL PRIMARY KEY ,
    category_id INT NOT NULL,
    brand_name VARCHAR(256) NOT NULL ,
    full_name VARCHAR(256) NOT NULL ,
    product_name VARCHAR(256) NOT NULL ,
    quantity integer NOT NULL,
    price real NOT NULL,
    amount integer NOT NULL
);

CREATE TABLE IF NOT EXISTS categories
(
    category_id SERIAL NOT NULL PRIMARY KEY ,
    category_name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id),

    UNIQUE (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS user_products
(
    user_id INT NOT NULL,
    product_id INT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id),

    UNIQUE (user_id, product_id)
);



INSERT INTO roles (role_id, role_name)
SELECT 1, 'ROLE_USER'
    WHERE NOT EXISTS (
        SELECT role_id FROM roles WHERE role_id = 1
    );

INSERT INTO roles (role_id, role_name)
SELECT 2, 'ROLE_ADMIN'
    WHERE NOT EXISTS (
        SELECT role_id FROM roles WHERE role_id = 2
    );


