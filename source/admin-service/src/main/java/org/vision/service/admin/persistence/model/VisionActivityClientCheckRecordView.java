package org.vision.service.admin.persistence.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class VisionActivityClientCheckRecordView extends VisionActivityClientView implements Serializable {

    private List<VisionCheckRecord> visionCheckRecordList;

}
