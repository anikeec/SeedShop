use seedsdb;

INSERT INTO USER_GENDER(gender_id, name) VALUES (0, 'U');
INSERT INTO USER_GENDER(gender_id, name) VALUES (1, 'F');
INSERT INTO USER_GENDER(gender_id, name) VALUES (2, 'M');

INSERT INTO APPUSER(user_id, sec_name, first_name, third_name, email, phones, discount,  gender_id, country, region, area, city, temp, used) VALUES 
(3, '', '', '', '', '', '0', 1, '', '', '', '', false, true);
INSERT INTO APPUSER(user_id, sec_name, first_name, third_name, email, phones, discount,  gender_id, country, region, area, city, login, passwd_hash, role, sess_id, temp, used) VALUES 
(1, '���஢', '����', '���஢��', 'peter@bk.ru', '380502103706', '0.5', 2, '��ࠨ��', '����᪠�', '�஢��᪮�', '�஢���', 'librarian1', '65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5', 'ROLE_ADMIN', 12345678901234567890123456789012, true, false);
INSERT INTO APPUSER(user_id, sec_name, first_name, third_name, email, phones, discount,  gender_id, country, region, area, city, login, passwd_hash, sess_id) VALUES 
(2, '����஢', '����', '���஢��', 'oleg@gmail.com', '3806741903706', '0', 2, '��㧨�', '', '', '������', 'Oleg', 'g3456f6364fh', 12345678901234567890123456789015);

INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (0, '�����뢮�', 1);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (1, '����� ����', 1);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (2, '�������', 0);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (3, '��⠩�', 0);
INSERT INTO DELIVERY_SERVICE(delivery_id, name, collect_avail) VALUES (4, '��௮��', 0);

INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (0, '�� ᮧ���');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (1, '����');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (2, '���⢥ত��');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (3, '�⪫����');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (4, '��।�� ��ॢ��稪�');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (5, '����祭');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (6, '��ࠢ���');
INSERT INTO DELIVERY_STATUS(status_id, status) VALUES (7, '�믮����');

INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (1, '���ॡ�⥫�');
INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (2, '�����');
INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (3, '������� 1');
INSERT INTO PRODUCT_LOCATION(location_id, name) VALUES (4, '������� 2');

INSERT INTO INVOICE(user_id, order_id, order_date, paid_date, sent_date, discount, pay, status_id, sec_name, first_name, third_name, phone, add_info_u, source_id, destination_id, delivery_id, delivery_office, prepayment) VALUES 
(2, 1, '98-12-31', '00-00-00', '00-00-00', 5.0, 125.65, 0, '����஢', '����', '���஢��', '3806741903706', '���⠢�� �� ����', 2, 1, 2, 12, 1);
INSERT INTO INVOICE(user_id, order_id, order_date, paid_date, sent_date, discount, pay, status_id, sec_name, first_name, third_name, phone, add_info_u, source_id, destination_id, delivery_id, delivery_office, prepayment) VALUES 
(1, 3, '98-10-20', '00-00-00', '00-00-00', 5.0, 135.80, 0, '����஢', '����', '���஢��', '3806741903706', '', 1, 3, 2, 12, 1);
INSERT INTO INVOICE(user_id, order_id, order_date, paid_date, sent_date, discount, pay, status_id, sec_name, first_name, third_name, phone, add_info_u, source_id, destination_id, delivery_id, delivery_office, prepayment, add_info_m, backorder_id) VALUES 
(1, 2, '85-11-14', '00-00-00', '00-00-00', 3.0, 220.50, 0, '���஢', '����', '���஢��', '380502103706', '���⠢�� �� ᪫���', 3, 1, 3, 8, 0, '������ �� ���짮��⥫�', 3);


INSERT INTO MANUFACTURE(manufact_id, name, address) VALUES (1, '�ਬ㫠', '��୨���, ��.������, 50');
INSERT INTO MANUFACTURE(manufact_id, name, address) VALUES (2, '������ ��ࠨ��', '����, �஢��᪮� ��ᯥ��, 35');

INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (1, NULL, '����');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (2, 1, '�����');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (3, 2, '����� �����');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (4, 1, '�������');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (5, 4, '������� ᫨���');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (6, 4, '������� ஧���');
INSERT INTO A_PRODUCT(product_id, parent_id, name) VALUES (7, 2, '����� ��᮫���');

INSERT INTO PACK(pack_id, name) VALUES (1, '�����쪠�');
INSERT INTO PACK(pack_id, name) VALUES (2, '�।���');
INSERT INTO PACK(pack_id, name) VALUES (3, '������');

INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (1, 100, NULL, 1);
INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (2, 200, NULL, 2);
INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (3, 500, NULL, 3);
INSERT INTO PACKING(packing_id, weight, amount, pack_id) VALUES (4, NULL, 50, 1);

INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000001, 1, 3, 1, 2.50);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000002, 1, 3, 2, 5.00);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000003, 1, 3, 3, 6.80);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000004, 2, 5, 4, 4.55);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000005, 1, 6, 2, 3.65);
INSERT INTO PRODUCT(barcode, manufact_id, product_id, packing_id, price) VALUES (0000000000006, 2, 7, 2, 5.20);

INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (1, 1, 0000000000001, 2.25, 5);
INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (2, 1, 0000000000002, 4.75, 8);
INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (3, 1, 0000000000003, 6.50, 4);
INSERT INTO AN_ORDER(id, order_id, barcode, price, amount) VALUES (4, 2, 0000000000002, 2.25, 3);

INSERT INTO AVAILABILITY(id, location_id, barcode, available, reserv) VALUES (1, 2, 0000000000001, 500, 20);
INSERT INTO AVAILABILITY(id, location_id, barcode, available, reserv) VALUES (2, 3, 0000000000001, 50, 10);
INSERT INTO AVAILABILITY(id, location_id, barcode, available, reserv) VALUES (3, 4, 0000000000001, 37, 15);
INSERT INTO AVAILABILITY(id, location_id, barcode, available, reserv) VALUES (4, 2, 0000000000002, 450, 50);
INSERT INTO AVAILABILITY(id, location_id, barcode, available) VALUES (5, 3, 0000000000002, 75);
INSERT INTO AVAILABILITY(id, location_id, barcode, available) VALUES (6, 4, 0000000000002, 28);



