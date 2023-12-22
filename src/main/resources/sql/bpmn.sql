create table bpmn
(
    bpmn_id  varchar(255)            not null
        primary key,
    xml_file varchar(255) default '' null,
    map_file varchar(255) default '' null
);