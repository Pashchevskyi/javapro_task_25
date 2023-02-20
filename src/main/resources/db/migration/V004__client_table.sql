create TABLE IF NOT EXISTS public.client
(
id int NOT NULL,

name varchar(255),

email varchar(255),
phone varchar(17),

address_id int NOT NULL UNIQUE,
CONSTRAINT client_pkey PRIMARY KEY(id)
)