create table form_bpmn
(
    form_id  varchar(255) not null
        primary key,
    bpmn_id  varchar(255) null,
    form_map varchar(255) null,
    bpmn_map varchar(255) null
);