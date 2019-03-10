package org.vision.service.admin.service.vo;

import java.util.List;

public interface VisionClientCheckRecordVO extends VisionClientVO {

    public List<? extends VisionCheckRecordVO> getCheckRecordList();
}
