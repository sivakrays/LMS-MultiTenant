-- Table: public.tenant_details
CREATE TABLE IF NOT EXISTS tenant_details
(
    id bigint NOT NULL,
    email character varying(255)  NOT NULL,
    issuer character varying(255)  NOT NULL,
    password character varying(255)  NOT NULL,
    tenant_id character varying(255)  NOT NULL,
    CONSTRAINT tenant_details_pkey PRIMARY KEY (id),
    CONSTRAINT uk_byhkipf4dsvjqj0op6lf56trw UNIQUE (email)
)
