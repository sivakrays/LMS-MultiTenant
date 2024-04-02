CREATE TABLE IF NOT EXISTS user_details
(
     standard integer,
         created_date timestamp(6) without time zone,
         id bigint NOT NULL,
         city character varying(255),
         confirm_password character varying(255) NOT NULL,
         country character varying(255),
         email character varying(255) NOT NULL,
         gender character varying(255) ,
         name character varying(255) NOT NULL,
         password character varying(255) NOT NULL,
         profile_image text,
         role character varying(255),
         school character varying(255),
         CONSTRAINT user_details_pkey PRIMARY KEY (id),
         CONSTRAINT user_details_email_key UNIQUE (email)
);

-- Table: course
CREATE TABLE IF NOT EXISTS course
(
    is_html_course boolean,
        price integer,
        ratings integer,
        date timestamp(6) without time zone,
        enrolled bigint,
        user_id bigint NOT NULL,
        author_name character varying(255),
        category character varying(255),
        course_id character varying(255),
        description text,
        language character varying(255),
        overview character varying(255),
        thumb_nail text,
        title character varying(255),
        what_you_will_learn text,
        CONSTRAINT course_pkey PRIMARY KEY (course_id)
);
-- Table: section
CREATE TABLE IF NOT EXISTS section
(
    key integer,
        course_id character varying(255),
        section_id character varying(255) NOT NULL,
        title character varying(255),
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
        description text,
        link text,
        section_id character varying(255),
        sub_section_id character varying(255) NOT NULL,
        title character varying(255),
        CONSTRAINT sub_section_pkey PRIMARY KEY (sub_section_id),
        CONSTRAINT fk6qmlkrayugejciehhvnyoohut FOREIGN KEY (section_id)
            REFERENCES section (section_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

-- Table: quiz
CREATE TABLE IF NOT EXISTS quiz
(
   key integer,
       answer character varying(255),
       question text,
       quiz_id character varying(255) NOT NULL,
       sub_section_id character varying(255),
       title character varying(255),
       options character varying(255)[],
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
        rank_id character varying(255) NOT NULL,
        sub_section_id character varying(255),
        CONSTRAINT quiz_rank_pkey PRIMARY KEY (rank_id)
);
-- Table: public.cart
CREATE TABLE IF NOT EXISTS cart
(
    create_date timestamp(6) without time zone,
        user_id bigint,
        cart_id character varying(255) NOT NULL,
        course_id character varying(255),
        CONSTRAINT cart_pkey PRIMARY KEY (cart_id)
);


CREATE TABLE IF NOT EXISTS edu_content
(
    id bigint NOT NULL,
        image text,
        image_content text,
        image_title character varying(255),
        standard character varying(255),
        tenant_id character varying(255),
        CONSTRAINT edu_content_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS home
(
    id bigint NOT NULL,
        banner_image text,
        course_title character varying(255),
        course_title2 character varying(255),
        education_title character varying(255),
        home_title character varying(255),
        promo_description text,
        promo_title text,
        promo_video text,
        standard character varying(255),
        support_number character varying(255),
        tenant_id character varying(255),
        theme text,
        CONSTRAINT home_pkey PRIMARY KEY (id)
);



