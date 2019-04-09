package org.vision.service.admin.persistence.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemUser implements Serializable {
    /**
     * 只读.
    . id
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "id", required = false,example = "")
    private String id;

    /**
     * 只读.
    . username
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "username", required = false,example = "")
    private String username;

    /**
     * 只读.
    . password
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "password", required = false,example = "")
    private String password;

    /**
     * 只读.
    . enabled
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "enabled", required = false,example = "")
    private Boolean enabled;

    /**
     * 只读.
    . locked
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "locked", required = false,example = "")
    private Boolean locked;

    /**
     * 只读.
    . account_expire
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "accountExpire", required = false,example = "")
    private Date accountExpire;

    /**
     * 只读.
    . credential_expire
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "credentialExpire", required = false,example = "")
    private Date credentialExpire;

    /**
     * 只读.
    . created_time
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "createdTime", required = false,example = "")
    private Date createdTime;

    /**
     * 只读.
    . modified_time
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    @io.swagger.annotations.ApiModelProperty(value = "",name = "modifiedTime", required = false,example = "")
    private Date modifiedTime;

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    private static final long serialVersionUID = -2845792307123914560L;

}