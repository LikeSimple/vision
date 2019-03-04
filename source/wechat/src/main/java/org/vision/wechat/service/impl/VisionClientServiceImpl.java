package org.vision.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vision.wechat.common.IDUtils;
import org.vision.wechat.common.ResponseData;
import org.vision.wechat.common.SysResponseEnum;
import org.vision.wechat.model.RelationBO;
import org.vision.wechat.persistence.mapper.VisionClientPOMapper;
import org.vision.wechat.persistence.mapper.WxClientVisionClientPOMapper;
import org.vision.wechat.persistence.model.VisionClientPO;
import org.vision.wechat.persistence.model.VisionClientPOExample;
import org.vision.wechat.persistence.model.WxClientVisionClientPO;
import org.vision.wechat.persistence.model.WxClientVisionClientPOExample;
import org.vision.wechat.service.VisionClientService;

@Service
public class VisionClientServiceImpl implements VisionClientService {

  @Autowired
  private VisionClientPOMapper visionClientPOMapper;
  
  @Autowired
  private WxClientVisionClientPOMapper wxClientVisionClientPOMapper;

  @Override
  public ResponseData<List<VisionClientPO>> list(String wxClientId) {
    ResponseData<List<VisionClientPO>> responseData = new ResponseData<>();
    
    WxClientVisionClientPOExample exampleRelation = new WxClientVisionClientPOExample();
    exampleRelation.createCriteria().andWxClientIdEqualTo(wxClientId);
    List<WxClientVisionClientPO> listRelation  = wxClientVisionClientPOMapper.selectByExample(exampleRelation);
    
    List<String> idList = new ArrayList<>();
    for (WxClientVisionClientPO po : listRelation) {
      idList.add(po.getVisionClientId());
    }
    
    if (idList.isEmpty()) {
      responseData.setData(new ArrayList<>());
      return responseData;
    }
    
    VisionClientPOExample example = new VisionClientPOExample();
    example.createCriteria().andIdIn(idList);
    List<VisionClientPO> list = visionClientPOMapper.selectByExample(example);
    
    responseData.setData(list);
    return responseData;
  }

  @Override
  public ResponseData<Object> relation(RelationBO bo) {
    ResponseData<Object> responseData = new ResponseData<>();
    
    String name = bo.getName();
    String idNumber = bo.getIdNumber();
    
    VisionClientPOExample exampleExist = new VisionClientPOExample();
    exampleExist.createCriteria().andNameEqualTo(name).andIdNumberEqualTo(idNumber);
    List<VisionClientPO> listExist = visionClientPOMapper.selectByExample(exampleExist);
    
    if (listExist == null || listExist.isEmpty()) {
      responseData.setCode(SysResponseEnum.VISION_CLIENT_NO_EXIST.getCode());
      responseData.setMessage(SysResponseEnum.VISION_CLIENT_NO_EXIST.getMessage());
      return responseData;
    }
    
    String visionClientId = listExist.get(0).getId();
    String wxClientId = bo.getWxClientId();
    
    WxClientVisionClientPOExample example = new WxClientVisionClientPOExample();
    example.createCriteria().andVisionClientIdEqualTo(visionClientId)
      .andWxClientIdEqualTo(wxClientId);
    long count = wxClientVisionClientPOMapper.countByExample(example);
    if (count > 0) {
      responseData.setCode(SysResponseEnum.VISION_CLIENT_RELATIONED.getCode());
      responseData.setMessage(SysResponseEnum.VISION_CLIENT_RELATIONED.getMessage());
      return responseData;
    }
    
    WxClientVisionClientPO record = new WxClientVisionClientPO();
    record.setId(IDUtils.getId());
    record.setVisionClientId(visionClientId);
    record.setWxClientId(wxClientId);
    
    wxClientVisionClientPOMapper.insertSelective(record);

    return responseData;
  }

  

}
