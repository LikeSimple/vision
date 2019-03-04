package org.vision.wechat.service;

import java.util.List;

import org.vision.wechat.common.ResponseData;
import org.vision.wechat.model.RelationBO;
import org.vision.wechat.persistence.model.VisionClientPO;

public interface VisionClientService {
  
  ResponseData<List<VisionClientPO>> list(String wxClientId);
  
  ResponseData<Object> relation(RelationBO bo);
  
  
}
