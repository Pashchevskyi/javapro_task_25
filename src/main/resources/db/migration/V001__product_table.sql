create TABLE IF NOT EXISTS public.product
(
    id bigint NOT NULL,
    name varchar(255) NOT NULL,
    description varchar(255),
    price float,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)