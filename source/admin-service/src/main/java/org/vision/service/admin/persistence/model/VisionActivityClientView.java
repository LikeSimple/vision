package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VisionActivityClientView extends VisionClientView implements Serializable {

    private VisionActivity visionActivity;

    private VisionActivityClient visionActivityClient;

    private static final long serialVersionUID = 7696842126909447626L;
}
