package org.vision.service.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.ActivityCriteria;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.service.ActivityService;
import org.vision.service.admin.service.vo.VisionActivityClientVO;
import org.vision.service.admin.service.vo.VisionActivityVO;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * 根据查询条件获取活动列表，按照时间顺序倒序排列
     * @param activityCriteria
     * @param pageSize
     * @param pageNum
     * @return
     */
    @PostMapping("/list")
    public ResponseData<List<? extends VisionActivityVO>> getList(
            ActivityCriteria activityCriteria,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        return new ResponseData<>(activityService.selectByCriteria(activityCriteria, pageSize, pageNum));
    }

    /**
     * 创建一个活动
     * @param activityParam
     * @return
     */
    @PostMapping(value = "/new")
    public ResponseData<VisionActivityVO> create(@RequestBody ActivityParam activityParam) {
        return new ResponseData<>(activityService.create(activityParam));
    }

    /**
     * 修改一个活动
     * @param activityParam
     * @return
     */
    @PostMapping("/{activityId}/modify")
    public ResponseData<VisionActivityVO> modify(@PathVariable("activityId") String activityId, @RequestBody ActivityParam activityParam) {
        activityService.modify(activityId, activityParam);
        return new ResponseData<>(activityService.selectById(activityId));
    }

    /**
     * 归档一个活动
     * @param activityId
     * @return
     */
    @PostMapping("/{activityId}/archive")
    public ResponseData<Boolean> archive(@PathVariable("activityId") String activityId) {
        return new ResponseData<>(activityService.archive(activityId));
    }

    /**
     * 恢复一个活动
     * @param activityId
     * @return
     */
    @PostMapping("/{activityId}/unarchive")
    public ResponseData<Boolean> unarchive(@PathVariable("activityId") String activityId) {
        return new ResponseData<>(activityService.unarchive(activityId));
    }

    /**
     * 获取活动用户列表（学校学生）
     * @param activityId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @PostMapping("/{activityId}/client/list")
    public ResponseData<List<? extends VisionActivityClientVO>> getClientList(
            @PathVariable("activityId") String activityId,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        return new ResponseData<>(activityService.getClientList(activityId, pageSize, pageNum));
    }

    /**
     * 导入活动用户列表（文件形式）
     * @param activityId
     * @param multipartFile
     * @return
     */
    @PostMapping("/{activityId}/client/import")
    public ResponseData<List<? extends VisionActivityClientVO>> importClientList(@PathVariable("activityId") String activityId, MultipartFile multipartFile) {
        return new ResponseData<>(activityService.importClientList(activityId, multipartFile));
    }

    /**
     * 添加活动用户（单个）
     * @param activityId
     * @param clientId
     * @return
     */
    @PostMapping("/{activityId}/client/{clientId}/new")
    public  ResponseData<VisionActivityClientVO> createClient(@PathVariable("activityId") String activityId, @PathVariable("clientId") String clientId) {
        return new ResponseData<>(activityService.newClient(activityId, clientId));
    }

    /**
     * 删除活动用户（单个）
     * @param activityId
     * @param clientId
     * @return
     */
    @PostMapping("/{activityId}/client/{clientId}/delete")
    public ResponseData<Boolean> deleteClient(@PathVariable("activityId") String activityId, @PathVariable("clientId") String clientId) {
        return new ResponseData<>(activityService.deleteClient(activityId, clientId));
    }

    /**
     * 导入活动用户视力筛查数据
     * @param activityId
     * @param multipartFile
     * @return
     */
    @PostMapping("/{activityId}/report/import")
    public ResponseData<List<? extends VisionCheckRecordVO>> importClientCheckReport(@PathVariable("activityId") String activityId, MultipartFile multipartFile) {
        return new ResponseData<>(activityService.importClientCheckReport(activityId, multipartFile));
    }
}