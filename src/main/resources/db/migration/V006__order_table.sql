create TABLE IF NOT EXISTS public.order
(
id int NOT NULL,
client_id int NOT NULL,
CONSTRAINT order_pkey PRIMARY KEY(id),
CONSTRAINT order_client_fkey FOREIGN KEY(client_id) REFERENCES client(id)
)