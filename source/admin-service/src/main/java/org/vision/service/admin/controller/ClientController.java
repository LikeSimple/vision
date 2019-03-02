package org.vision.service.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.VisionClientCriteria;
import org.vision.service.admin.service.ClientService;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;
import org.vision.service.admin.service.vo.VisionClientVO;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * 获取客户列表（学生）
     * @param visionClientCriteria
     * @param pageSize
     * @param pageNum
     * @return
     */
    @PostMapping("/list")
    public ResponseData<List<? extends VisionClientVO>> getList(
            VisionClientCriteria visionClientCriteria,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

        return new ResponseData<>(clientService.getList(visionClientCriteria, pageSize, pageNum));
    }

    /**
     * 获取指定客户的视力筛查记录
     * @param clientId
     * @return
     */
    @PostMapping("/{clientId}/report/list")
    public ResponseData<List<? extends VisionCheckRecordVO>> getCheckReportList(@PathVariable("clientId") String clientId) {
        return new ResponseData<>(clientService.getCheckRecordList(clientId));
    }

}
