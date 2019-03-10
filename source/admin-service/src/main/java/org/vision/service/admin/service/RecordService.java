package org.vision.service.admin.service;


import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.service.vo.VisionClientCheckRecordVO;

import java.util.List;

public interface RecordService {

    List<? extends VisionClientCheckRecordVO> getClientCheckRecordList(VisionCheckRecordCriteria visionCheckRecordCriteria, int pageSize, int pageNum);

}
