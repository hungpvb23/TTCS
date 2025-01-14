CREATE TABLE public.classes (
                                class_id serial4 NOT NULL,
                                class_name varchar(100) NOT NULL,
                                teacher_name varchar(100) NOT NULL,
                                semester varchar(20) NOT NULL,
                                created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                updated_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                CONSTRAINT classes_pkey PRIMARY KEY (class_id)
);

CREATE TABLE public.courses (
                                course_id serial4 NOT NULL,
                                course_name varchar(100) NOT NULL,
                                course_code varchar(20) NOT NULL,
                                credits int4 NOT NULL,
                                created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                updated_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                CONSTRAINT courses_course_code_key UNIQUE (course_code),
                                CONSTRAINT courses_credits_check CHECK ((credits > 0)),
                                CONSTRAINT courses_pkey PRIMARY KEY (course_id)
);

CREATE TABLE public.student_classes (
                                        id serial4 NOT NULL,
                                        student_id int4 NULL,
                                        class_id int4 NULL,
                                        enrolled_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                        CONSTRAINT student_classes_pkey PRIMARY KEY (id),
                                        CONSTRAINT student_classes_student_id_class_id_key UNIQUE (student_id, class_id),
                                        CONSTRAINT student_classes_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.classes(class_id) ON DELETE CASCADE,
                                        CONSTRAINT student_classes_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.students(student_id) ON DELETE CASCADE
);

CREATE TABLE public.student_course_grades (
                                              id serial4 NOT NULL,
                                              student_id int4 NOT NULL,
                                              course_id int4 NOT NULL,
                                              grade float8 NOT NULL,
                                              CONSTRAINT student_course_grades_pkey PRIMARY KEY (id),
                                              CONSTRAINT student_course_grades_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.courses(course_id),
                                              CONSTRAINT student_course_grades_student_id_fkey FOREIGN KEY (student_id) REFERENCES public.students(student_id)
);

CREATE TABLE public.students (
                                 student_id serial4 NOT NULL,
                                 first_name varchar(50) NOT NULL,
                                 last_name varchar(50) NOT NULL,
                                 date_of_birth date NOT NULL,
                                 gender varchar(10) NULL,
                                 email varchar(100) NOT NULL,
                                 phone varchar(15) NULL,
                                 address text NULL,
                                 created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                 updated_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
                                 student_code varchar(50) NULL,
                                 CONSTRAINT students_pkey PRIMARY KEY (student_id)
);

CREATE TABLE public.users (
                              id serial4 NOT NULL,
                              username varchar(50) NOT NULL,
                              "password" varchar(255) NOT NULL,
                              CONSTRAINT users_pkey PRIMARY KEY (id),
                              CONSTRAINT users_username_key UNIQUE (username)
);