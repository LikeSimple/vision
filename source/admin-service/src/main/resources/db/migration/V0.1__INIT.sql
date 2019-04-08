create table city
(
    id char(22) default '' not null
        primary key,
    province_id char(22) default '' not null,
    name varchar(30) default '' not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null
);

create table county
(
    id char(22) default '' not null
        primary key,
    name varchar(30) default '' not null,
    city_id char(22) default '' not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null
);

create table province
(
    id char(22) default '' not null
        primary key,
    name varchar(30) default '' not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null
);

create table system_audit
(
    id int auto_increment
        primary key,
    user_id char(22) not null,
    name varchar(30) not null,
    action varchar(200) not null,
    parameters varchar(500) null,
    execution_time datetime default CURRENT_TIMESTAMP not null
)
    comment '系统操作审计';

create table system_authority
(
    id char(22) not null
        primary key,
    name varchar(40) not null,
    `desc` varchar(200) null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint name
        unique (name)
)
    comment '系统权限';

create table system_resource
(
    id char(22) not null
        primary key,
    name varchar(30) not null,
    serial_key varchar(6) not null,
    url varchar(100) not null,
    icon varchar(40) null,
    remark varchar(100) default '' not null,
    enabled tinyint(1) default 1 not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint name
        unique (name),
    constraint serial_key
        unique (serial_key)
)
    comment '系统资源';

create table system_resource_authority
(
    resource_id char(22) not null,
    authority_id char(22) not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    primary key (resource_id, authority_id)
)
    comment '系统资源权限';

create table system_role
(
    id char(22) not null
        primary key,
    name char(40) not null,
    `desc` varchar(200) null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint name
        unique (name)
)
    comment '系统角色';

create table system_role_authority
(
    role_id char(22) not null,
    authority_id char(22) not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    primary key (role_id, authority_id)
)
    comment '系统角色权限';

create table system_user
(
    id char(22) not null
        primary key,
    username varchar(30) not null,
    password char(60) not null,
    enabled tinyint(1) default 1 not null,
    locked tinyint(1) default 0 not null,
    account_expire datetime null,
    credential_expire datetime null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint username
        unique (username)
)
    comment '系统管理员';

create table system_user_profile
(
    id char(22) not null
        primary key,
    name varchar(30) default '' not null,
    avatar varchar(50) default '' null,
    gender tinyint(1) default 0 not null comment '0:未知,1:男性,2:女性',
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint real_name
        unique (name)
)
    comment '系统管理员配置';

create table system_user_role
(
    system_user_id char(22) not null,
    role_id char(22) not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    primary key (system_user_id, role_id)
)
    comment '系统管理员角色';

create table vision_activity
(
    id char(22) not null
        primary key,
    name varchar(50) not null comment '活动名称',
    address varchar(200) not null comment '活动举办地址',
    begin_date datetime not null comment '活动开始时间',
    end_date datetime not null comment '活动终止时间',
    content tinytext not null comment '活动内容详情',
    contact_man varchar(30) null comment '联系人',
    contact_phone_number varchar(50) null comment '联系电话',
    remark varchar(200) null comment '活动备注',
    archived tinyint(1) default 0 not null,
    modified_time datetime null,
    created_time datetime default CURRENT_TIMESTAMP not null
)
    comment '视力检测活动';

create table vision_activity_client
(
    vision_activity_id char(22) not null,
    vision_member_id char(22) null,
    vision_client_id char(22) not null,
    enabled tinyint(1) default 0 not null comment '激活',
    created_time datetime default CURRENT_TIMESTAMP not null,
    primary key (vision_activity_id, vision_client_id)
)
    comment '视力检测活动用户';

create table vision_activity_client_check_record
(
    vision_client_id char(22) not null,
    vision_activity_id char(22) not null,
    vision_check_record_id char(22) not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    primary key (vision_client_id, vision_activity_id, vision_check_record_id)
)
    comment '视力检测活动检测报告';

