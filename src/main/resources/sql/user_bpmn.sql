create table user_bpmn
(
    id      int auto_increment
        primary key,
    user_id varchar(255) default '' not null,
    bpmn_id varchar(255) default '' not null
);