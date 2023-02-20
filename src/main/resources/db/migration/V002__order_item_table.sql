create TABLE IF NOT EXISTS public.order_item
(
id int NOT NULL,

product_id int NOT NULL,

count int,
CONSTRAINT order_item_pkey PRIMARY KEY (id),
CONSTRAINT order_item_product_fkey FOREIGN KEY(product_id) REFERENCES product(id)
)