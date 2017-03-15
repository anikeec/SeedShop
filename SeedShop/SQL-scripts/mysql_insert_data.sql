use seedsdb;

INSERT INTO USER_GENDER(gender_id, name) VALUES (0, 'F');
INSERT INTO USER_GENDER(gender_id, name) VALUES (1, 'M');

INSERT INTO APPUSER(user_id, sec_name, first_name, third_name, email, phones, discount, birthday, gender_id, country, region, area, city) VALUES 
(0, '', '', '', '', '', '0', NULL, 1, '', '', '', '');
INSERT INTO APPUSER(user_id, sec_name, first_name, third_name, email, phones, discount, birthday, gender_id, country, region, area, city, sess_id) VALUES 
(1, 'Петров', 'Петр', 'Петрович', 'peter@bk.ru', '380502103706', '0.5', NULL, 1, 'Украина', 'Киевская', 'Броварской', 'Бровары', 12345678901234567890123456789013);
INSERT INTO APPUSER(user_id, sec_name, first_name, third_name, email, phones, discount, birthday, gender_id, country, region, area, city, sess_id) VALUES 
(2, 'Сидоров', 'Олег', 'Петрович', 'oleg@gmail.com', '3806741903706', '0', NULL, 1, 'Грузия', '', '', 'Тбилиси', 12345678901234567890123456789012);

INSERT INTO USER_AUTHORIZATION(user_id, login, passwd_hash) VALUES (1, 'Peter', 'asdf5gfG54fh');
INSERT INTO USER_AUTHORIZATION(user_id, login, passwd_hash) VALUES (2, 'Oleg', 'g3456f6364fh');

INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (1, 'Новая почта', 1);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (2, 'Деливери', 0);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (3, 'Интайм', 0);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (4, 'Укрпочта', 0);

INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (0, 'Не создан');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (1, 'Новый');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (2, 'Подтвержден');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (3, 'Отклонен');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (4, 'Передан перевозчику');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (5, 'Оплачен');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (6, 'Отправлен');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (7, 'Выполнен');

INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (1, 'Потребитель');
INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (2, 'Склад');
INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (3, 'Магазин 1');
INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (4, 'Магазин 2');

INSERT INTO INVOICE(user_id, order_id, order_date, paid_date, sent_date, discount, pay, status_id, sec_name, first_name, third_name, phone, add_info_u, source_id, destination_id, delivery_id, delivery_office, prepayment) VALUES 
(2, 1, '98-12-31', '00-00-00', '00-00-00', 5.0, 125.65, 0, 'Сидоров', 'Олег', 'Петрович', '3806741903706', 'Доставка до дома', 2, 1, 2, 12, 1);
INSERT INTO INVOICE(user_id, order_id, order_date, paid_date, sent_date, discount, pay, status_id, sec_name, first_name, third_name, phone, add_info_u, source_id, destination_id, delivery_id, delivery_office, prepayment) VALUES 
(1, 3, '98-10-20', '00-00-00', '00-00-00', 5.0, 135.80, 5, 'Сидоров', 'Олег', 'Петрович', '3806741903706', '', 1, 3, 2, 12, 1);
INSERT INTO INVOICE(user_id, order_id, order_date, paid_date, sent_date, discount, pay, status_id, sec_name, first_name, third_name, phone, add_info_u, source_id, destination_id, delivery_id, delivery_office, prepayment, add_info_m, backorder_id) VALUES 
(1, 2, '85-11-14', '00-00-00', '00-00-00', 3.0, 220.50, 2, 'Петров', 'Петр', 'Петрович', '380502103706', 'Доставка до склада', 3, 1, 3, 8, 0, 'Возврат от пользователя', 3);


INSERT INTO MANUFACTURE(manufact_id, name, address) VALUES (1, 'Примула', 'Чернигов, пр.Победы, 50');
INSERT INTO MANUFACTURE(manufact_id, name, address) VALUES (2, 'Семена Украины', 'Киев, Броварской проспект, 35');

INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (1, NULL, 'овощи');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (2, 1, 'огурец');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (3, 2, 'зозуля');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (4, 1, 'помидор');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (5, 4, 'сливка');

INSERT INTO PACK(pack_id, name) VALUES (1, 'Маленькая');
INSERT INTO PACK(pack_id, name) VALUES (2, 'Средняя');
INSERT INTO PACK(pack_id, name) VALUES (3, 'Большая');

INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (1, 100, NULL, 1);
INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (2, 200, NULL, 2);
INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (3, 500, NULL, 3);
INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (4, NULL, 50, 1);

INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000001, 1, 3, 1, 2.50);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000002, 1, 3, 2, 5.00);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000003, 1, 3, 3, 6.80);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000004, 2, 5, 4, 4.55);

INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (1, 1, 0000000000001, 2.25, 5);
INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (2, 1, 0000000000002, 4.75, 8);
INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (3, 1, 0000000000003, 6.50, 4);
INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (4, 2, 0000000000002, 2.25, 3);

INSERT INTO PROVIDER(provider_id, name, address) VALUES (1, 'Местные', 'Чернигов, пр.Победы, 50');
INSERT INTO PROVIDER(provider_id, name, address) VALUES (2, 'Сортсемовощ', 'Киев, пр.Мира, 25');
INSERT INTO PROVIDER(provider_id, name, address) VALUES (3, 'Элитнасиння', 'Одесса, ул.Дерибасовская, 10');

INSERT INTO PRODUCT_PROVIDER(id, product_id, provider_id) VALUES (1, 3, 1);
INSERT INTO PRODUCT_PROVIDER(id, product_id, provider_id) VALUES (2, 3, 2);
INSERT INTO PRODUCT_PROVIDER(id, product_id, provider_id) VALUES (3, 3, 3);
INSERT INTO PRODUCT_PROVIDER(id, product_id, provider_id) VALUES (4, 5, 1);

INSERT INTO AVAILABILITY(id, location_id, barcode, amount) VALUES (1, 2, 0000000000001, 500);
INSERT INTO AVAILABILITY(id, location_id, barcode, amount) VALUES (2, 3, 0000000000001, 50);
INSERT INTO AVAILABILITY(id, location_id, barcode, amount) VALUES (3, 4, 0000000000001, 37);
INSERT INTO AVAILABILITY(id, location_id, barcode, amount) VALUES (4, 2, 0000000000002, 450);
INSERT INTO AVAILABILITY(id, location_id, barcode, amount) VALUES (5, 3, 0000000000002, 75);
INSERT INTO AVAILABILITY(id, location_id, barcode, amount) VALUES (6, 4, 0000000000002, 28);