create table vision_check_record
(
    id char(22) not null comment '主键id'
        primary key,
    vision_client_id char(22) not null,
    eye_type char(2) not null comment '左右眼：OD-左眼，OS-右眼',
    check_date datetime not null comment '检测日期',
    data_type tinyint(1) null comment '数据类型：0x01-数字，0x02-图像，图像只有一幅，图像类型时，左右眼标识忽略',
    picture_file varchar(100) null comment '图片文件名称',
    pupil decimal(5,1) not null comment '瞳孔大小',
    se1 decimal(5,2) not null comment 'CYL-等效球面度',
    ds1 decimal(5,2) not null comment 'CYL-球面度',
    dc1 decimal(5,2) not null comment 'CYL-柱面度',
    axis1 int not null comment 'CYL-柱面轴位角',
    se2 decimal(5,2) not null comment 'CYL+等效球面度',
    ds2 decimal(5,2) not null comment 'CYL+球面度',
    dc2 decimal(5,2) not null comment 'CYL+柱面度',
    axis2 int not null comment 'CYL+柱面轴位角',
    pd int not null comment '瞳距',
    mm_hg decimal(5,1) not null comment '眼压',
    gaze_h int not null comment '水平固视',
    gaze_v int not null comment '垂直固视',
    remark varchar(300) null comment '备注',
    enabled tinyint(1) default 1 not null comment '数据有效性：1=有效；0=无效',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    modified_time datetime null comment '修改时间'
);

create table vision_client
(
    id char(22) not null
        primary key,
    wx_client_id char(22) null comment '微信注册用户',
    name varchar(30) not null comment '姓名',
    gender tinyint(1) default 0 not null comment '0:未知,1:男性,2:女性',
    age int default -1 not null comment '-1: 年龄保密',
    id_number varchar(18) not null comment '身份证号',
    native_place varchar(30) null comment '籍贯',
    height decimal(6,2) default 0.00 null comment '身高',
    weight decimal(6,2) null comment '体重',
    birthday date null comment '生日',
    phone_number varchar(30) null comment '手机号',
    province varchar(50) null comment '省份',
    city varchar(50) null comment '城市',
    county varchar(50) null comment '区县',
    detail_address varchar(50) null comment '详细地址',
    vision_acuity_left decimal(2,1) default 0.0 null comment '裸眼视力-左',
    vision_acuity_right decimal(2,1) default 0.0 null comment '裸眼视力-右',
    vision_acuity decimal(2,1) default 0.0 null comment '裸眼视力',
    diopters_left int default 0 null comment '屈光度-左',
    diopters_right int default 0 null comment '屈光度-右',
    astigmatism_left int default 0 null comment '散光度-左',
    astigmatism_right int default 0 null comment '散光度_右',
    joint_luminosity_left int default 0 null comment '联合光度-左',
    joint_luminosity_right int default 0 null comment '联合光度-右',
    axis_left int default 0 null comment '轴位-左',
    axis_right int default 0 null comment '轴位-右',
    pupil_distance int default 0 null comment '瞳距',
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint vision_client_id_number_uindex
        unique (id_number)
)
    comment '睛锐客户';

create table vision_school
(
    id char(22) default '' not null
        primary key,
    name varchar(30) default '' not null,
    province varchar(30) null,
    county varchar(30) null,
    city varchar(30) null,
    detail_address varchar(60) null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null,
    constraint vision_school_name_uindex
        unique (name)
);

create table vision_school_class
(
    id char(22) default '' not null
        primary key,
    vision_school_id char(22) default '' not null,
    name varchar(30) default '' not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null
);

create table vision_school_class_member
(
    id char(22) default '' not null
        primary key,
    vision_class_id char(22) default '' not null,
    student_number varchar(20) default '' not null,
    vision_client_id char(22) default '' not null,
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null
);

create table wx_client
(
    id char(22) not null
        primary key,
    public_id int null comment '小程序id',
    app_id varchar(100) null,
    open_id varchar(100) null comment '微信openId',
    name varchar(20) null comment '姓名',
    wx_nick varchar(50) null comment '微信昵称',
    avatar_image varchar(255) null comment '微信头像',
    gender tinyint(1) default 0 not null comment '性别,0:未知,1:男,2:女',
    age int default -1 not null comment '-1: 年龄保密',
    birthday date null comment '生日',
    id_number varchar(18) null comment '身份证号',
    province varchar(50) null comment '省份',
    city varchar(50) null comment '城市',
    county varchar(50) null comment '区县',
    detail_address varchar(255) null comment '详细地址',
    created_time datetime default CURRENT_TIMESTAMP not null,
    modified_time datetime null
);

-- 插入初始数据（用户，权限，角色）k