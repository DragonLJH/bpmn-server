create table user
(
    user_id       varchar(255)            not null
        primary key,
    user_name     varchar(255) default "" null,
    user_phone    varchar(255) default "" null,
    user_password varchar(255) default "" null
);