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
     is_html_course boolean NOT NULL,
        price integer NOT NULL,
        ratings integer NOT NULL,
        date timestamp(6) without time zone,
        enrolled bigint NOT NULL,
        user_id bigint NOT NULL,
        author_name character varying(255) ,
        category character varying(255) ,
        course_id character varying(255)  NOT NULL,
        description text ,
        language character varying(255) ,
        overview character varying(255) ,
        thumb_nail text ,
        title character varying(255) ,
        what_you_will_learn text ,
        CONSTRAINT course_pkey PRIMARY KEY (course_id)
);
-- Table: public.section
CREATE TABLE IF NOT EXISTS section
(
     key integer,
        course_id character varying(255) ,
        section_id character varying(255)  NOT NULL,
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
       description text ,
       link text ,
       section_id character varying(255) ,
       sub_section_id character varying(255)  NOT NULL,
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
     key integer,
        answer character varying(255) ,
        question text ,
        quiz_id character varying(255)  NOT NULL,
        sub_section_id character varying(255) ,
        title character varying(255)  ,
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
       rank_id character varying(255)  NOT NULL,
       sub_section_id character varying(255) ,
       CONSTRAINT quiz_rank_pkey PRIMARY KEY (rank_id)
);
-- Table: public.cart
CREATE TABLE IF NOT EXISTS cart
(
     create_date timestamp(6) without time zone,
        user_id bigint,
        cart_id character varying(255)  NOT NULL,
        course_id character varying(255) ,
        CONSTRAINT cart_pkey PRIMARY KEY (cart_id)
);



CREATE TABLE IF NOT EXISTS html_course
(
     chapter_order integer,
         order_changed boolean NOT NULL,
         user_id bigint,
         chapter character varying(255) ,
         content character varying(255) ,
         html_course_id character varying(255) ,
         id character varying(255) NOT NULL,
         image character varying(255) ,
         type character varying(255) ,
         CONSTRAINT html_course_pkey PRIMARY KEY (id),
         CONSTRAINT fk5hpxxy37f1y5iy0d3p191wwqu FOREIGN KEY (html_course_id)
             REFERENCES course (course_id) MATCH SIMPLE
             ON UPDATE NO ACTION
             ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS purchased_course
(
      purchased boolean,
       id bigint NOT NULL,
       purchased_on timestamp(6) without time zone,
       user_id bigint,
       course_id character varying(255),
       CONSTRAINT purchased_course_pkey PRIMARY KEY (id)
);


