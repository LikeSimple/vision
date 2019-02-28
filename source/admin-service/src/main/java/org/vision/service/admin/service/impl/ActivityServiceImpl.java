package org.vision.service.admin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.vision.service.admin.controller.criteria.ActivityCriteria;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.service.ActivityService;
import org.vision.service.admin.service.vo.VisionActivityClientVO;
import org.vision.service.admin.service.vo.VisionActivityVO;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;

import java.util.List;

@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {

    @Override
    public List<? extends VisionActivityVO> selectByCriteria(ActivityCriteria activityCriteria, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public VisionActivityVO create(ActivityParam activityParam) {
        return null;
    }

    @Override
    public VisionActivityVO modify(String activityId, ActivityParam activityParam) {
        return null;
    }

    @Override
    public Boolean archive(String activityId) {
        return null;
    }

    @Override
    public Boolean unarchive(String activityId) {
        return null;
    }

    @Override
    public List<? extends VisionActivityClientVO> getClientList(String activityId, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public VisionActivityClientVO newClient(String activityId, String clientId) {
        return null;
    }

    @Override
    public Boolean deleteClient(String activityId, String clientId) {
        return null;
    }

    @Override
    public List<? extends VisionActivityClientVO> importClientList(String activityId, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public List<? extends VisionCheckRecordVO> importClientCheckReport(String activityId, MultipartFile file) {
        return null;
    }
}
