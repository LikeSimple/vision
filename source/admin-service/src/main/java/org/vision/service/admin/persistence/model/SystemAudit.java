package org.vision.service.admin.persistence.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class SystemAudit implements Serializable {
    private Integer id;

    private String userId;

    private String name;

    private String action;

    private String parameters;

    private Date executionTime;

    private static final long serialVersionUID = 7313644307059812157L;
}