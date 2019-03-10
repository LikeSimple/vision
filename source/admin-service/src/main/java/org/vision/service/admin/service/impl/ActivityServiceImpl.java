package org.vision.service.admin.service.impl;

import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.vision.service.admin.controller.criteria.ActivityCriteria;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.persistence.mapper.*;
import org.vision.service.admin.persistence.model.*;
import org.vision.service.admin.service.ActivityService;
import org.vision.service.admin.service.vo.VisionActivityClientCheckRecordVO;
import org.vision.service.admin.service.vo.VisionActivityClientVO;
import org.vision.service.admin.service.vo.VisionActivityVO;
import org.vision.service.admin.service.vo.VisionCheckRecordVO;
import org.vision.service.admin.util.PoiUtil;
import org.vision.service.admin.util.ShortUUIDGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service("ActivityService")
@Slf4j
public class ActivityServiceImpl implements ActivityService {

    private VisionActivityMapper activityMapper;

    private VisionActivityClientMapper activityClientMapper;

    private VisionActivityClientCheckRecordMapper activityClientCheckRecordMapper;

    private VisionClientMapper clientMapper;

    private VisionSchoolMapper schoolMapper;

    private VisionSchoolClassMapper schoolClassMapper;

    private VisionSchoolClassMemberMapper schoolClassMemberMapper;

    public ActivityServiceImpl(VisionActivityMapper activityMapper, VisionActivityClientMapper activityClientMapper, VisionActivityClientCheckRecordMapper activityClientCheckRecordMapper, VisionClientMapper clientMapper, VisionSchoolMapper schoolMapper, VisionSchoolClassMapper schoolClassMapper, VisionSchoolClassMemberMapper schoolClassMemberMapper) {
        this.activityMapper = activityMapper;
        this.activityClientMapper = activityClientMapper;
        this.activityClientCheckRecordMapper = activityClientCheckRecordMapper;
        this.clientMapper = clientMapper;
        this.schoolMapper = schoolMapper;
        this.schoolClassMapper = schoolClassMapper;
        this.schoolClassMemberMapper = schoolClassMemberMapper;
    }

