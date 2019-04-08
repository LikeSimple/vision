package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;

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
    private static final long serialVersionUID = 1L;

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public String getId() {
        return id;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public String getUsername() {
        return username;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public String getPassword() {
        return password;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public Date getAccountExpire() {
        return accountExpire;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setAccountExpire(Date accountExpire) {
        this.accountExpire = accountExpire;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public Date getCredentialExpire() {
        return credentialExpire;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setCredentialExpire(Date credentialExpire) {
        this.credentialExpire = credentialExpire;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 只读. 
     *
     * @mbg.generated 2019-04-08 10:20:21
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}