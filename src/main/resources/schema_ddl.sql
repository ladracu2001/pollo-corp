CREATE TABLE client (
    cuil VARCHAR(20) PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50),
    lastName VARCHAR(50) NOT NULL,
    birthDate DATE NOT NULL ,
    email VARCHAR(75),
    phoneNumber VARCHAR(50),
    address VARCHAR(255)
) ENGINE=MEMORY;