--liquibase formatted sql
--changeset zero_waste:1

CREATE TABLE Tickets (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    type VARCHAR(15) NOT NULL,
    title VARCHAR(50) NOT NULL,
    comment VARCHAR(100) NOT NULL
);

CREATE TABLE Orders (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    address_id BIGINT NOT NULL,
    package_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    order_date DATE NOT NULL
);

CREATE TABLE Notifications (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,
    content VARCHAR(100) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE Users (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    home_address BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50),
    email VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    account_status VARCHAR(50) NOT NULL
);

CREATE TABLE Addresses (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    city VARCHAR(50) NOT NULL,
    street_name VARCHAR(50) NOT NULL,
    building_number BIGINT NOT NULL,
    apartment_number BIGINT NOT NULL,
    postcode BIGINT NOT NULL
);

CREATE TABLE Seller_Reviews (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    seller_id BIGINT NOT NULL,
    rating BIGINT NOT NULL,
    comment VARCHAR(100) NOT NULL
);

CREATE TABLE Product_Reviews (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    rating BIGINT NOT NULL,
    comment VARCHAR(100) NOT NULL
);

CREATE TABLE Sellers (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    address_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE Products (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price DOUBLE PRECISION
);

CREATE TABLE Product_Package (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    product_id BIGINT NOT NULL,
    package_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL
);

CREATE TABLE Packages (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    seller_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    name VARCHAR (50) NOT NULL,
    expiry_date DATE NOT NULL
);

CREATE TABLE User_Seller (
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    seller_id BIGINT NOT NULL
);

ALTER TABLE Tickets ADD CONSTRAINT TICKET_ORDER_FK FOREIGN KEY (order_id) REFERENCES Orders (id);

ALTER TABLE Tickets ADD CONSTRAINT TICKET_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE Orders ADD CONSTRAINT ORDER_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE Orders ADD CONSTRAINT ORDER_ADDRESS_FK FOREIGN KEY (address_id) REFERENCES Addresses (id);

ALTER TABLE Notifications ADD CONSTRAINT NOTIFICATION_ORDER_FK FOREIGN KEY (order_id) REFERENCES Orders (id);

ALTER TABLE Notifications ADD CONSTRAINT NOTIFICATION_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE Users ADD CONSTRAINT USER_ADDRESS_FK FOREIGN KEY (home_address) REFERENCES Addresses (id);

ALTER TABLE Sellers ADD CONSTRAINT SELLER_ADDRESS_FK FOREIGN KEY (address_id) REFERENCES Addresses (id);

ALTER TABLE Packages ADD CONSTRAINT PACKAGE_SELLER_FK FOREIGN KEY (seller_id) REFERENCES Sellers (id);

ALTER TABLE Packages ADD CONSTRAINT PACKAGE_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE Packages ADD CONSTRAINT PACKAGE_PRODUCT_FK FOREIGN KEY (product_id) REFERENCES Products (id);

ALTER TABLE Orders ADD CONSTRAINT ORDER_PACKAGE_FK FOREIGN KEY (package_id) REFERENCES Packages (id);

ALTER TABLE Product_Package ADD CONSTRAINT PP_PRODUCT_FK FOREIGN KEY (product_id) REFERENCES Products (id);

ALTER TABLE Product_Package ADD CONSTRAINT PP_PACKAGE_FK FOREIGN KEY (package_id) REFERENCES Packages (id);

ALTER TABLE Seller_Reviews ADD CONSTRAINT SREVIEW_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE Seller_Reviews ADD CONSTRAINT SREVIEW_SELLER_FK FOREIGN KEY (seller_id) REFERENCES Sellers (id);

ALTER TABLE Product_Reviews ADD CONSTRAINT PREVIEW_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE Product_Reviews ADD CONSTRAINT PREVIEW_PRODUCT_FK FOREIGN KEY (product_id) REFERENCES Products (id);

ALTER TABLE User_Seller ADD CONSTRAINT PREVIEW_US_USER FOREIGN KEY (user_id) REFERENCES Users (id);

ALTER TABLE User_Seller ADD CONSTRAINT PREVIEW_US_SELLER FOREIGN KEY (seller_id) REFERENCES Sellers (id);

ALTER TABLE Products ADD CONSTRAINT PRODUCT_USER_FK FOREIGN KEY (user_id) REFERENCES Users (id);

INSERT INTO Addresses (city, street_name, building_number, apartment_number, postcode)
VALUES ('Warsaw', 'Wiejska', 15, 2, '15302');

INSERT INTO Addresses (city, street_name, building_number, apartment_number, postcode)
VALUES ('Bialystok', 'Mazowiecka', 39, 3, '15302');

INSERT INTO Addresses (city, street_name, building_number, apartment_number, postcode)
VALUES ('Sokolow Podlaski', 'Wyspianskiego', 4, 2, '19203');

INSERT INTO Users (home_address, name, surname, email, role, login, password, account_status)
VALUES (2, 'Bartosz', 'Wisniewski', 'bartekx3000@gmail.com', 'ROLE_ADMIN', 'admin', '$2a$12$D97BW/WudLfGsz9KDjL.R.QJAiXQAwcJnGkoYCUq4x8mIvXibTO7m', 'ACTIVE');

INSERT INTO Users (home_address, name, surname, email, role, login, password, account_status)
VALUES (1, 'Rafal', 'Mickiewicz', 'mickiewicz467@gmail.com', 'ROLE_USER', 'user', '$2a$12$6TgJBhHKwc1yAYJ.Yym9v.oeNmjCLC/CmGcOtRqSvfaehEDEiG66a', 'ACTIVE');

INSERT INTO Users (home_address, name, surname, email, role, login, password, account_status)
VALUES (3, 'Biedronka', null, 'biedronka@wp.pl', 'ROLE_SELLER', 'biedronka', '$2a$12$h0bpeH4Pk2EuHy4fs5xOtuRl5tSVMQhjYrZaoi9eLHr2vGJ7OL0eG', 'ACTIVE');

INSERT INTO Users (home_address, name, surname, email, role, login, password, account_status)
VALUES (1, 'Tesco', null, 'tesco@o2.pl', 'ROLE_SELLER', 'tesco', '$2a$12$vwgloGRAqEiE9HgFMXj3/OZONhMbmkqnNTbGPghELGR6Q9sxcbUFS', 'ACTIVE');

INSERT INTO Sellers (address_id, name, login, password)
VALUES (3, 'Biedronka', 'biedronka', '$2a$12$h0bpeH4Pk2EuHy4fs5xOtuRl5tSVMQhjYrZaoi9eLHr2vGJ7OL0eG');

INSERT INTO Sellers (address_id, name, login, password)
VALUES (1, 'Tesco', 'tesco', '$2a$12$vwgloGRAqEiE9HgFMXj3/OZONhMbmkqnNTbGPghELGR6Q9sxcbUFS');

INSERT INTO User_Seller (user_id, seller_id)
VALUES (3, 1);

INSERT INTO User_Seller (user_id, seller_id)
VALUES (4, 2);

INSERT INTO Products (user_id, name, description, price)
VALUES (3, 'Ham', 'Delicious meat', 29.99);

INSERT INTO Products (user_id, name, description, price)
VALUES (3, 'Yoghurt', 'Without lactose', 3.99);

INSERT INTO Products (user_id, name, description, price)
VALUES (3, 'Pizza', 'Fast meal for your dinner', 12.99);

INSERT INTO Products (user_id, name, description, price)
VALUES (4, 'Sugar', 'To make your life less bitter', 4.99);

INSERT INTO Products (user_id, name, description, price)
VALUES (4, 'Spaghetti', 'Twisted Meal', 29.99);

INSERT INTO Products (user_id, name, description, price)
VALUES (4, 'Tomatoes', 'Fresh tomatoes', 11.99);

INSERT INTO Packages (seller_id, user_id, product_id, name, expiry_date)
VALUES (1, 3, 1, 'Meat Package', '2024-01-10');

INSERT INTO Packages (seller_id, user_id, product_id, name, expiry_date)
VALUES (1, 3, 2, 'Dairy Drop', '2023-12-21');

INSERT INTO Packages (seller_id, user_id, product_id, name, expiry_date)
VALUES (2, 4, 4, 'Sweet Surprise', '2023-12-29');

INSERT INTO Packages (seller_id, user_id, product_id, name, expiry_date)
VALUES (2, 4, 5, 'Twisted Package', '2023-12-24');

INSERT INTO Product_Package (product_id, package_id, quantity)
VALUES (1, 1, 2);

INSERT INTO Product_Package (product_id, package_id, quantity)
VALUES (2, 2, 4);

INSERT INTO Product_Package (product_id, package_id, quantity)
VALUES (4, 3, 5);

INSERT INTO Product_Package (product_id, package_id, quantity)
VALUES (5, 4, 1);