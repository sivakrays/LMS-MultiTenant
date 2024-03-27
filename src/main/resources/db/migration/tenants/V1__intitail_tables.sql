CREATE TABLE IF NOT EXISTS user_details
(
     id bigint NOT NULL,
        city character varying(255) ,
        confirm_password character varying(255)  NOT NULL,
        country character varying(255) ,
        created_date timestamp(6) without time zone,
        email character varying(255)  NOT NULL,
        gender character varying(255) ,
        name character varying(255)  NOT NULL,
        password character varying(255)  NOT NULL,
        role character varying(255) ,
        school character varying(255) ,
        standard integer,
        profile_image text,
        CONSTRAINT user_details_pkey PRIMARY KEY (id),
        CONSTRAINT uk_4d9rdl7d52k8x3etihxlaujvh UNIQUE (email)
);

-- Table: public.course
CREATE TABLE IF NOT EXISTS course
(
    price integer,
    ratings integer,
    date timestamp(6) without time zone,
    enrolled bigint,
    user_id bigint NOT NULL,
    course_id uuid NOT NULL,
    author_name character varying(255) ,
    category character varying(255) ,
    description text  ,
    language character varying(255) ,
    overview character varying(255) ,
    thumb_nail text ,
    title character varying(255) ,
    what_you_will_learn text ,
    is_html_course boolean,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);
-- Table: public.section
CREATE TABLE IF NOT EXISTS section
(
    key integer,
    course_id uuid,
    section_id uuid NOT NULL,
    title character varying(255) ,
    CONSTRAINT section_pkey PRIMARY KEY (section_id),
    CONSTRAINT fkoy8uc0ftpivwopwf5ptwdtar0 FOREIGN KEY (course_id)
        REFERENCES course (course_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
-- Table: public.sub_section
CREATE TABLE IF NOT EXISTS sub_section
(
    key integer,
    section_id uuid,
    sub_section_id uuid NOT NULL,
    description text ,
    link text ,
    title character varying(255) ,
    CONSTRAINT sub_section_pkey PRIMARY KEY (sub_section_id),
    CONSTRAINT fk6qmlkrayugejciehhvnyoohut FOREIGN KEY (section_id)
        REFERENCES section (section_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: public.quiz
CREATE TABLE IF NOT EXISTS quiz
(
    answer text,
    key integer,
    quiz_id uuid NOT NULL,
    sub_section_id uuid,
    question text ,
    title character varying(255) ,
    options character varying(255)[] ,
    CONSTRAINT quiz_pkey PRIMARY KEY (quiz_id),
    CONSTRAINT fkbq87nt5wprr78k7d6mqwrenjf FOREIGN KEY (sub_section_id)
        REFERENCES sub_section (sub_section_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
-- Table: public.quiz_rank
CREATE TABLE IF NOT EXISTS quiz_rank
(
    badge integer NOT NULL,
    energy_points integer,
    user_id bigint,
    rank_id uuid NOT NULL,
    sub_section_id uuid,
    CONSTRAINT quiz_rank_pkey PRIMARY KEY (rank_id)
);
-- Table: public.cart
CREATE TABLE IF NOT EXISTS cart
(
    create_date timestamp(6) without time zone,
    user_id bigint,
    cart_id uuid NOT NULL,
    course_id uuid,
    CONSTRAINT cart_pkey PRIMARY KEY (cart_id)
);


CREATE TABLE IF NOT EXISTS edu_content
(
    id bigint NOT NULL,
    image text ,
    image_content text ,
    standard character varying(255) ,
    tenant_id character varying(255) ,
    image_title character varying(255) ,
    CONSTRAINT edu_content_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS home
(
    id bigint NOT NULL,
    banner_image text,
    course_title character varying(255) ,
    course_title2 character varying(255) ,
    education_title character varying(255) ,
    home_title character varying(255) ,
    promo_description text,
    promo_title text ,
    promo_video text ,
    standard character varying(255) ,
    support_number character varying(255),
    tenant_id character varying(255) ,
    theme text ,
    CONSTRAINT home_pkey PRIMARY KEY (id)
);



