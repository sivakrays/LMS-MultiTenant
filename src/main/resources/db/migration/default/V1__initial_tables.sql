-- Table: public.tenant_details
CREATE TABLE IF NOT EXISTS tenant_details
(
    id bigint NOT NULL,
    email character varying(255)  NOT NULL,
    issuer character varying(255)  NOT NULL,
    password character varying(255)  NOT NULL,
    tenant_id character varying(255)  NOT NULL,
    created_date timestamp(6) without time zone,
    role character varying(255),
    CONSTRAINT tenant_details_pkey PRIMARY KEY (id),
    CONSTRAINT uk_byhkipf4dsvjqj0op6lf56trw UNIQUE (email)
);

CREATE SEQUENCE IF NOT EXISTS tenant_details_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
-- Table: public.admin
CREATE TABLE IF NOT EXISTS admin
(
    id bigint NOT NULL,
    created_date timestamp(6) without time zone,
    email character varying(255),
    password character varying(255) ,
    role character varying(255),
    CONSTRAINT admin_pkey PRIMARY KEY (id),
    CONSTRAINT uk_c0r9atamxvbhjjvy5j8da1kam UNIQUE (email)
);
CREATE SEQUENCE IF NOT EXISTS admin_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


