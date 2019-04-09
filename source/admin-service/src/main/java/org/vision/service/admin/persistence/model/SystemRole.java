package org.vision.service.admin.persistence.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
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
  private static final long serialVersionUID = 9103706986859719016L;

}