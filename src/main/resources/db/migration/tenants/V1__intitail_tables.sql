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
        CONSTRAINT user_details_pkey PRIMARY KEY (id),
        CONSTRAINT uk_4d9rdl7d52k8x3etihxlaujvh UNIQUE (email)
);

-- Table: public.course
CREATE TABLE IF NOT EXISTS course
(
    course_id integer NOT NULL,
    author_name character varying(255) ,
    category character varying(255) ,
    date timestamp(6) without time zone,
    description character varying(255) ,
    enrolled bigint,
    language character varying(255) ,
    overview character varying(255) ,
    price integer,
    ratings integer,
    thumb_nail text ,
    title character varying(255) ,
    what_you_will_learn text ,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);
-- Table: public.section
CREATE TABLE IF NOT EXISTS section
(
    section_id integer NOT NULL,
    key integer,
    title character varying(255) ,
    course_id integer,
    CONSTRAINT section_pkey PRIMARY KEY (section_id),
    CONSTRAINT fkoy8uc0ftpivwopwf5ptwdtar0 FOREIGN KEY (course_id)
        REFERENCES course (course_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
-- Table: public.sub_section
CREATE TABLE IF NOT EXISTS sub_section
(
    sub_section_id integer NOT NULL,
    description character varying(255) ,
    key integer,
    link character varying(255) ,
    title character varying(255) ,
    section_id integer,
    CONSTRAINT sub_section_pkey PRIMARY KEY (sub_section_id),
    CONSTRAINT fk6qmlkrayugejciehhvnyoohut FOREIGN KEY (section_id)
        REFERENCES section (section_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Table: public.quiz
CREATE TABLE IF NOT EXISTS quiz
(
    quiz_id integer NOT NULL,
    answer integer NOT NULL,
    key integer,
    options character varying(255)[] ,
    question character varying(255) ,
    sub_section_id integer,
    title character varying(255)  ,
    CONSTRAINT quiz_pkey PRIMARY KEY (quiz_id),
    CONSTRAINT fkbq87nt5wprr78k7d6mqwrenjf FOREIGN KEY (sub_section_id)
        REFERENCES sub_section (sub_section_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
-- Table: public.quiz_rank
CREATE TABLE IF NOT EXISTS quiz_rank
(
    rank_id integer NOT NULL,
    badge integer NOT NULL,
    energy_points integer,
    sub_section_id integer,
    user_id bigint,
    CONSTRAINT quiz_rank_pkey PRIMARY KEY (rank_id)
);
-- Table: public.cart
CREATE TABLE IF NOT EXISTS cart
(
    cart_id bigint NOT NULL,
    course_id integer,
    create_date timestamp(6) without time zone,
    user_id bigint,
    CONSTRAINT cart_pkey PRIMARY KEY (cart_id)
);



