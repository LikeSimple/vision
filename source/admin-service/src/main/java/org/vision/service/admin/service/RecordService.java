package org.vision.service.admin.service;


import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;

import java.util.List;

public interface RecordService {

    List<? extends VisionCheckRecordVO> getList(VisionCheckRecordCriteria visionCheckRecordCriteria, int pageSize, int pageNum);
}
