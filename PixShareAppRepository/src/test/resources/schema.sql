create schema pixsharedb collate utf8mb4_general_ci;

create table pixsharedb.hibernate_sequence
(
    next_val bigint null
)
    engine=MyISAM;

create table pixsharedb.user
(
    user_id int not null
        primary key,
    user_email varchar(255) null,
    user_first_name varchar(255) null,
    user_last_name varchar(255) null,
    password varchar(255) null
);

create table pixsharedb.image
(
    imageid int not null
        primary key,
    img_date varchar(255) null,
    link varchar(255) null,
    img_name varchar(255) null,
    size double null,
    userid int null,
    constraint user_fk
        foreign key (userid) references pixsharedb.user (user_id)
            on delete cascade
);

create table pixsharedb.sharedimage
(
    shared_image_id int not null
        primary key,
    image_name varchar(255) null,
    image_id int null,
    user_id_shared int null,
    user_id_sharer int null,
    constraint image_fk
        foreign key (image_id) references pixsharedb.image (imageid)
            on delete cascade,
    constraint shared_user_fk
        foreign key (user_id_shared) references pixsharedb.user (user_id)
            on delete cascade,
    constraint sharer_user_fk
        foreign key (user_id_sharer) references pixsharedb.user (user_id)
            on delete cascade
);

