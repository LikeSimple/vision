create table if not exists menu_authority
(
  menu_id      char(22) not null,
  authority_id char(22) not null,
  primary key (menu_id, authority_id)
)
  comment '资源权限';

create table if not exists sys_authority
(
  id            char(22)                           not null
    primary key,
  name          varchar(30)                        not null,
  `desc`        varchar(100)                       null,
  create_time   datetime default CURRENT_TIMESTAMP not null,
  modified_time datetime                           null
)
  comment '系统权限';

create table if not exists sys_menu
(
  id              char(22)                              not null
    primary key,
  name            varchar(30)                           not null,
  sequence_number varchar(10) default '0'               not null,
  url             varchar(200)                          not null,
  icon            varchar(200)                          null,
  remark          varchar(200)                          null,
  create_time     datetime    default CURRENT_TIMESTAMP not null,
  modified_time   datetime                              null,
  enabled         tinyint(1)  default 1                 not null
)
  comment '系统资源';

create table if not exists sys_user
(
  id                char(22)                             not null
    primary key,
  username          varchar(30)                          not null,
  password          char(80)                             not null,
  enabled           tinyint(1) default 1                 not null,
  locked            tinyint(1) default 0                 not null,
  account_expire    datetime                             null,
  credential_expire datetime                             null,
  create_time       datetime   default CURRENT_TIMESTAMP not null,
  modified_time     datetime                             null
)
  comment '系统用户';

create table if not exists sys_user_audit
(
  id             int auto_increment
    primary key,
  user_id        char(22)                           not null,
  username       varchar(30)                        not null,
  action         varchar(200)                       not null,
  parameters     varchar(500)                       null,
  execurion_time datetime default CURRENT_TIMESTAMP not null
)
  comment '用户操作审计表';

create table if not exists sys_user_authority
(
  user_id       char(22)                           not null,
  authority_id  char(22)                           not null,
  create_time   datetime default CURRENT_TIMESTAMP not null,
  modified_time datetime                           null,
  primary key (user_id, authority_id)
)
  comment '用户权限';

create table if not exists vision_acuity_tester
(
  id            char(22)                             not null comment '主键id'
    primary key,
  serial_number varchar(50)                          null comment '序列号',
  model         varchar(50)                          null comment '型号',
  type          varchar(20)                          null comment '类型：筛选仪，检测器',
  address       varchar(255)                         null comment '保管地址',
  remark        varchar(300)                         null comment '备注',
  create_time   datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
  modified_time datetime                             null comment '修改时间',
  enabled       tinyint(1) default 1                 null comment '可用性：1=有效；0=无效'
)
  comment '视力检测仪器表';

create table if not exists visual_check_report
(
  id                   char(22)                             not null comment '主键id'
    primary key,
  wx_user_id           char(22)                             not null comment '微信用户id',
  visual_check_task_id char(22)                             not null comment '视力检测任务id',
  eye_type             varchar(20)                          null comment '左右眼：OD-左眼，OS-右眼',
  check_date           datetime                             not null comment '检测日期',
  data_type            tinyint(1)                           null comment '数据类型：0x01-数字，0x02-图像，图像只有一幅，图像类型时，左右眼标识忽略',
  picture_file         varchar(100)                         null comment '图片文件名称',
  pupil                double(5, 1)                         null comment '瞳孔大小',
  se1                  double(5, 2)                         null comment 'CYL-等效球面度',
  ds1                  double(5, 2)                         null comment 'CYL-球面度',
  dc1                  double(5, 2)                         null comment 'CYL-柱面度',
  axis1                int                                  null comment 'CYL-柱面轴位角',
  se2                  double(5, 2)                         null comment 'CYL+等效球面度',
  ds2                  double(5, 2)                         null comment 'CYL+球面度',
  dc2                  double(5, 2)                         null comment 'CYL+柱面度',
  axis2                int                                  null comment 'CYL+柱面轴位角',
  pd                   int                                  null comment '瞳距',
  mm_hg                double(5, 1)                         null comment '眼压',
  gaze_h               int                                  null comment '水平固视',
  gaze_v               int                                  null comment '垂直固视',
  remark               varchar(300)                         null comment '备注',
  valid                tinyint(1) default 1                 not null comment '数据有效性：1=有效；0=无效',
  create_time          datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
  modified_time        datetime                             null comment '修改时间'
);

