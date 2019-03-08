package org.vision.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.common.SystemConstants;
import org.vision.wechat.model.CheckRecordGetListBO;
import org.vision.wechat.model.CheckRecordListBO;
import org.vision.wechat.model.CheckRecordVO;
import org.vision.wechat.persistence.mapper.VisionCheckRecordPOMapper;
import org.vision.wechat.persistence.model.VisionCheckRecordPO;
import org.vision.wechat.service.CheckRecordPOService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CheckRecordPOServiceImpl implements CheckRecordPOService {
  
  @Autowired
  private VisionCheckRecordPOMapper visionCheckRecordPOMapper;
  
  @Override
  public ResponseData<PageInfo<CheckRecordListBO>> list(CheckRecordGetListBO bo) {
    PageHelper.offsetPage(bo.getPageNum(), bo.getPageSize());
    
    List<CheckRecordListBO> list = visionCheckRecordPOMapper.list(bo);
    
    ResponseData<PageInfo<CheckRecordListBO>> responseData  = new ResponseData<>();
    responseData.setData(new PageInfo<>(list));
    
    return responseData;
  }

  @Override
  public ResponseData<CheckRecordVO> find(String activityId, String clientId) {
    ResponseData<CheckRecordVO> responseData = new ResponseData<>();
    CheckRecordVO vo = new CheckRecordVO();
    
    List<VisionCheckRecordPO> list = visionCheckRecordPOMapper.activityClientList(activityId, clientId);
    
    if (list == null || list.isEmpty()) {
      responseData.setData(vo);
      return responseData;
    }
    
    for (VisionCheckRecordPO po : list) {
      String eyeType = po.getEyeType();
      if (SystemConstants.RECOARE_EYE_TYPE_LEFT.equals(eyeType)) {
        vo.setLeftEye(po);
      } else if (SystemConstants.RECOARE_EYE_TYPE_RIGHT.equals(eyeType)) {
        vo.setRightEye(po);;
      }
    }
    
    responseData.setData(vo);

    return responseData;
  }

}
