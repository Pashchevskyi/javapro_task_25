alter table public.order_item add order_id int not null,
 add constraint order_item_order_fkey foreign key(order_id) references "order"(id)