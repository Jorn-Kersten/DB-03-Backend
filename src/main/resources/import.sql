INSERT INTO users (name, email)
VALUES ('admin', 'admin@gmail.com');

INSERT INTO shoppinglist (date, name, supermarket, totalprice)
VALUES ('2022-10-12', 'test list', 'Albert Heijn', 0.00);

INSERT INTO product (name, supermarket, url, date, price, content)
VALUES ('Coca-Cola', 'Albert Heijn', 'https://www.ah.nl/producten/product/wi2800/coca-cola-regular', '2022-10-12', 2.49, 0.5);
INSERT INTO product (name, supermarket, url, date, price, content)
VALUES ('Fanta', 'Albert Heijn', 'https://www.ah.nl/producten/product/wi2803/fanta-orange', '2022-10-12', 2.25, 0.5);
INSERT INTO product (name, supermarket, url, date, price, content)
VALUES ('Coca-Cola', 'Jumbo', 'https://www.jumbo.com/producten/coca-cola-original-taste-1,5l-428483FLS', '2022-10-12', 2.42, 0.5);
INSERT INTO product (name, supermarket, url, date, price, content)
VALUES ('Fanta', 'Jumbo', 'https://www.jumbo.com/producten/fanta-orange-pet-fles-1,5l-428478FLS', '2022-10-12', 2.09, 0.5);

INSERT INTO shoppinglistproduct (shoppingListId, quantity, content, userName, name, supermarket, url, date, price)
VALUES (1, 1, 1.5, 'admin','Coca-Cola', 'Jumbo', 'https://www.jumbo.com/producten/coca-cola-original-taste-1,5l-428483FLS', '2022-10-12', 2.42);
INSERT INTO shoppinglistproduct (shoppingListId, quantity, content, userName, name, supermarket, url, date, price)
VALUES (1, 1, 1.5, 'admin', 'Fanta', 'Jumbo', 'https://www.jumbo.com/producten/fanta-orange-pet-fles-1,5l-428478FLS', '2022-10-12', 2.09);