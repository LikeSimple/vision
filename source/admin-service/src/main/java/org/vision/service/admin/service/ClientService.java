package org.vision.service.admin.service;

import org.vision.service.admin.controller.criteria.VisionClientCriteria;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;
import org.vision.service.admin.service.vo.VisionClientVO;

import java.util.List;

public interface ClientService {
    List<? extends VisionClientVO> getList(VisionClientCriteria visionClientCriteria, int pageSize, int pageNum);

    List<? extends VisionCheckRecordVO> getCheckReportList(String clientId);
}