create table if not exists wx_public
(
  id                           char(22)                           not null comment '主键id'
    primary key,
  nick_name                    varchar(100)                       null comment '小程序昵称',
  appid                        varchar(100)                       null comment 'appid',
  qr_code_url                  varchar(255)                       null comment '二维码图片的url',
  head_img_url                 varchar(255)                       null comment '公众号头像',
  service_type                 char                               null comment '公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号',
  verify_type                  char(2)                            null comment '公众号认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证',
  authorizer_refresh_token     varchar(200)                       null comment '授权刷新码',
  authorizer_access_token      varchar(200)                       null comment '授权码',
  authorizer_access_token_time bigint                             null comment '授权码过期时间',
  jssdk_access_token           varchar(200)                       null comment 'jssdk token',
  alias                        varchar(50)                        null comment '授权方公众号所设置的微信号，可能为空',
  signature                    varchar(500)                       null comment '账号介绍',
  is_open_store                tinyint(1)                         null comment '是否开通微信门店功能',
  is_open_scan                 tinyint(1)                         null comment '是否开通微信扫商品功能',
  is_open_pay                  tinyint(1)                         null comment '是否开通微信支付功能',
  is_open_card                 tinyint(1)                         null comment '是否开通微信卡券功能',
  is_open_shake                tinyint(1)                         null comment '是否开通微信摇一摇功能',
  code                         varchar(100)                       null comment '授权方公众号所设置的微信号，可能为空',
  originid                     varchar(100)                       null comment '原始id',
  jssdk_access_token_time      bigint                             null comment 'jssdk token 过期时间',
  platform_appid               varchar(100)                       null comment '第三方应用appid',
  is_connect                   tinyint(1)                         null comment '是否授权',
  mini_program_info            varchar(1000)                      null comment '可根据这个字段判断是否为小程序类型授权',
  principal_name               varchar(100)                       null comment '公众号/小程序主体名称',
  remark                       varchar(300)                       null comment '备注',
  create_time                  datetime default CURRENT_TIMESTAMP null comment '创建时间',
  modified_time                datetime                           null comment '修改时间'
)
  comment '小程序表';

create table if not exists wx_user
(
  id                int auto_increment comment '主键id'
    primary key,
  public_id         int                                    null comment '小程序id',
  appid             varchar(100)                           null comment 'appid',
  open_id           varchar(100)                           null comment '微信openid',
  name              varchar(20)                            null comment '姓名',
  wx_nick           varchar(50) collate utf8mb4_unicode_ci null comment '微信昵称',
  head_portraits    varchar(255)                           null comment '微信头像',
  gender            varchar(2)                             null comment '性别,女：0 男：1',
  age               int                                    null comment '年龄',
  birth_day         datetime                               null comment '生日',
  id_number         varchar(30)                            null comment '身份证号',
  province          varchar(50)                            null comment '省份',
  city              varchar(50)                            null comment '城市',
  county            varchar(50)                            null comment '区县',
  detail_address    varchar(255)                           null comment '详细地址',
  guardian_name     varchar(20)                            null comment '监护人',
  guardian_relation varchar(2)                             null comment '监护关系：1-父亲，2-母亲，10-其他',
  guardian_phone    varchar(20)                            null comment '监护人手机号',
  guardian_wechat   varchar(30)                            null comment '监护人微信号',
  qr_code           varchar(255)                           null comment '二维码地址',
  registered        tinyint(1) default 0                   not null comment '是否注册：0-未注册，1-已注册',
  remark            varchar(300)                           null comment '备注',
  create_time       datetime   default CURRENT_TIMESTAMP   null comment '创建时间',
  modified_time     datetime                               null comment '修改时间',
  version           int        default 1                   null comment '版本号'
)
  comment '微信用户表';

create index open_id
  on wx_user (open_id);


