package org.vision.service.admin.service.impl;

import org.springframework.stereotype.Service;
import org.vision.service.admin.controller.criteria.VisionClientCriteria;
import org.vision.service.admin.service.ClientService;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;
import org.vision.service.admin.service.vo.VisionClientVO;

import java.util.List;

@Service("ClientService")
public class ClientServiceImpl implements ClientService {
    @Override
    public List<? extends VisionClientVO> getList(VisionClientCriteria visionClientCriteria, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<? extends VisionCheckRecordVO> getCheckReportList(String clientId) {
        return null;
    }
}
