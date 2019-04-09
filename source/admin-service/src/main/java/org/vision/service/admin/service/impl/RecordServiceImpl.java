package org.vision.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.persistence.mapper.VisionCheckRecordMapper;
import org.vision.service.admin.service.RecordService;
import org.vision.service.admin.service.vo.VisionClientCheckRecordVO;

import java.util.List;

@Service("RecordService")
public class RecordServiceImpl implements RecordService {

    private VisionCheckRecordMapper visionCheckRecordMapper;

    public RecordServiceImpl(VisionCheckRecordMapper visionCheckRecordMapper) {

        this.visionCheckRecordMapper = visionCheckRecordMapper;
    }

    // TODO

    @Override
    public List<? extends VisionClientCheckRecordVO> getClientCheckRecordList(VisionCheckRecordCriteria visionCheckRecordCriteria, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);

        return null;
    }
}

