CREATE TABLE IF NOT EXISTS user_details
(
    standard character varying(255),
    created_date timestamp(6) without time zone,
    id bigint NOT NULL,
    city character varying(255),
    confirm_password character varying(255),
    country character varying(255),
    email character varying(255),
    gender character varying(255),
    name character varying(255),
    password character varying(255),
    profile_image text,
    role character varying(255),
    school character varying(255),
    CONSTRAINT user_details_pkey PRIMARY KEY (id),
    CONSTRAINT user_details_email_key UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS otp
(
    otp_id bigint NOT NULL DEFAULT nextval('otp_otp_id_seq'),
    email character varying(255),
    expiry_time timestamp(6) without time zone,
    otp character varying(255),
    CONSTRAINT otp_pkey PRIMARY KEY (otp_id)
);

CREATE TABLE IF NOT EXISTS course
(
    is_free boolean,
    discount_percentage integer,
        is_html_course boolean,
        price integer NOT NULL,
        ratings integer NOT NULL,
        created_date timestamp(6) without time zone,
        enrolled bigint NOT NULL,
        updated_time timestamp(6) without time zone,
        user_id bigint NOT NULL,
        visible_to character varying(255),
        author_name character varying(255),
        category character varying(255),
        course_id character varying(255) NOT NULL,
        description text,
        language character varying(255),
        overview character varying(255),
        thumb_nail text,
        title character varying(255),
        what_you_will_learn text,
        CONSTRAINT course_pkey PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS section
(
    key integer,
        course_id character varying(255),
        section_id character varying(255),
        title character varying(255),
        CONSTRAINT section_pkey PRIMARY KEY (section_id),
        CONSTRAINT fkoy8uc0ftpivwopwf5ptwdtar0 FOREIGN KEY (course_id)
            REFERENCES course (course_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS sub_section
(
     key integer,
        description text,
        link text,
        section_id character varying(255),
        sub_section_id character varying(255),
        title character varying(255),
        CONSTRAINT sub_section_pkey PRIMARY KEY (sub_section_id),
        CONSTRAINT fk6qmlkrayugejciehhvnyoohut FOREIGN KEY (section_id)
            REFERENCES section (section_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS quiz
(
    key integer,
        answer character varying(255),
        question text,
        quiz_id character varying(255),
        section_id character varying(255),
        title character varying(255),
        options character varying(255)[],
        CONSTRAINT quiz_pkey PRIMARY KEY (quiz_id),
        CONSTRAINT fkmlx8xst0rbhivy6wu01xn9mb0 FOREIGN KEY (section_id)
            REFERENCES section (section_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS quiz_rank
(
    badge integer NOT NULL,
        energy_points integer,
        user_id bigint NOT NULL,
        rank_id character varying(255),
        section_id character varying(255),
        CONSTRAINT quiz_rank_pkey PRIMARY KEY (rank_id)
);

CREATE TABLE IF NOT EXISTS cart
(
    create_date timestamp(6) without time zone,
        user_id bigint NOT NULL,
        cart_id character varying(255),
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
        banner_heading text,
        banner_image text,
        banner_paragraph text,
        banner_sub_heading text,
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

CREATE TABLE IF NOT EXISTS chapter
(
    chapter_order integer,
        user_id bigint NOT NULL,
        chapter character varying(255),
        chapter_id character varying(255) NOT NULL,
        html_course_id character varying(255),
        CONSTRAINT chapter_pkey PRIMARY KEY (chapter_id),
        CONSTRAINT fkcbk2o5i1mtbt6byjylminw3hb FOREIGN KEY (html_course_id)
            REFERENCES course (course_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS chapter_content
(
    chapter_content_order integer,
        order_changed boolean,
        chapter_content_id character varying(255) NOT NULL,
        chapter_id character varying(255),
        content text,
        image text,
        type character varying(255),
        CONSTRAINT chapter_content_pkey PRIMARY KEY (chapter_content_id),
        CONSTRAINT fk64p0ov70yngvygwyup0xcy8f0 FOREIGN KEY (chapter_id)
            REFERENCES chapter (chapter_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS purchased_course
(
    purchased boolean NOT NULL,
        purchased_id bigint NOT NULL,
        completed_date timestamp(6) without time zone,
        purchased_on timestamp(6) without time zone,
        user_id bigint NOT NULL,
        is_completed boolean NOT NULL,
        course_id character varying(255) NOT NULL,
        CONSTRAINT purchased_course_pkey PRIMARY KEY (purchased_id)
);

CREATE TABLE IF NOT EXISTS feedback
(
    active boolean NOT NULL,
        rating integer NOT NULL,
        reviewed boolean NOT NULL,
        created_date timestamp(6) without time zone,
        id bigint NOT NULL,
        user_id bigint NOT NULL,
        comment text,
        CONSTRAINT feedback_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS course_tracker
(
    duration bigint NOT NULL,
        id bigint NOT NULL,
        user_id bigint NOT NULL,
        course_id character varying(255) NOT NULL,
        section_id character varying(255) NOT NULL,
        sub_section_id character varying(255) NOT NULL,
        CONSTRAINT course_tracker_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS class_room
(
    classroom_id character varying(255) NOT NULL,
    class_room_name character varying(255),
    created_by bigint,
    no_of_users bigint,
    CONSTRAINT class_room_pkey PRIMARY KEY (classroom_id)
);

CREATE TABLE IF NOT EXISTS classroom_data
(
    id character varying(255) NOT NULL,
    classroom_id character varying(255) NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT classroom_data_pkey PRIMARY KEY (id)
);



