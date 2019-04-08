package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;

public class SystemRole implements Serializable {

  /**
   * 只读. . id
   * @mbg.generated  2019-04-08 10:57:31
   */
  @io.swagger.annotations.ApiModelProperty(value = "", name = "id", required = false, example = "")
  private String id;
  /**
   * 只读. . name
   * @mbg.generated  2019-04-08 10:57:31
   */
  @io.swagger.annotations.ApiModelProperty(value = "", name = "name", required = false, example = "")
  private String name;
  /**
   * 只读. . description
   * @mbg.generated  2019-04-08 10:57:31
   */
  @io.swagger.annotations.ApiModelProperty(value = "", name = "description", required = false, example = "")
  private String description;
  /**
   * 只读. . created_time
   * @mbg.generated  2019-04-08 10:57:31
   */
  @io.swagger.annotations.ApiModelProperty(value = "", name = "createdTime", required = false, example = "")
  private Date createdTime;
  /**
   * 只读. . modified_time
   * @mbg.generated  2019-04-08 10:57:31
   */
  @io.swagger.annotations.ApiModelProperty(value = "", name = "modifiedTime", required = false, example = "")
  private Date modifiedTime;
  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  private static final long serialVersionUID = 1L;

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public String getId() {
    return id;
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public void setId(String id) {
    this.id = id == null ? null : id.trim();
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public String getName() {
    return name;
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public String getDescription() {
    return description;
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public void setDescription(String description) {
    this.description = description == null ? null : description.trim();
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public Date getCreatedTime() {
    return createdTime;
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public Date getModifiedTime() {
    return modifiedTime;
  }

  /**
   * 只读. 
   * @mbg.generated  2019-04-08 10:57:31
   */
  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}