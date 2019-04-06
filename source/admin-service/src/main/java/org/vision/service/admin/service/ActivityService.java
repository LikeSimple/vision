package org.vision.service.admin.service;

import org.springframework.web.multipart.MultipartFile;
import org.vision.service.admin.controller.criteria.ActivityCriteria;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.service.vo.VisionActivityClientCheckRecordVO;
import org.vision.service.admin.service.vo.VisionActivityClientVO;
import org.vision.service.admin.service.vo.VisionActivityVO;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ActivityService {

    List<? extends VisionActivityVO> selectByCriteria(ActivityCriteria activityCriteria, int pageSize, int pageNum);

    VisionActivityVO selectById(String activityId);

    VisionActivityVO create(ActivityParam activityParam);

    void modify(String activityId, ActivityParam activityParam);

    Boolean archive(String activityId);

    Boolean unarchive(String activityId);

    List<? extends VisionActivityClientVO> getClientList(String activityId, int pageSize, int pageNum);

    VisionActivityClientVO newClient(String activityId, String clientId);

    Boolean deleteClient(String activityId, String clientId);

    List<? extends VisionActivityClientVO> importClientList(String activityId, MultipartFile multipartFile) throws IOException;

    List<? extends VisionActivityClientCheckRecordVO> importClientCheckRecord(String activityId, MultipartFile file) throws ParseException, IOException;

    List<? extends VisionActivityClientCheckRecordVO> getActivityClientCheckRecordList(String activityId, int pageSize, int pageNum);
}
