package org.vision.wechat.service;

import org.vision.wechat.common.ResponseData;
import org.vision.wechat.model.CheckRecordGetListBO;
import org.vision.wechat.model.CheckRecordListBO;
import org.vision.wechat.model.CheckRecordVO;

import com.github.pagehelper.PageInfo;

public interface CheckRecordPOService {
  
  ResponseData<PageInfo<CheckRecordListBO>> list(CheckRecordGetListBO bo);
  
  ResponseData<CheckRecordVO> find(String activityId, String clientId);
  
  
}