    @Override
    public List<? extends VisionActivityVO> selectByCriteria(ActivityCriteria activityCriteria, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<VisionActivity> visionActivities = activityMapper.selectByCriteria(activityCriteria);
        return visionActivities.stream().map(VisionActivityVOImpl::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VisionActivityVO create(ActivityParam activityParam) {
        VisionActivity visionActivity = new VisionActivity(activityParam);
        Assert.isTrue(0 != activityMapper.insertSelective(visionActivity));
        return new VisionActivityVOImpl(visionActivity);
    }

    @Override
    @Transactional
    public void modify(String activityId, ActivityParam activityParam) {
        VisionActivity visionActivity = new VisionActivity(activityParam);
        visionActivity.setId(activityId);
        visionActivity.setModifiedTime(new Date());
        Assert.isTrue(0 != activityMapper.updateByPrimaryKeySelective(visionActivity));
    }

    @Override
    @Transactional
    public Boolean archive(String activityId) {
        return applyArchive(activityId, true);
    }

    @Override
    @Transactional
    public Boolean unarchive(String activityId) {
        return applyArchive(activityId, false);
    }


    private Boolean applyArchive(String activityId, boolean archived) {
        VisionActivity visionActivity = new VisionActivity();
        visionActivity.setId(activityId);
        visionActivity.setArchived(archived);
        visionActivity.setModifiedTime(new Date());
        return 0 != activityMapper.updateByPrimaryKeySelective(visionActivity);
    }

    @Override
    public List<? extends VisionActivityClientVO> getClientList(String activityId, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<VisionActivityClientView> visionActivityClientViews = activityClientMapper.selectByActivityId(activityId);
        return visionActivityClientViews.stream().map(VisionActivityClientVOImpl::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VisionActivityClientVO newClient(String activityId, String clientId) {
        VisionActivityClient visionActivityClient = new VisionActivityClient(activityId, clientId);
        Assert.isTrue(1 == activityClientMapper.insert(visionActivityClient));
        return new VisionActivityClientVOImpl(activityClientMapper.selectByClientId(clientId));
    }

    @Override
    @Transactional
    public Boolean deleteClient(String activityId, String clientId) {
        Assert.isTrue(1 == activityClientMapper.deleteByPrimaryKey(activityId, clientId));
        return true;
    }

    @Override
    @Transactional
    public List<? extends VisionActivityClientVO> importClientList(String activityId, MultipartFile multipartFile) throws IOException {
        final String[] keyList = new String[]{"姓名", "性别", "年龄", "身份证号", "籍贯", "学校", "班级", "学号", "身高", "体重",
                "裸眼视力左", "裸眼视力右", "裸眼视力双眼", "屈光度左", "屈光度右", "散光度左", "散光度右", "联合光度左", "联合光度右",
                "轴位左", "轴位右", "瞳距"};

        //用于查重（保存已有对象Id）
        HashMap<String, VisionClient> visionClientHashMap = new HashMap<>();
        HashMap<String, VisionSchool> visionSchoolHashMap = new HashMap<>();
        HashMap<String, VisionSchoolClass> visionSchoolClassHashMap = new HashMap<>();
        HashMap<String, VisionSchoolClassMember> visionSchoolClassMemberHashMap = new HashMap<>();

        //中间变量
        VisionClient visionClient;
        VisionSchool visionSchool;
        VisionSchoolClass visionSchoolClass;
        VisionSchoolClassMember visionSchoolClassMember;
        VisionActivityClient visionActivityClient;

        //读取Excel表格
        List<String[]> list = PoiUtil.readExcel(multipartFile);
        log.info(multipartFile.getName() + "加载成功！");

        //分析头部位置（可能乱序）
        HashMap<String, Integer> keys = new HashMap<>(keyList.length);
        for (String s : list.get(0)) {
            keys.put(s, indexOf(keyList, s));
        }
        log.info("文件头部分析成功！");

        //导入数据
        String tempKey; //临时Key变量
        boolean isNew; //临时新数据标志

        for (int i = 1; i < list.size(); i++) {

            //当前列表查重复（防止行重复）
            tempKey = list.get(i)[keys.get("身份证号")].trim();
            visionClient = visionClientHashMap.get(tempKey);
            if (null == visionClient) {

                isNew = false;
                // 列表数据没有重复，数据库进行查重
                visionClient = clientMapper.selectByIdNumber(tempKey);

                if (null == visionClient) {
                    // 数据库没有重复（新数据）
                    isNew = true; //标志该数据为新数据
                    visionClient = new VisionClient();
                    visionClient.setId(ShortUUIDGenerator.newID());
                    visionClient.setCreatedTime(new Date());
                }

                visionClient.setName(list.get(i)[keys.get("姓名")]);
                visionClient.setGender((list.get(i)[keys.get("性别")].equals("男") ? 1 : 2));
                visionClient.setAge(Integer.valueOf(list.get(i)[keys.get("年龄")]));
                visionClient.setIdNumber(list.get(i)[keys.get("身份证号")].trim());
                visionClient.setNativePlace(list.get(i)[keys.get("籍贯")]);
                visionClient.setHeight(BigDecimal.valueOf(Double.valueOf(list.get(i)[keys.get("身高")])));
                visionClient.setWeight(BigDecimal.valueOf(Double.valueOf(list.get(i)[keys.get("体重")])));
                visionClient.setVisionAcuityLeft(BigDecimal.valueOf(Double.valueOf(list.get(i)[keys.get("裸眼视力左")])));
                visionClient.setVisionAcuityRight(BigDecimal.valueOf(Double.valueOf(list.get(i)[keys.get("裸眼视力右")])));
                visionClient.setVisionAcuity(BigDecimal.valueOf(Double.valueOf(list.get(i)[keys.get("裸眼视力双眼")])));
                visionClient.setDioptersLeft(Integer.valueOf(list.get(i)[keys.get("屈光度左")]));
                visionClient.setDioptersRight(Integer.valueOf(list.get(i)[keys.get("屈光度右")]));
                visionClient.setAstigmatismLeft(Integer.valueOf(list.get(i)[keys.get("散光度左")]));
                visionClient.setAstigmatismRight(Integer.valueOf(list.get(i)[keys.get("散光度右")]));
                visionClient.setJointLuminosityLeft(Integer.valueOf(list.get(i)[keys.get("联合光度左")]));
                visionClient.setJointLuminosityRight(Integer.valueOf(list.get(i)[keys.get("联合光度右")]));
                visionClient.setAxisLeft(Integer.valueOf(list.get(i)[keys.get("轴位左")]));
                visionClient.setAxisRight(Integer.valueOf(list.get(i)[keys.get("轴位右")]));
                visionClient.setPupilDistance(Integer.valueOf(list.get(i)[keys.get("瞳距")]));
                if (isNew) {
                    Assert.isTrue(1 == clientMapper.insertSelective(visionClient));
                    log.info("保存新用户成功！");
                } else {
                    visionClient.setModifiedTime(new Date());
                    Assert.isTrue(1 == clientMapper.updateByPrimaryKey(visionClient));
                    log.info("用户数据更新成功！");
                }
                // 保持数据到列表
                visionClientHashMap.put(visionClient.getIdNumber(), visionClient);
            } else {
                //当前用户重复，不处理跳过
                printLine(i, list.get(i));
                continue;
            }

            //import School
            tempKey = list.get(i)[keys.get("学校")].trim();
            visionSchool = visionSchoolHashMap.get(tempKey);
            if (null == visionSchool) {

                //当前列表中没有重复，数据库查重
                visionSchool = schoolMapper.selectByName(tempKey);
                if (null == visionSchool) {
                    visionSchool = new VisionSchool();
                    visionSchool.setId(ShortUUIDGenerator.newID());
                    visionSchool.setName(list.get(i)[keys.get("学校")]);
                    visionSchool.setCreatedTime(new Date());
                    Assert.isTrue(1 == schoolMapper.insertSelective(visionSchool));
                }
                visionSchoolHashMap.put(tempKey, visionSchool);
            }

            //import School Class
            tempKey = list.get(i)[keys.get("班级")].trim();
            visionSchoolClass = visionSchoolClassHashMap.get(tempKey);
            if (null == visionSchoolClass) {
                visionSchoolClass = schoolClassMapper.selectByName(tempKey);
                if (null == visionSchoolClass) {
                    visionSchoolClass = new VisionSchoolClass();
                    visionSchoolClass.setId(ShortUUIDGenerator.newID());
                    visionSchoolClass.setName(list.get(i)[keys.get("班级")]);
                    visionSchoolClass.setVisionSchoolId(visionSchool.getId());
                    visionSchoolClass.setCreatedTime(new Date());
                    Assert.isTrue(1 == schoolClassMapper.insertSelective(visionSchoolClass));
                }
                visionSchoolClassHashMap.put(tempKey, visionSchoolClass);
            }

            //import School Class member
            tempKey = list.get(i)[keys.get("学号")];
            visionSchoolClassMember = visionSchoolClassMemberHashMap.get(tempKey);
            if (null == visionSchoolClassMember) {
                visionSchoolClassMember = schoolClassMemberMapper.selectByCombinedKeys(visionSchoolClass.getId(), visionClient.getId());
                isNew = false;
                if (null == visionSchoolClassMember) {
                    visionSchoolClassMember = new VisionSchoolClassMember();
                    visionSchoolClassMember.setId(ShortUUIDGenerator.newID());
                    visionSchoolClassMember.setCreatedTime(new Date());
                    isNew = true;
                }
                visionSchoolClassMember.setVisionClassId(visionSchoolClass.getId());
                visionSchoolClassMember.setStudentNumber(list.get(i)[keys.get("学号")]);
                visionSchoolClassMember.setVisionClientId(visionClient.getId());
                if (isNew) {
                    Assert.isTrue(1 == schoolClassMemberMapper.insertSelective(visionSchoolClassMember));
                } else {
                    visionSchoolClassMember.setModifiedTime(new Date());
                    Assert.isTrue(1 == schoolClassMemberMapper.updateByPrimaryKeySelective(visionSchoolClassMember));
                }
                visionSchoolClassMemberHashMap.put(tempKey, visionSchoolClassMember);
            }

            //import activity client(需要查重)
            visionActivityClient = activityClientMapper.selectByPrimaryKey(activityId, visionClient.getId());
            if (null == visionActivityClient) {
                visionActivityClient = new VisionActivityClient();
                visionActivityClient.setVisionActivityId(activityId);
                visionActivityClient.setVisionMemberId(visionSchoolClassMember.getId());
                visionActivityClient.setVisionClientId(visionClient.getId());
                visionActivityClient.setCreatedTime(new Date());
                Assert.isTrue(1 == activityClientMapper.insertSelective(visionActivityClient));
            }
        }


        //Clear HashMap
        visionClientHashMap.clear();
        visionSchoolHashMap.clear();
        visionSchoolClassHashMap.clear();
        visionSchoolClassMemberHashMap.clear();

        return activityClientMapper.selectByActivityId(activityId).stream().map(VisionActivityClientVOImpl::new).collect(Collectors.toList());
    }


    @Override
    public VisionActivityVO selectById(String activityId) {
        return new VisionActivityVOImpl(activityMapper.selectByPrimaryKey(activityId));
    }

    @Override
    @Transactional
    public List<? extends VisionActivityClientCheckRecordVO> importClientCheckRecord(String activityId, MultipartFile file) {
        return null;
    }

    @Override
    public List<? extends VisionActivityClientCheckRecordVO> getActivityClientCheckRecordList(String activityId, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return activityClientCheckRecordMapper.selectByActivityId(activityId).stream().map(VisionActivityClientCheckRecordVOImpl::new).collect(Collectors.toList());
    }

    private int indexOf(String[] source, String key) {
        for (int i = 0; i < source.length; i++)
            if (source[i].equals(key)) return i;
        return -1;
    }

    private void printLine(int line, String[] source) {
        for (String s : source) System.out.print(String.format("%d %s\t", line, s));
        System.out.println();
    }

    private class VisionActivityVOImpl implements VisionActivityVO {

        private VisionActivity visionActivity;

        private VisionActivityVOImpl(VisionActivity visionActivity) {
            this.visionActivity = visionActivity;
        }

        @Override
        public String getId() {
            return visionActivity.getId();
        }

        @Override
        public String getName() {
            return visionActivity.getName();
        }

        @Override
        public String getAddress() {
            return visionActivity.getAddress();
        }

        @Override
        public Date getBeginDate() {
            return visionActivity.getBeginDate();
        }

        @Override
        public Date getEndDate() {
            return visionActivity.getEndDate();
        }

        @Override
        public String getContent() {
            return visionActivity.getContent();
        }

        @Override
        public String getContactMan() {
            return visionActivity.getContactMan();
        }

        @Override
        public String getContactPhoneNumber() {
            return visionActivity.getContactPhoneNumber();
        }

        @Override
        public String getRemark() {
            return visionActivity.getRemark();
        }

    }

    private class VisionActivityClientVOImpl implements VisionActivityClientVO {

        private VisionActivityClientView visionActivityClientView;

        private VisionActivityClientVOImpl(VisionActivityClientView visionActivityClientView) {
            this.visionActivityClientView = visionActivityClientView;
        }

        @Override
        public String getVisionActivityId() {
            return visionActivityClientView.getVisionActivity().getId();
        }

        @Override
        public String getSchoolName() {
            return visionActivityClientView.getVisionSchool().getName();
        }

        @Override
        public String getClassName() {
            return visionActivityClientView.getVisionSchoolClass().getName();
        }

        @Override
        public String getStudentNumber() {
            return visionActivityClientView.getVisionSchoolClassMember().getStudentNumber();
        }

        @Override
        public String getClientId() {
            return visionActivityClientView.getVisionClient().getId();
        }

        @Override
        public String getName() {
            return visionActivityClientView.getVisionClient().getName();
        }

        @Override
        public Integer getGender() {
            return visionActivityClientView.getVisionClient().getGender();
        }

        @Override
        public Integer getAge() {
            return visionActivityClientView.getVisionClient().getAge();
        }

        @Override
        public String getIdNumber() {
            return visionActivityClientView.getVisionClient().getIdNumber();
        }

        @Override
        public String getNativePlace() {
            return visionActivityClientView.getVisionClient().getNativePlace();
        }

        @Override
        public BigDecimal getHeight() {
            return visionActivityClientView.getVisionClient().getHeight();
        }

        @Override
        public BigDecimal getWeight() {
            return visionActivityClientView.getVisionClient().getWeight();
        }

        @Override
        public Date getBirthday() {
            return visionActivityClientView.getVisionClient().getBirthday();
        }

        @Override
        public String getPhoneNumber() {
            return visionActivityClientView.getVisionClient().getPhoneNumber();
        }

        @Override
        public String getProvince() {
            return visionActivityClientView.getVisionClient().getProvince();
        }

        @Override
        public String getCity() {
            return visionActivityClientView.getVisionClient().getCity();
        }

        @Override
        public String county() {
            return visionActivityClientView.getVisionClient().getCounty();
        }

        @Override
        public String getDetailAddress() {
            return visionActivityClientView.getVisionClient().getDetailAddress();
        }

        @Override
        public BigDecimal getVisionAcuityLeft() {
            return visionActivityClientView.getVisionClient().getVisionAcuityLeft();
        }

        @Override
        public BigDecimal getVisionAcuityRight() {
            return visionActivityClientView.getVisionClient().getVisionAcuityRight();
        }

        @Override
        public BigDecimal getVisionAcuity() {
            return visionActivityClientView.getVisionClient().getVisionAcuity();
        }

        @Override
        public Integer getDioptersLeft() {
            return visionActivityClientView.getVisionClient().getDioptersLeft();
        }

        @Override
        public Integer getDioptersRight() {
            return visionActivityClientView.getVisionClient().getDioptersRight();
        }

        @Override
        public Integer getAstigmatismLeft() {
            return visionActivityClientView.getVisionClient().getAstigmatismLeft();
        }

        @Override
        public Integer getAstigmatismRight() {
            return visionActivityClientView.getVisionClient().getAstigmatismRight();
        }

        @Override
        public Integer getJointLuminosityLeft() {
            return visionActivityClientView.getVisionClient().getJointLuminosityLeft();
        }

        @Override
        public Integer getJointLuminosityRight() {
            return visionActivityClientView.getVisionClient().getJointLuminosityRight();
        }

        @Override
        public Integer getAxisLeft() {
            return visionActivityClientView.getVisionClient().getAxisLeft();
        }

        @Override
        public Integer getAxisRight() {
            return visionActivityClientView.getVisionClient().getAxisRight();
        }

        @Override
        public Integer getPupilDistance() {
            return visionActivityClientView.getVisionClient().getPupilDistance();
        }
    }

    private class VisionCheckRecordVOImpl implements VisionCheckRecordVO {

        private VisionCheckRecord visionCheckRecord;

        public VisionCheckRecordVOImpl(VisionCheckRecord visionCheckRecord) {
            this.visionCheckRecord = visionCheckRecord;
        }

        @Override
        public String getId() {
            return visionCheckRecord.getId();
        }

        @Override
        public String getEyeType() {
            return visionCheckRecord.getEyeType();
        }

        @Override
        public Date checkDate() {
            return visionCheckRecord.getCheckDate();
        }

        @Override
        public Integer getDataType() {
            return visionCheckRecord.getDataType();
        }

        @Override
        public String getPictureFile() {
            return visionCheckRecord.getPictureFile();
        }

        @Override
        public BigDecimal getPupil() {
            return visionCheckRecord.getPupil();
        }

        @Override
        public BigDecimal getSe1() {
            return visionCheckRecord.getSe1();
        }

        @Override
        public BigDecimal getDs1() {
            return visionCheckRecord.getDs1();
        }

        @Override
        public BigDecimal getDc1() {
            return visionCheckRecord.getDc1();
        }

        @Override
        public Integer getAxis1() {
            return visionCheckRecord.getAxis1();
        }

        @Override
        public BigDecimal getSe2() {
            return visionCheckRecord.getSe2();
        }

        @Override
        public BigDecimal getDs2() {
            return visionCheckRecord.getDs2();
        }

        @Override
        public BigDecimal getDc2() {
            return visionCheckRecord.getDc2();
        }

        @Override
        public Integer getAxis2() {
            return visionCheckRecord.getAxis2();
        }

        @Override
        public Integer getPd() {
            return visionCheckRecord.getPd();
        }

        @Override
        public BigDecimal getMmHg() {
            return visionCheckRecord.getMmHg();
        }

        @Override
        public Integer getGazeH() {
            return visionCheckRecord.getGazeH();
        }

        @Override
        public Integer getGazeV() {
            return visionCheckRecord.getGazeV();
        }
    }

    private class VisionActivityClientCheckRecordVOImpl implements VisionActivityClientCheckRecordVO {

        private VisionActivityClientCheckRecordView visionActivityClientCheckRecordView;

        private VisionActivityClientCheckRecordVOImpl(VisionActivityClientCheckRecordView visionActivityClientCheckRecordView) {
            this.visionActivityClientCheckRecordView = visionActivityClientCheckRecordView;
        }

        @Override
        public List<? extends VisionCheckRecordVO> getCheckRecordList() {
            return visionActivityClientCheckRecordView.getVisionCheckRecordList().stream().map(VisionCheckRecordVOImpl::new).collect(Collectors.toList());
        }

        @Override
        public String getVisionActivityId() {
            return visionActivityClientCheckRecordView.getVisionActivity().getId();
        }

        @Override
        public String getSchoolName() {
            return visionActivityClientCheckRecordView.getVisionSchool().getName();
        }

        @Override
        public String getClassName() {
            return visionActivityClientCheckRecordView.getVisionSchoolClass().getName();
        }

        @Override
        public String getStudentNumber() {
            return visionActivityClientCheckRecordView.getVisionSchoolClassMember().getStudentNumber();
        }

        @Override
        public String getClientId() {
            return visionActivityClientCheckRecordView.getVisionClient().getId();
        }

        @Override
        public String getName() {
            return visionActivityClientCheckRecordView.getVisionClient().getName();
        }

        @Override
        public Integer getGender() {
            return visionActivityClientCheckRecordView.getVisionClient().getGender();
        }

        @Override
        public Integer getAge() {
            return visionActivityClientCheckRecordView.getVisionClient().getAge();
        }

        @Override
        public String getIdNumber() {
            return visionActivityClientCheckRecordView.getVisionClient().getIdNumber();
        }

        @Override
        public String getNativePlace() {
            return visionActivityClientCheckRecordView.getVisionClient().getNativePlace();
        }

        @Override
        public BigDecimal getHeight() {
            return visionActivityClientCheckRecordView.getVisionClient().getHeight();
        }

        @Override
        public BigDecimal getWeight() {
            return visionActivityClientCheckRecordView.getVisionClient().getWeight();
        }

        @Override
        public Date getBirthday() {
            return visionActivityClientCheckRecordView.getVisionClient().getBirthday();
        }

        @Override
        public String getPhoneNumber() {
            return visionActivityClientCheckRecordView.getVisionClient().getPhoneNumber();
        }

        @Override
        public String getProvince() {
            return visionActivityClientCheckRecordView.getVisionClient().getProvince();
        }

        @Override
        public String getCity() {
            return visionActivityClientCheckRecordView.getVisionClient().getCity();
        }

        @Override
        public String county() {
            return visionActivityClientCheckRecordView.getVisionClient().getCounty();
        }

        @Override
        public String getDetailAddress() {
            return visionActivityClientCheckRecordView.getVisionClient().getDetailAddress();
        }

        @Override
        public BigDecimal getVisionAcuityLeft() {
            return visionActivityClientCheckRecordView.getVisionClient().getVisionAcuityLeft();
        }

        @Override
        public BigDecimal getVisionAcuityRight() {
            return visionActivityClientCheckRecordView.getVisionClient().getVisionAcuityRight();
        }

        @Override
        public BigDecimal getVisionAcuity() {
            return visionActivityClientCheckRecordView.getVisionClient().getVisionAcuity();
        }

        @Override
        public Integer getDioptersLeft() {
            return visionActivityClientCheckRecordView.getVisionClient().getDioptersLeft();
        }

        @Override
        public Integer getDioptersRight() {
            return visionActivityClientCheckRecordView.getVisionClient().getDioptersRight();
        }

        @Override
        public Integer getAstigmatismLeft() {
            return visionActivityClientCheckRecordView.getVisionClient().getAstigmatismLeft();
        }

        @Override
        public Integer getAstigmatismRight() {
            return visionActivityClientCheckRecordView.getVisionClient().getAstigmatismRight();
        }

        @Override
        public Integer getJointLuminosityLeft() {
            return visionActivityClientCheckRecordView.getVisionClient().getJointLuminosityLeft();
        }

        @Override
        public Integer getJointLuminosityRight() {
            return visionActivityClientCheckRecordView.getVisionClient().getJointLuminosityRight();
        }

        @Override
        public Integer getAxisLeft() {
            return visionActivityClientCheckRecordView.getVisionClient().getAxisLeft();
        }

        @Override
        public Integer getAxisRight() {
            return visionActivityClientCheckRecordView.getVisionClient().getAxisRight();
        }

        @Override
        public Integer getPupilDistance() {
            return visionActivityClientCheckRecordView.getVisionClient().getPupilDistance();
        }
    }

}
