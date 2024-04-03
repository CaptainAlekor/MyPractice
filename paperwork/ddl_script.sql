create table users (
    id serial primary key,
    email varchar(255) not null unique,
    password varchar(255) not null
);

create table groups (
    id serial primary key,
    name varchar(255) not null unique
);

create table students (
    id serial primary key,
    name varchar(255) not null,
    surname varchar(255) not null,
    group_id integer not null references groups (id),
    user_id integer not null unique references users (id) on delete cascade
);

create table professors (
    id serial primary key,
    name varchar(255) not null,
    surname varchar(255) not null,
    user_id integer not null unique references users (id) on delete cascade
);

create table courses (
    id serial primary key,
    name varchar(255) not null unique
);

create table group_courses (
    id serial primary key,
    group_id integer not null references groups (id) on delete cascade,
    course_id integer not null references courses (id) on delete cascade
);

create table professor_courses (
    id serial primary key,
    professor_id integer not null references professors (id) on delete cascade,
    course_id integer not null references courses (id) on delete cascade
);

create table grades (
    id serial primary key,
    value integer not null check (value >= 0 and value <= 10),
    date date not null,
    student_id integer not null references students (id) on delete cascade,
    course_id integer not null references courses (id) on delete cascade
);