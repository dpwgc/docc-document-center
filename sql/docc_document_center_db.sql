create table `category`
(
    id            bigint unsigned auto_increment
        primary key,
    app_id        varchar(63)  not null comment '应用id',
    category_id   varchar(63)  not null comment '分类id',
    parent_id     varchar(63)  not null comment '父类id',
    category_name varchar(127) not null default '' comment '分类名称',
    detail        text                  default '' comment '详情',
    extra         text                  default '' comment '附加内容',
    score         bigint       not null default 0 comment '排序权值',
    attr          int          not null default 0 comment '属性',
    type          int          not null default 0 comment '类型',
    status        int          not null default 1 comment '状态（1-正常、0-删除）',
    version       bigint       not null default 0 comment '版本号（乐观锁）',
    create_time   bigint       not null default 0 comment '创建时间',
    update_time   bigint       not null default 0 comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table `column`
(
    id            bigint unsigned auto_increment
        primary key,
    app_id        varchar(63)  not null comment '应用id',
    category_id   varchar(63)  not null comment '分类id',
    column_id     varchar(63)  not null comment '专栏id',
    author_id     varchar(63)  not null comment '作者id',
    column_name   varchar(127) not null default '' comment '专栏名称',
    detail        text                  default '' comment '详情',
    extra         text                  default '' comment '附加内容',
    score         bigint       not null default 0 comment '排序权值',
    attr          int          not null default 0 comment '属性',
    type          int          not null default 0 comment '类型',
    status        int          not null default 1 comment '状态（1-正常、0-删除）',
    version       bigint       not null default 0 comment '版本号（乐观锁）',
    create_time   bigint       not null default 0 comment '创建时间',
    update_time   bigint       not null default 0 comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table `tag`
(
    id          bigint unsigned auto_increment
        primary key,
    app_id      varchar(63)  not null comment '应用id',
    tag_name    varchar(127) not null default '' comment '标签名称',
    number      bigint       not null default 0 comment '带有该标签的文档数量',
    status      int          not null default 1 comment '状态（1-正常、0-删除）',
    version     bigint       not null default 0 comment '版本号（乐观锁）',
    create_time bigint       not null default 0 comment '创建时间',
    update_time bigint       not null default 0 comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table `comment`
(
    id              bigint unsigned auto_increment
        primary key,
    app_id          varchar(63) not null comment '应用id',
    document_id     varchar(63) not null comment '文档id',
    comment_id      varchar(63) not null comment '评论id',
    author_id       varchar(63) not null comment '作者id',
    content         text                 default '' comment '主体内容',
    extra           text                 default '' comment '附加内容',
    like_num        bigint      not null default 0 comment '点赞数',
    love_num        bigint      not null default 0 comment '收藏数',
    share_num       bigint      not null default 0 comment '转发数',
    sub_comment_num bigint      not null default 0 comment '子评论数',
    attr            int         not null default 0 comment '属性',
    type            int         not null default 0 comment '类型',
    status          int         not null default 1 comment '状态（1-正常、0-删除）',
    version         bigint      not null default 0 comment '版本号（乐观锁）',
    create_time     bigint      not null default 0 comment '创建时间',
    update_time     bigint      not null default 0 comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

create table `sub_comment`
(
    id             bigint unsigned auto_increment
        primary key,
    app_id         varchar(63) not null comment '应用id',
    document_id    varchar(63) not null comment '文档id',
    comment_id     varchar(63) not null comment '评论id',
    sub_comment_id varchar(63) not null comment '子评论id',
    author_id      varchar(63) not null comment '作者id',
    reply_to       varchar(63) not null comment '回复对象id',
    content        text                 default '' comment '主体内容',
    extra          text                 default '' comment '附加内容',
    like_num       bigint      not null default 0 comment '点赞数',
    love_num       bigint      not null default 0 comment '收藏数',
    share_num      bigint      not null default 0 comment '转发数',
    attr           int         not null default 0 comment '属性',
    type           int         not null default 0 comment '类型',
    status         int         not null default 1 comment '状态（1-正常、0-删除）',
    version        bigint      not null default 0 comment '版本号（乐观锁）',
    create_time    bigint      not null default 0 comment '创建时间',
    update_time    bigint      not null default 0 comment '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
