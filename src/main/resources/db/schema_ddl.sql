CREATE TABLE IF NOT EXISTS client (
    cuil VARCHAR(20) PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL ,
    email VARCHAR(75),
    phone_number VARCHAR(50),
    address VARCHAR(255)
) ENGINE=MEMORY;