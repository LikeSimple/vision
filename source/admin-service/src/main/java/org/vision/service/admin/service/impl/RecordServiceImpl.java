package org.vision.service.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.vision.service.admin.common.SystemConstants;
import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.persistence.mapper.VisionCheckRecordMapper;
import org.vision.service.admin.persistence.model.VisionCheckRecord;
import org.vision.service.admin.persistence.model.VisionCheckRecordClientView;
import org.vision.service.admin.service.RecordService;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;

import com.github.pagehelper.PageHelper;

@Service("RecordService")
public class RecordServiceImpl implements RecordService {
  
    private VisionCheckRecordMapper visionCheckRecordMapper;
  
    public RecordServiceImpl(VisionCheckRecordMapper visionCheckRecordMapper) {
        this.visionCheckRecordMapper = visionCheckRecordMapper;
    }
    
    @Override
    public List<? extends VisionCheckRecordVO> getList(VisionCheckRecordCriteria visionCheckRecordCriteria, int pageSize, int pageNum) {
      PageHelper.startPage(pageNum, pageSize);
      return visionCheckRecordMapper.selectByCriteria(visionCheckRecordCriteria).stream().map(VisionCheckRecordVOImpl::new).collect(Collectors.toList());
    }
    
    @Override
    public List<? extends VisionCheckRecordVO> getActivityRecordList(
        String activityId, int pageSize, int pageNum) {
      PageHelper.startPage(pageNum, pageSize);
      return visionCheckRecordMapper.selectActivityRecordByCriteria(activityId).stream().map(VisionCheckRecordVOImpl::new).collect(Collectors.toList());
    }
    
    private class VisionCheckRecordVOImpl implements VisionCheckRecordVO {

      private VisionCheckRecordClientView visionCheckRecordClientView;

      private VisionCheckRecordVOImpl(VisionCheckRecordClientView visionCheckRecordClientView) {
          this.visionCheckRecordClientView = visionCheckRecordClientView;
      }

      @Override
      public String getSchoolName() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionSchool().getName();
      }

      @Override
      public String getClassName() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionSchoolClass().getName();
      }

      @Override
      public String getActivityName() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionActivity().getName();
      }
      
      @Override
      public String getName() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionClient().getName();
      }

      @Override
      public String getIdNumber() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionClient().getIdNumber();
      }

      @Override
      public VisionCheckRecord getLeftEye() {
        // TODO Auto-generated method stub
        List<VisionCheckRecord> list = visionCheckRecordClientView.getRecordList();
        for (VisionCheckRecord basic : list) {
          String eyeType = basic.getEyeType();
          if (SystemConstants.RECOARE_EYE_TYPE_LEFT.equals(eyeType)) {
            return basic;
          } 
        }
        return null;
      }

      @Override
      public VisionCheckRecord getRightEye() {
        // TODO Auto-generated method stub
        List<VisionCheckRecord> list = visionCheckRecordClientView.getRecordList();
        for (VisionCheckRecord basic : list) {
          String eyeType = basic.getEyeType();
          if (SystemConstants.RECOARE_EYE_TYPE_RIGHT.equals(eyeType)) {
            return basic;
          }
        }
        return null;
      }

      
  }

}
