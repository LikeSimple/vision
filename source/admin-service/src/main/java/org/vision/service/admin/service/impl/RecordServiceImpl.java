package org.vision.service.admin.service.impl;

import org.springframework.stereotype.Service;
import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.service.RecordService;
import org.vision.service.admin.service.vo.VisionClientCheckRecordVO;

import java.util.List;

@Service("RecordService")
public class RecordServiceImpl implements RecordService {

    public RecordServiceImpl() {

    }

    // TODO

    @Override
    public List<? extends VisionClientCheckRecordVO> getClientCheckRecordList(VisionCheckRecordCriteria visionCheckRecordCriteria, int pageSize, int pageNum) {
        return null;
    }
}

