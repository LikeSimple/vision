package org.vision.service.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.service.RecordService;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class RecordController {

    private RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * 根据条件查找视力筛查记录
     * @param visionCheckRecordCriteria
     * @param pageSize
     * @param pageNum
     * @return
     */
    @PostMapping("/list")
    public ResponseData<List<? extends VisionCheckRecordVO>> getList(
            VisionCheckRecordCriteria visionCheckRecordCriteria,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

        return new ResponseData<>(recordService.getList(visionCheckRecordCriteria, pageSize, pageNum));
    }
}
