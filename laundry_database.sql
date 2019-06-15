
create database lol_db;

create table tb_state (
  id SERIAL PRIMARY KEY,
  name VARCHAR (256)
);


insert into tb_state(name) values ('Paraná');
insert into tb_state(name) values ('São Paulo');

create table tb_city (
  id SERIAL PRIMARY KEY,
  name VARCHAR (256),
  state_id INTEGER,
  FOREIGN KEY (state_id) REFERENCES tb_state(id)
);

insert into tb_city(name, state_id) values ('São Paulo', 2);
insert into tb_city(name, state_id) values ('Nova Independência', 2);
insert into tb_city(name, state_id) values ('Valinhos', 2);
insert into tb_city(name, state_id) values ('Santa Isabel', 2);
insert into tb_city(name, state_id) values ('Santo André', 2);
insert into tb_city(name, state_id) values ('Rubiácea', 2);

insert into tb_city(name, state_id) values ('Curitiba', 1);
insert into tb_city(name, state_id) values ('Indianópolis', 1);
insert into tb_city(name, state_id) values ('Paranavaí', 1);
insert into tb_city(name, state_id) values ('Mamborê', 1);
insert into tb_city(name, state_id) values ('Nova Olímpia', 1);
insert into tb_city(name, state_id) values ('Verê', 1);

create table tb_address (
  id SERIAL PRIMARY KEY,
  street VARCHAR (256),
  number VARCHAR (256),
  city_id INTEGER,
  FOREIGN KEY (city_id) REFERENCES tb_city(id)
);

insert into tb_address(street, number, city_id) values ('Alameda Mirella Meira', '1', 1);
insert into tb_address(street, number, city_id) values ('Ponte João Vitor Santana', '2', 2);
insert into tb_address(street, number, city_id) values ('Viela Mariana Silva', '3', 3);
insert into tb_address(street, number, city_id) values ('Rodovia Pedro Henrique Moreira', '4', 4);
insert into tb_address(street, number, city_id) values ('Ponte Márcio Santos', '5', 5);
insert into tb_address(street, number, city_id) values ('Rodovia Ana Vitória Nogueira', '6', 6);
insert into tb_address(street, number, city_id) values ('Viela Feliciano Jaques', '7', 7);
insert into tb_address(street, number, city_id) values ('Alameda Fabiano', '8', 8);


create table tb_user (
  id SERIAL PRIMARY KEY,
  role VARCHAR (20),
  name VARCHAR (256),
  email VARCHAR (256),
  password VARCHAR (256),
  sex VARCHAR(1),
  document VARCHAR(11),
  phone VARCHAR(12),
  address_id INTEGER,
  registration VARCHAR(12),
  FOREIGN KEY (address_id) REFERENCES tb_address(id)
);

insert into tb_user(role, name, email, password, sex, document, phone, address_id ) values ('admin', 'Admin Default' , 'admin@gmail.com', '12341234', 'M', 'document', 'phone', 1);
insert into tb_user(role, name, email, password, sex, document, phone, address_id) values ('customer', 'Customer Default' , 'customer@gmail.com', '12341234', 'M', 'document', 'phone', 2);
insert into tb_user(role, name, email, password, sex, document, phone, address_id) values ('customer', 'Isadora Velasques' , 'isadoravelasques@gmail.com', '12341234', 'M', 'document', 'phone', 3);



create table tb_order (
  id SERIAL PRIMARY KEY,
  address_id INTEGER,
  user_id INTEGER,
  price DECIMAL,
  dead_line TIMESTAMP,
  status VARCHAR(256),
  created_at TIMESTAMP,
  payment_date TIMESTAMP,
  FOREIGN KEY (address_id) REFERENCES tb_address(id),
  FOREIGN KEY (user_id) REFERENCES tb_user(id)
);

insert into tb_order(address_id, user_id, price, dead_line, status, created_at, payment_date) values (3, 2, 200.00, now(), 'waiting', now(), now());
insert into tb_order(address_id, user_id, price, dead_line, status, created_at, payment_date) values (3, 2, 200.00, now(), 'waiting', now(), now());
insert into tb_order(address_id, user_id, price, dead_line, status, created_at, payment_date) values (4, 3, 300.00, now(), 'waiting', now(), now());

create table tb_vesture (
  id SERIAL PRIMARY KEY,
  description VARCHAR(256),
  price DECIMAL,
  days INTEGER
);

insert into tb_vesture(description, price, days) values ('Camiseta Clara', 10.0, 3);
insert into tb_vesture(description, price, days) values ('Camiseta Escura', 8.0, 3);
insert into tb_vesture(description, price, days) values ('Calça de modetom', 8.0, 3);
insert into tb_vesture(description, price, days) values ('Camisa Social', 15.0, 3);
insert into tb_vesture(description, price, days) values ('Blazer', 20.0, 3);
insert into tb_vesture(description, price, days) values ('Saia', 5.0, 3);
insert into tb_vesture(description, price, days) values ('Maio', 9.0, 3);
insert into tb_vesture(description, price, days) values ('Meia', 3.50, 3);

create table tb_order_item (
  id SERIAL PRIMARY KEY,
  vesture_id INTEGER,
  order_id INTEGER,
  description VARCHAR(100),
  FOREIGN KEY (order_id) REFERENCES tb_order(id),
  FOREIGN KEY (vesture_id) REFERENCES tb_vesture(id)
);

insert into tb_order_item(vesture_id, order_id, description) values (1, 1, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (2, 1, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (3, 1, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (4, 1, 'Descrição');

insert into tb_order_item(vesture_id, order_id, description) values (5, 2, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (6, 2, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (7, 2, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (8, 2, 'Descrição');

insert into tb_order_item(vesture_id, order_id, description) values (2, 3, 'Descrição');
insert into tb_order_item(vesture_id, order_id, description) values (4, 3, 'Descrição');
