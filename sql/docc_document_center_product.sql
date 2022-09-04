create table category
(
    id            bigint unsigned auto_increment
        primary key,
    app_id        varchar(64)  not null,
    category_id   varchar(64)  not null,
    parent_id     varchar(64)  not null,
    category_name varchar(100) not null,
    detail        text         not null,
    extra         text         not null,
    score         bigint       not null,
    status        int          not null,
    attr          int          not null,
    type          int          not null,
    create_time   bigint       not null,
    update_time   bigint       not null
)
    auto_increment = 4;

create table tag
(
    id          bigint unsigned auto_increment
        primary key,
    app_id      varchar(64)  not null,
    tagName     varchar(100) not null,
    number      bigint       not null,
    status      int          not null,
    create_time bigint       not null,
    update_time bigint       not null
);
