package org.vision.service.admin.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.vision.service.admin.controller.criteria.VisionCheckRecordCriteria;
import org.vision.service.admin.persistence.mapper.VisionCheckRecordMapper;
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
      public String getId() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getId();
      }

      @Override
      public String getVisionClientId() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getVisionClientId();
      }

      @Override
      public String getEyeType() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getEyeType();
      }

      @Override
      public Date getCheckDate() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getCheckDate();
      }

      @Override
      public Boolean getDataType() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getDataType();
      }

      @Override
      public String getPictureFile() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getPictureFile();
      }

      @Override
      public BigDecimal getPupil() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getPupil();
      }

      @Override
      public BigDecimal getSe1() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getSe1();
      }

      @Override
      public BigDecimal getDs1() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getDs1();
      }

      @Override
      public BigDecimal getDc1() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getDc1();
      }

      @Override
      public Integer getAxis1() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getAxis1();
      }

      @Override
      public BigDecimal getSe2() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getSe2();
      }

      @Override
      public BigDecimal getDs2() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getDs2();
      }

      @Override
      public BigDecimal getDc2() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getDc2();
      }

      @Override
      public Integer getAxis2() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getAxis2();
      }

      @Override
      public Integer getPd() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getPd();
      }

      @Override
      public BigDecimal getMmHg() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getMmHg();
      }

      @Override
      public Integer getGazeH() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getGazeH();
      }

      @Override
      public Integer getGazeV() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getGazeV();
      }

      @Override
      public String getRemark() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getRemark();
      }

      @Override
      public Boolean getEnabled() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getEnabled();
      }

      @Override
      public Date getCreateTime() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getCreateTime();
      }

      @Override
      public Date getModifiedTime() {
        // TODO Auto-generated method stub
        return visionCheckRecordClientView.getVisionCheckRecord().getModifiedTime();
      }

      
  }
}
