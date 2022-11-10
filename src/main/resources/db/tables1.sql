

CREATE TABLE IF NOT EXISTS public.customer
(
    id uuid NOT NULL default gen_random_uuid(),
    name character varying(100) ,
    address character varying(100) ,
    mailId character varying(255) ,
    phoneNumber character varying(255),
    CONSTRAINT customer_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

alter table public.customer add updated_at timestamp;

alter table public.customer add password character varying(100) not null ;

CREATE TABLE IF NOT EXISTS public.product
(
    id uuid NOT NULL default gen_random_uuid(),
    name character varying(100) ,
    cost bigint ,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.farm
(
    id uuid NOT NULL default gen_random_uuid(),
    type_of_farm character varying(100) ,
    cost bigint ,
    CONSTRAINT farm_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.client
(
    id uuid NOT NULL default gen_random_uuid(),
    name character varying(100) ,
    address character varying(100) ,
    mailId character varying(255) ,
    phoneNumber character varying(255),
    CONSTRAINT client_pkey PRIMARY KEY (id)
)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.order_product
(
    order_id uuid NOT NULL default gen_random_uuid(),
    product_id uuid NOT NULL ,
    CONSTRAINT order_pkey PRIMARY KEY (order_id),
    CONSTRAINT fk_product FOREIGN KEY (product_id)
        REFERENCES public.order_product (order_id)  MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_product FOREIGN KEY (product_id)
        REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.order_farm
(
    order_id uuid NOT NULL default gen_random_uuid(),
    farm_id uuid NOT NULL ,
    CONSTRAINT order_pkeys PRIMARY KEY (order_id),
    CONSTRAINT fk_farm FOREIGN KEY (farm_id)
        REFERENCES public.order_farm (order_id)  MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_farm FOREIGN KEY (farm_id)
        REFERENCES public.farm (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.order_Customer
(
    order_id uuid NOT NULL default gen_random_uuid(),
    customer_id uuid NOT NULL ,
    CONSTRAINT order_pkeysss PRIMARY KEY (order_id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id)
        REFERENCES public.order_customer (order_id)  MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.order_Client
(
    order_id uuid NOT NULL default gen_random_uuid(),
    client_id uuid NOT NULL ,
    CONSTRAINT order_pkeyss PRIMARY KEY (order_id),
    CONSTRAINT fk_client FOREIGN KEY (client_id)
        REFERENCES public.order_client (order_id)  MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_order_client FOREIGN KEY (client_id)
        REFERENCES public.client(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

alter table public.client add updated_at timestamp;

alter table public.client add password character varying(100) not null ;




