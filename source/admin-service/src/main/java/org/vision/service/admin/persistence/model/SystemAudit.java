package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SystemAudit implements Serializable {
    private Integer id;

    private String userId;

    private String realName;

    private String action;

    private String parameters;

    private Date executionTime;

    private static final long serialVersionUID = 1L;
}