package org.vision.wechat.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table wx_client
 */
@Data
public class WxClientPO implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.id
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String id;

    /**
     * Database Column Remarks:
     *   微信openId
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.open_id
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String openId;

    /**
     * Database Column Remarks:
     *   姓名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.name
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String name;

    /**
     * Database Column Remarks:
     *   微信昵称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.wx_nick
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String wxNick;

    /**
     * Database Column Remarks:
     *   微信头像
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.avatar_image
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String avatarImage;

    /**
     * Database Column Remarks:
     *   性别,0:未知,1:男,2:女
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.gender
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String gender;

    /**
     * Database Column Remarks:
     *   -1: 年龄保密
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.age
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private Integer age;

    /**
     * Database Column Remarks:
     *   生日
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.birthday
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private Date birthday;

    /**
     * Database Column Remarks:
     *   身份证号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.id_number
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String idNumber;

    /**
     * Database Column Remarks:
     *   省份
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.province
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String province;

    /**
     * Database Column Remarks:
     *   城市
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.city
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String city;

    /**
     * Database Column Remarks:
     *   区县
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.county
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String county;

    /**
     * Database Column Remarks:
     *   详细地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_client.detail_address
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private String detailAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wx_client
     *
     * @mbg.generated Mon Mar 04 15:29:41 CST 2019
     */
    private static final long serialVersionUID = 1L;
}