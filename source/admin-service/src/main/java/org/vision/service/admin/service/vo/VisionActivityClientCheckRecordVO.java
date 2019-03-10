package org.vision.service.admin.service.vo;

import java.util.List;

public interface VisionActivityClientCheckRecordVO extends VisionActivityClientVO {

    List<? extends VisionCheckRecordVO> getCheckRecordList();

}
