CREATE DATABASE IF NOT EXISTS vision;

USE vision;

# 系统管理员
CREATE TABLE IF NOT EXISTS system_admin(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `username` VARCHAR(30) NOT NULL UNIQUE,
  `password` CHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `locked` TINYINT(1) NOT NULL DEFAULT 0,
  `account_expire` DATETIME DEFAULT NULL,
  `credential_expire` DATETIME DEFAULT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` DATETIME DEFAULT NULL
) COMMENT '系统管理员';

# 系统管理员初始化信息
INSERT INTO system_admin (`id`, `username`, `password`) VALUES ('3cngvbN1pfxVfsXsGMfmhJ', 'admin', '$2a$04$sq.FyqIPYhf4y.N5gZ0g7ee6HpAMbvlsjQdhPTOZppT4lLvtqefb2');

# 系统管理员配置
CREATE TABLE IF NOT EXISTS system_admin_profile(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `real_name` VARCHAR(30) NOT NULL UNIQUE DEFAULT '',
  `avatar_image` VARCHAR(50) DEFAULT '',
  `gender` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0:未知,1:男性,2:女性',
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` DATETIME DEFAULT NULL
) COMMENT '系统管理员配置';

# 系统管理员配置初始化信息
INSERT INTO system_admin_profile(`id`, `real_name`, `gender`) VALUES ('3cngvbN1pfxVfsXsGMfmhJ', '管理员', 1);

# 系统权限
CREATE TABLE IF NOT EXISTS system_authority(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `name` VARCHAR(40) NOT NULL UNIQUE,
  `desc` VARCHAR(200) DEFAULT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` DATETIME DEFAULT NULL
) COMMENT '系统权限';

# 系统角色
CREATE TABLE IF NOT EXISTS system_role(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `name` CHAR(40) NOT NULL UNIQUE,
  `desc` VARCHAR(200) DEFAULT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` DATETIME DEFAULT NULL
) COMMENT '系统角色';

# 系统角色权限
CREATE TABLE IF NOT EXISTS system_role_authority(
  `role_id` CHAR(22) NOT NULL,
  `authority_id` CHAR(22) NOT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `pk_role_authority` PRIMARY KEY (`role_id`, `authority_id`)
) COMMENT '系统角色权限';

# 系统管理员角色
CREATE TABLE IF NOT EXISTS system_admin_role(
  `admin_id` CHAR(22) NOT NULL,
  `role_id` CHAR(22) NOT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `pk_admin_role` PRIMARY KEY (`admin_id`, `role_id`)
) COMMENT '系统管理员角色';

# 系统资源
CREATE TABLE IF NOT EXISTS system_resource(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `name` VARCHAR(30) NOT NULL UNIQUE,
  `serial_key` VARCHAR(6) NOT NULL UNIQUE,
  `url` VARCHAR(100) NOT NULL,
  `icon` VARCHAR(40) DEFAULT NULL,
  `remark` VARCHAR(100) NOT NULL DEFAULT '',
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_time` DATETIME DEFAULT NULL
) COMMENT '系统资源';

# 系统资源权限
CREATE TABLE IF NOT EXISTS system_resource_authority(
  `resource_id` CHAR(22) NOT NULL,
  `authority_id` CHAR(22) NOT NULL,
  `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT `pk_system_resource_authority` PRIMARY KEY (resource_id, authority_id)
) COMMENT '系统资源权限';

# 系统操作审计
CREATE TABLE IF NOT EXISTS system_audit(
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id CHAR(22) NOT NULL,
  real_name VARCHAR(30) NOT NULL,
  action VARCHAR(200) NOT NULL,
  parameters VARCHAR(500) DEFAULT NULL,
  execution_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) COMMENT '系统操作审计';

# 微信注册用户
CREATE TABLE IF NOT EXISTS wx_client(
  id CHAR(22) NOT NULL PRIMARY KEY,
  public_id int COMMENT '小程序id',
  app_id VARCHAR(100) DEFAULT NULL,
  open_id VARCHAR(100) DEFAULT NULL COMMENT '微信openId',
  name VARCHAR(20) DEFAULT NULL COMMENT '姓名',
  wx_nick VARCHAR(50) DEFAULT NULL COMMENT '微信昵称',
  avatar_image VARCHAR(255) DEFAULT NULL COMMENT '微信头像',
  gender TINYINT(1) NOT NULL DEFAULT 0 COMMENT '性别,0:未知,1:男,2:女',
  age INT NOT NULL DEFAULT -1 COMMENT '-1: 年龄保密',
  birthday DATE DEFAULT NULL COMMENT '生日',
  id_number VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
  province VARCHAR(50) DEFAULT NULL COMMENT '省份',
  city VARCHAR(50) DEFAULT NULL COMMENT '城市',
  county VARCHAR(50) DEFAULT NULL COMMENT '区县',
  detail_address VARCHAR(255) COMMENT '详细地址'
);

