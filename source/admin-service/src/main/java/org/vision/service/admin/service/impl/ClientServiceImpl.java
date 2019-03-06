package org.vision.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.vision.service.admin.controller.criteria.VisionClientCriteria;
import org.vision.service.admin.persistence.mapper.VisionClientMapper;
import org.vision.service.admin.persistence.model.VisionClientView;
import org.vision.service.admin.service.ClientService;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;
import org.vision.service.admin.service.vo.VisionClientVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("ClientService")
public class ClientServiceImpl implements ClientService {

    private VisionClientMapper visionClientMapper;

    public ClientServiceImpl(VisionClientMapper visionClientMapper) {
        this.visionClientMapper = visionClientMapper;
    }

    @Override
    public List<? extends VisionClientVO> getList(VisionClientCriteria visionClientCriteria, int pageSize, int pageNum) {

        PageHelper.startPage(pageNum, pageSize);
        return visionClientMapper.selectByCriteria(visionClientCriteria).stream().map(VisionClientVOImpl::new).collect(Collectors.toList());
    }

    @Override
    public List<? extends VisionCheckRecordVO> getCheckRecordList(String clientId) {
        return null;
    }

    private class VisionClientVOImpl implements VisionClientVO {

        private VisionClientView visionClientView;

        private VisionClientVOImpl(VisionClientView visionClientView) {
            this.visionClientView = visionClientView;
        }

        @Override
        public String getSchoolName() {
            return visionClientView.getVisionSchool().getName();
        }

        @Override
        public String getClassName() {
            return visionClientView.getVisionSchoolClass().getName();
        }

        @Override
        public String getStudentNumber() {
            return visionClientView.getVisionSchoolClassMember().getStudentNumber();
        }

        @Override
        public String getClientId() {
            return visionClientView.getVisionClient().getId();
        }

        @Override
        public String getName() {
            return visionClientView.getVisionClient().getName();
        }

        @Override
        public Integer getGender() {
            return new Integer(visionClientView.getVisionClient().getGender());
        }

        @Override
        public Integer getAge() {
            return visionClientView.getVisionClient().getAge();
        }

        @Override
        public String getIdNumber() {
            return visionClientView.getVisionClient().getIdNumber();
        }

        @Override
        public String getNativePlace() {
            return visionClientView.getVisionClient().getNativePlace();
        }

        @Override
        public BigDecimal getHeight() {
            return visionClientView.getVisionClient().getHeight();
        }

        @Override
        public BigDecimal getWeight() {
            return visionClientView.getVisionClient().getWeight();
        }

        @Override
        public Date getBirthday() {
            return visionClientView.getVisionClient().getBirthday();
        }

        @Override
        public String getPhoneNumber() {
            return visionClientView.getVisionClient().getPhoneNumber();
        }

        @Override
        public String getProvince() {
            return visionClientView.getVisionClient().getProvince();
        }

        @Override
        public String getCity() {
            return visionClientView.getVisionClient().getCity();
        }

        @Override
        public String county() {
            return visionClientView.getVisionClient().getCounty();
        }

        @Override
        public String getDetailAddress() {
            return visionClientView.getVisionClient().getDetailAddress();
        }

        @Override
        public BigDecimal getVisionAcuityLeft() {
            return visionClientView.getVisionClient().getVisionAcuityLeft();
        }

        @Override
        public BigDecimal getVisionAcuityRight() {
            return visionClientView.getVisionClient().getVisionAcuityRight();
        }

        @Override
        public BigDecimal getVisionAcuity() {
            return visionClientView.getVisionClient().getVisionAcuity();
        }

        @Override
        public Integer getDioptersLeft() {
            return visionClientView.getVisionClient().getDioptersLeft();
        }

        @Override
        public Integer getDioptersRight() {
            return visionClientView.getVisionClient().getDioptersRight();
        }

        @Override
        public Integer getAstigmatismLeft() {
            return visionClientView.getVisionClient().getAstigmatismLeft();
        }

        @Override
        public Integer getAstigmatismRight() {
            return visionClientView.getVisionClient().getAstigmatismRight();
        }

        @Override
        public Integer getJoinLuminosityLeft() {
            return visionClientView.getVisionClient().getJointLuminosityLeft();
        }

        @Override
        public Integer getJoinLuminosityRight() {
            return visionClientView.getVisionClient().getJointLuminosityRight();
        }

        @Override
        public Integer getAxisLeft() {
            return visionClientView.getVisionClient().getAxisLeft();
        }

        @Override
        public Integer getAxisRight() {
            return visionClientView.getVisionClient().getAxisRight();
        }

        @Override
        public Integer getPupilDistance() {
            return visionClientView.getVisionClient().getPupilDistance();
        }
    }
}
