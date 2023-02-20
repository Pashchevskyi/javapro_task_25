alter table public.address add client_id int NOT NULL UNIQUE,
 ADD CONSTRAINT address_client_fkey FOREIGN KEY(client_id) REFERENCES client(id);