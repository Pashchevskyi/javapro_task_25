create TABLE IF NOT EXISTS public.address
(
id int NOT NULL,

country varchar(255),

city varchar(255),

street varchar(255),

house varchar(255),
CONSTRAINT address_pkey PRIMARY KEY(id)
)