# 睛锐客户（被检查用户）
CREATE TABLE IF NOT EXISTS vision_client(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `wx_client_id` CHAR(22) DEFAULT NULL COMMENT '微信注册用户',
  `name` VARCHAR(30) NOT NULL COMMENT '姓名',
  `age` INT NOT NULL DEFAULT -1 COMMENT '-1: 年龄保密',
  `birthday` DATE DEFAULT NULL COMMENT '生日',
  `phone_number` VARCHAR(30) DEFAULT NULL,
  `province` VARCHAR(50) DEFAULT NULL COMMENT '省份',
  `city` VARCHAR(50) DEFAULT NULL COMMENT '城市',
  `county` VARCHAR(50) DEFAULT NULL COMMENT '区县',
  `detail_address` VARCHAR(50) DEFAULT NULL COMMENT '详细地址',
  `gender` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0:未知,1:男性,2:女性',
  `id_number` VARCHAR(18) NOT NULL COMMENT '身份证号'
) COMMENT '睛锐客户';

# 视力检测活动
CREATE TABLE IF NOT EXISTS vision_activity(
  `id` CHAR(22) NOT NULL PRIMARY KEY,
  `name` VARCHAR(50) NOT NULL COMMENT '活动名称',
  `address` VARCHAR(200) NOT NULL COMMENT '活动举办地址',
  `begin_date` DATETIME NOT NULL COMMENT '活动开始时间',
  `end_date` DATETIME NOT NULL COMMENT '活动终止时间',
  `content` TINYTEXT NOT NULL COMMENT '活动内容详情',
  `contact_man` VARCHAR(30) COMMENT '联系人',
  `contact_phone_number` VARCHAR(50) COMMENT '联系电话',
  `remark` VARCHAR(200) COMMENT '活动备注'
) COMMENT '视力检测活动';

# 视力检测活动用户
CREATE TABLE IF NOT EXISTS vision_activity_client(
  `vision_client_id` CHAR(22) NOT NULL,
  `vision_activity_id` CHAR(22) NOT NULL,
  `enabled` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '激活',
  CONSTRAINT `pk_vision_activity_client` PRIMARY KEY ( vision_activity_id, vision_client_id)
) COMMENT '视力检测活动用户';

# 视力检测活动检测报告
CREATE TABLE IF NOT EXISTS vision_activity_client_check_report(
  `vision_client_id` CHAR(22) NOT NULL,
  `vision_activity_id` CHAR(22) NOT NULL,
  `vision_check_report_id` CHAR(22) NOT NULL,
  CONSTRAINT `pk_vision_activity_client_check_report` PRIMARY KEY (vision_client_id, vision_activity_id, vision_check_report_id)
) COMMENT '视力检测活动检测报告';

# 视力检查场所（固定）
# CREATE TABLE IF NOT EXISTS vision_check_clinic;

# 视力检查任务（申请和审批），关联被检查用户
# CREATE TABLE IF NOT EXISTS vision_task;

# 视力检查任务检测报告
# CREATE TABLE IF NOT EXISTS vision_task_client_check_report;

# 视力检查报告
CREATE TABLE IF NOT EXISTS vision_check_report(
id CHAR(22) NOT NULL PRIMARY KEY COMMENT '主键id',
eye_type CHAR(2) NOT NULL COMMENT '左右眼：OD-左眼，OS-右眼',
check_date DATETIME NOT NULL COMMENT '检测日期',
data_type TINYINT(1) NULL COMMENT '数据类型：0x01-数字，0x02-图像，图像只有一幅，图像类型时，左右眼标识忽略',
picture_file VARCHAR(100) NULL COMMENT '图片文件名称',
pupil DECIMAL(5, 1) NOT NULL COMMENT '瞳孔大小',
se1 DECIMAL(5, 2) NOT NULL COMMENT 'CYL-等效球面度',
ds1 DECIMAL(5, 2) NOT NULL COMMENT 'CYL-球面度',
dc1 DECIMAL(5, 2) NOT NULL COMMENT 'CYL-柱面度',
axis1 INT NOT NULL COMMENT 'CYL-柱面轴位角',
se2 DECIMAL(5, 2) NOT NULL COMMENT 'CYL+等效球面度',
ds2 DECIMAL(5, 2) NOT NULL COMMENT 'CYL+球面度',
dc2 DECIMAL(5, 2) NOT NULL COMMENT 'CYL+柱面度',
axis2 INT NOT NULL COMMENT 'CYL+柱面轴位角',
pd INT NOT NULL COMMENT '瞳距',
mm_hg DECIMAL(5, 1) NOT NULL COMMENT '眼压',
gaze_h INT NOT NULL COMMENT '水平固视',
gaze_v INT NOT NULL COMMENT '垂直固视',
remark VARCHAR(300) NULL COMMENT '备注',
enabled TINYINT(1) NOT NULL DEFAULT 1 COMMENT '数据有效性：1=有效；0=无效',
create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
modified_time DATETIME NULL COMMENT '修改时间'
);
