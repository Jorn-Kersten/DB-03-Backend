INSERT INTO users (name, email)
VALUES ('admin', 'admin@gmail.com');

INSERT INTO product (name, supermarket, url, date, price)
VALUES ('Coca-Cola', 'Albert Heijn', 'https://www.ah.nl/producten/product/wi2800/coca-cola-regular', '2022-10-12', 2.49);
INSERT INTO product (name, supermarket, url, date, price)
VALUES ('Fanta', 'Albert Heijn', 'https://www.ah.nl/producten/product/wi2803/fanta-orange', '2022-10-12', 2.25);
INSERT INTO product (name, supermarket, url, date, price)
VALUES ('Coca-Cola', 'Jumbo', 'https://www.jumbo.com/producten/coca-cola-original-taste-1,5l-428483FLS', '2022-10-12', 2.42);
INSERT INTO product (name, supermarket, url, date, price)
VALUES ('Fanta', 'Jumbo', 'https://www.jumbo.com/producten/fanta-orange-pet-fles-1,5l-428478FLS', '2022-10-12', 2.09);

INSERT INTO shoppinglistproduct (shoppingListId, userId, quantity, content, name, supermarket, url, date, price)
VALUES (1, 1, 1, 1.5, 'Coca-Cola', 'Jumbo', 'https://www.jumbo.com/producten/coca-cola-original-taste-1,5l-428483FLS', '2022-10-12', 2.42);
INSERT INTO shoppinglistproduct (shoppingListId, userId, quantity, content, name, supermarket, url, date, price)
VALUES (1, 1, 1, 1.5, 'Fanta', 'Jumbo', 'https://www.jumbo.com/producten/fanta-orange-pet-fles-1,5l-428478FLS', '2022-10-12', 2.09);