-- Table: tenant_details
CREATE TABLE IF NOT EXISTS tenant_details
(
    created_date timestamp(6) without time zone,
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    issuer character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255),
    tenant_id character varying(255) NOT NULL,
    CONSTRAINT tenant_details_pkey PRIMARY KEY (id),
    CONSTRAINT tenant_details_email_key UNIQUE (email)
);

CREATE SEQUENCE IF NOT EXISTS tenant_details_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

-- Table: admin
CREATE TABLE IF NOT EXISTS admin
(
    created_date timestamp(6) without time zone,
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    role character varying(255),
    CONSTRAINT admin_pkey PRIMARY KEY (id),
    CONSTRAINT admin_email_key UNIQUE (email)
);

CREATE SEQUENCE IF NOT EXISTS admin_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


