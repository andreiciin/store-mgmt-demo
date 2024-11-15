INSERT INTO role_details(role_name, role_description) VALUES ('ROLE_ADMIN', 'Administrator with full access');
INSERT INTO role_details(role_name, role_description) VALUES ('ROLE_OFFICER', 'User with access to all orders');
INSERT INTO role_details(role_name, role_description) VALUES ('ROLE_USER', 'Standard user with limited access');

INSERT INTO user_details (name, email, password, address) VALUES ('Gimi', 'gimi.h@yahoo.com', '{noop}pass', 'Gimi Street 11');
INSERT INTO user_details (name, email, password, address) VALUES ('Nina', 'nina.p@gmail.com', 'pass456', 'Nina Street 12');
INSERT INTO user_details (name, email, password, address) VALUES ('Doru', 'doru.m@hotmail.com', 'pass789', 'Doru Street 13');
INSERT INTO user_details (name, email, password, address) VALUES ('Moni', 'moni.h@yahoo.com', 'pass317', 'Moni Street 14');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (4, 3);

INSERT INTO product_details (product_name, price, description) VALUES ('House Credit', 300000, 'house credit for 30 yrs');
INSERT INTO product_details (product_name, price, description) VALUES ('Small Loan', 1200, 'personal need loan');
INSERT INTO product_details (product_name, price, description) VALUES ('Car Credit', 20000, 'car credit for 5 yrs');

INSERT INTO order_details (total_cost, order_date, user_id) VALUES (0, '2024-10-01', 1);
INSERT INTO order_details (total_cost, order_date, user_id) VALUES (0, '2024-10-02', 2);
INSERT INTO order_details (total_cost, order_date, user_id) VALUES (0, '2024-10-03', 3);

INSERT INTO order_products (order_id, product_id) VALUES (1, 2);
INSERT INTO order_products (order_id, product_id) VALUES (1, 3);
INSERT INTO order_products (order_id, product_id) VALUES (2, 3);
INSERT INTO order_products (order_id, product_id) VALUES (3, 1);