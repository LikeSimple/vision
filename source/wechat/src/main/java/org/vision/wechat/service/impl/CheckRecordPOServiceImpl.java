package org.vision.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.model.CheckRecordGetListBO;
import org.vision.wechat.model.CheckRecordListBO;
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
  public ResponseData<VisionCheckRecordPO> find(String id) {
    VisionCheckRecordPO po = visionCheckRecordPOMapper.selectByPrimaryKey(id);
    
    ResponseData<VisionCheckRecordPO> responseData = new ResponseData<VisionCheckRecordPO>();
    responseData.setData(po);
    return responseData;
  }

}
