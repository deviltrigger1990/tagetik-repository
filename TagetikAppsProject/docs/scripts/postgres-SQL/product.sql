CREATE TABLE IF NOT EXISTS public.product
(
    product_id integer NOT NULL,
    creation_date timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    category smallint,
    description character varying(255) COLLATE pg_catalog."default",
    price double precision NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (product_id),
    CONSTRAINT product_category_check CHECK (category >= 0 AND category <= 2)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product
    OWNER to tagetik;