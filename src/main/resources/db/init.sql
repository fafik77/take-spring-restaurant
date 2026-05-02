-- Table: customers
CREATE TABLE public.customers (
    id BIGSERIAL PRIMARY KEY,
    delivery_address TEXT,
    phone_number VARCHAR(15),
    join_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Handled via @CreationTimestamp
);

-- Table: dishes
CREATE TABLE public.dishes (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);

-- Table: ingredients
CREATE TABLE public.ingredients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Table: orders
CREATE TABLE public.orders (
    id BIGSERIAL PRIMARY KEY,
    total_price NUMERIC(10, 2),
    delivery BOOLEAN,
    takeout BOOLEAN,
    self_takeout BOOLEAN,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- @CreationTimestamp
    deliver_date TIMESTAMP,
    customer_id BIGINT,
    CONSTRAINT fkpxtb8awmi0dk6smoh2vp1litg FOREIGN KEY (customer_id) REFERENCES public.customers(id)
);

-- Table: amount_of_ingredients
CREATE TABLE public.amount_of_ingredients (
    dish_id BIGINT,
    ingredient_id BIGINT,
    PRIMARY KEY (dish_id, ingredient_id),
    CONSTRAINT fklki3dijf2tihb0a5va0mej5h FOREIGN KEY (dish_id) REFERENCES public.dishes(id),
    CONSTRAINT fk57gksvlw9care7g5hka3g4phk FOREIGN KEY (ingredient_id) REFERENCES public.ingredients(id)
);

-- Table: order_items
CREATE TABLE public.order_items (
    order_id BIGINT,
    dish_id BIGINT,
    PRIMARY KEY (order_id, dish_id),
    CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES public.orders(id),
    CONSTRAINT fkn06bdypik73hotpxvefsrtn77 FOREIGN KEY (dish_id) REFERENCES public.dishes(id)
);















-- init data:
INSERT INTO public.customers (delivery_address, phone_number, join_date) VALUES
('ul. Zwycięstwa 12/4, 44-100 Gliwice', '500100200', '2026-01-15 10:30:00'),
('ul. Akademicka 5, 44-100 Gliwice', '600200300', '2026-02-20 14:15:00'),
('ul. Dolnych Wałów 8, 44-100 Gliwice', '700300400', '2026-03-05 09:00:00');

INSERT INTO public.ingredients (name) VALUES
('Sos pomidorowy San Marzano'),
('Mozzarella di Bufala'),
('Świeża bazylia'),
('Szynka Prosciutto Cotto'),
('Pieczarki'),
('Salami Ventricina (ostre)'),
('Ser Gorgonzola'),
('Ser Grana Padano'),
('Pancetta (włoski boczek)'),
('Świeże jaja'),
('Mięso mielone wołowe'),
('Mieszanka owoców morza'),
('Czosnek'),
('Oliwa Extra Vergine');

INSERT INTO public.dishes (name, price) VALUES
('Pizza Margherita', 32.00),
('Pizza Capricciosa', 39.00),
('Pizza Quattro Formaggi', 44.00),
('Pizza Diavola', 41.00),
('Spaghetti Carbonara', 36.00),
('Tagliatelle al Ragu', 35.00),
('Penne al Pesto', 34.00),
('Linguine ai Frutti di Mare', 49.00),
('Lasagne Klasyczna', 42.00),
('Gnocchi Sorrentina', 37.00);

