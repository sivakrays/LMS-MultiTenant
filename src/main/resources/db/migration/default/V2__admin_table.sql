-- Table: public.admin
CREATE TABLE IF NOT EXISTS admin
(
    id bigint NOT NULL,
    created_date timestamp(6) without time zone,
    email character varying(255),
    password character varying(255) ,
    CONSTRAINT admin_pkey PRIMARY KEY (id),
    CONSTRAINT uk_c0r9atamxvbhjjvy5j8da1kam UNIQUE (email)
)
