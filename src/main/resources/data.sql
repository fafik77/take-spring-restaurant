insert into customers(id, delivery_address)
values(1,'sezamkowa')
ON CONFLICT DO NOTHING;

--insert into orders(id, customer_id, delivery, takeout, self_takeout, total_price)
--values(1,1,false ,false ,false ,0)
--ON CONFLICT DO NOTHING;
