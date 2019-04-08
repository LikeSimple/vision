package org.vision.service.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.vision.service.admin.controller.criteria.ActivityCriteria;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.persistence.mapper.*;
import org.vision.service.admin.persistence.model.*;
import org.vision.service.admin.service.ActivityService;
import org.vision.service.admin.service.vo.VisionActivityClientCheckRecordVO;
import org.vision.service.admin.util.PoiUtil;
import org.vision.service.admin.util.ShortUUIDGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConfiguration(value = "")
public class AdminServiceApplicationTests {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemAuthorityMapper systemAuthorityMapper;

    @Autowired
    private SystemUserProfileMapper systemUserProfileMapper;

    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    private SystemRoleAuthorityMapper systemRoleAuthorityMapper;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private VisionActivityMapper visionActivityMapper;

    @Autowired
    private VisionActivityClientMapper visionActivityClientMapper;

    @Autowired
    private VisionSchoolMapper visionSchoolMapper;

    @Autowired
    private VisionSchoolClassMapper visionSchoolClassMapper;

    @Autowired
    private VisionSchoolClassMemberMapper visionSchoolClassMemberMapper;

    @Autowired
    private VisionClientMapper visionClientMapper;

    @Autowired
    private VisionCheckRecordMapper visionCheckRecordMapper;

    @Autowired
    private VisionActivityClientCheckRecordMapper visionActivityClientCheckRecordMapper;

    @Test
    @Transactional
    public void contextLoads() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

        String username = "admin";
        String password = "admin";

        SystemUser systemUser = new SystemUser();

        systemUser.setId(ShortUUIDGenerator.newID());
        systemUser.setUsername("admin");
        systemUser.setPassword(encoder.encode("admin"));
        systemUser.setEnabled(true);
        systemUser.setLocked(false);
        systemUser.setCreatedTime(new Date());
        systemUserMapper.insertSelective(systemUser);

        SystemUserProfile profile = new SystemUserProfile();
        profile.setId(systemUser.getId());
        profile.setAvatar("");
        profile.setGender((byte) 1);
        profile.setName("管理员");
        profile.setCreatedTime(new Date());
        systemUserProfileMapper.insertSelective(profile);

        SystemRole systemRole = new SystemRole();
        systemRole.setId(ShortUUIDGenerator.newID());
        systemRole.setName("ROLE_ADMIN");
        systemRoleMapper.insertSelective(systemRole);

        SystemUserRole systemUserRole = new SystemUserRole();
        systemUserRole.setSystemUserId(systemUser.getId());
        systemUserRole.setRoleId(systemRole.getId());
        systemUserRoleMapper.insertSelective(systemUserRole);

        SystemAuthority systemAuthority = new SystemAuthority();
        systemAuthority.setId(ShortUUIDGenerator.newID());
        systemAuthority.setName("AUTH_ADMIN");
        systemAuthority.setDesc("");
        systemAuthorityMapper.insertSelective(systemAuthority);

        SystemRoleAuthority systemRoleAuthority = new SystemRoleAuthority();
        systemRoleAuthority.setRoleId(systemRole.getId());
        systemRoleAuthority.setAuthorityId(systemAuthority.getId());
        systemRoleAuthorityMapper.insertSelective(systemRoleAuthority);
    }

    @Test
    public void VisionActivityTest() throws ParseException {
        ActivityCriteria criteria = new ActivityCriteria();
        criteria.setNameCriteria("活动");
        Assert.isTrue(2 == activityService.selectByCriteria(criteria, 2, 1).size());
        Assert.isTrue(1 == activityService.selectByCriteria(criteria, 2, 2).size());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        criteria.setBeginDate(df.parse("2019-03-01 00:00:00"));
        Assert.notEmpty(activityService.selectByCriteria(criteria, 1, 20));

        criteria.setEndDate(df.parse("2019-03-28 00:00:00"));
        Assert.notEmpty(activityService.selectByCriteria(criteria, 1, 20));

        criteria.setArchived(false);
        Assert.notEmpty(activityService.selectByCriteria(criteria, 1, 20));
    }

    @Test
    @Transactional
    public void InsertVisionActivityTest() {
        ActivityParam activityParam = new ActivityParam();

        activityParam.setName("XXX学校视力筛查活动");
        activityParam.setAddress("上海市黄浦区中山南一路XXX弄XXX号");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        activityParam.setBeginDate(new Date(2019, 3, 1));
        activityParam.setEndDate(new Date(2019, 3, 10));
        activityParam.setContent("活动内容通知等等等等");
        activityParam.setContactMan("联系人");
        activityParam.setContactPhoneNumber("00000000000");
        activityParam.setRemark("");

        VisionActivity visionActivity = new VisionActivity(activityParam);

        Assert.isTrue(0 != visionActivityMapper.insertSelective(visionActivity));
    }

    @Test
    @Transactional
    public void InsertVisionActivityClientTest() {
        //VisionActivity
        VisionActivity visionActivity = visionActivityMapper.selectByPrimaryKey("1emsbyhwN5PHAjTl3wFjAZ");
        //import client
        VisionClient visionClient = new VisionClient();
        visionClient.setId(ShortUUIDGenerator.newID());
        visionClient.setName("赵子杰");
        visionClient.setGender(1);
        visionClient.setAge(14);
        visionClient.setIdNumber("310115200501283450");
        visionClient.setNativePlace("江苏东台");
        visionClient.setHeight(BigDecimal.valueOf(155));
        visionClient.setWeight(BigDecimal.valueOf(46));
        visionClient.setVisionAcuityLeft(BigDecimal.valueOf(0.3));
        visionClient.setVisionAcuityRight(BigDecimal.valueOf(0.4));
        visionClient.setVisionAcuity(BigDecimal.valueOf(0.4));
        visionClient.setDioptersLeft(-175);
        visionClient.setDioptersRight(-150);
        visionClient.setAstigmatismLeft(0);
        visionClient.setAstigmatismRight(0);
        visionClient.setJointLuminosityLeft(-175);
        visionClient.setJointLuminosityRight(-150);
        visionClient.setAxisLeft(180);
        visionClient.setAxisRight(180);
        visionClient.setPupilDistance(53);
        visionClient.setCreatedTime(new Date());
        Assert.isTrue(1 == visionClientMapper.insertSelective(visionClient));
        //import School
        VisionSchool visionSchool = new VisionSchool();
        visionSchool.setId(ShortUUIDGenerator.newID());
        visionSchool.setName("上海进才北校");
        visionSchool.setCreatedTime(new Date());
        Assert.isTrue(1 == visionSchoolMapper.insertSelective(visionSchool));
        //import School Class
        VisionSchoolClass visionSchoolClass = new VisionSchoolClass();
        visionSchoolClass.setId(ShortUUIDGenerator.newID());
        visionSchoolClass.setName("初二（10）班");
        visionSchoolClass.setVisionSchoolId(visionSchool.getId());
        visionSchoolClass.setCreatedTime(new Date());
        Assert.isTrue(1 == visionSchoolClassMapper.insertSelective(visionSchoolClass));
        //import School Class member
        VisionSchoolClassMember visionSchoolClassMember = new VisionSchoolClassMember();
        visionSchoolClassMember.setId(ShortUUIDGenerator.newID());
        visionSchoolClassMember.setVisionClassId(visionSchoolClass.getId());
        visionSchoolClassMember.setStudentNumber("JC1324");
        visionSchoolClassMember.setVisionClientId(visionClient.getId());
        visionSchoolClassMember.setCreatedTime(new Date());
        Assert.isTrue(1 == visionSchoolClassMemberMapper.insertSelective(visionSchoolClassMember));
        //import activity client
        VisionActivityClient visionActivityClient = new VisionActivityClient();
        visionActivityClient.setVisionActivityId(visionActivity.getId());
        visionActivityClient.setVisionClientId(visionClient.getId());
        visionActivityClient.setCreatedTime(new Date());
        Assert.isTrue(1 == visionActivityClientMapper.insertSelective(visionActivityClient));

        Assert.notNull(visionActivityClientMapper.selectByClientId(visionClient.getId()));

        Assert.notEmpty(visionActivityClientMapper.selectByActivityId(visionActivity.getId()));

    }

    @Test
//    @Transactional
    public void PoiTest() {

        final String[] keyList = new String[]{"姓名", "性别", "年龄", "身份证号", "籍贯", "学校", "班级", "学号", "身高", "体重",
                "裸眼视力左", "裸眼视力右", "裸眼视力双眼", "屈光度左", "屈光度右", "散光度左", "散光度右", "联合光度左", "联合光度右",
                "轴位左", "轴位右", "瞳距"};

        //传入参数
        String activityId = "2KSDlSrNZfBrUMzrG9iad6";

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

        File file = new File("/Users/xuzhen/Documents/Dev/vision/docs/视力筛查系统表格.xlsx");

        try {
            InputStream is = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("视力筛查系统表格.xlsx", is);
            //读取Excel表格
            List<String[]> list = PoiUtil.readExcel(multipartFile);

            //分析头部位置（可能乱序）
            HashMap<String, Integer> keys = new HashMap<>(keyList.length);
            for (String s : list.get(0)) {
                keys.put(s, indexOf(keyList, s));
            }

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
                    visionClient = visionClientMapper.selectByIdNumber(tempKey);

                    if (null == visionClient) {
                        // 数据库没有重复（新数据）
                        isNew = true; //标志该数据为新数据
                        visionClient = new VisionClient();
                        visionClient.setId(ShortUUIDGenerator.newID());
                        visionClient.setCreatedTime(new Date());
                    }

                    visionClient.setName(list.get(i)[keys.get("姓名")]);
                    visionClient.setGender(list.get(i)[keys.get("性别")].equals("男") ? 1 : 2);
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
                        Assert.isTrue(1 == visionClientMapper.insertSelective(visionClient));
                    } else {
                        visionClient.setModifiedTime(new Date());
                        Assert.isTrue(1 == visionClientMapper.updateByPrimaryKey(visionClient));
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
                    visionSchool = visionSchoolMapper.selectByName(tempKey);
                    if (null == visionSchool) {
                        visionSchool = new VisionSchool();
                        visionSchool.setId(ShortUUIDGenerator.newID());
                        visionSchool.setName(list.get(i)[keys.get("学校")]);
                        visionSchool.setCreatedTime(new Date());
                        Assert.isTrue(1 == visionSchoolMapper.insertSelective(visionSchool));
                    }
                    visionSchoolHashMap.put(tempKey, visionSchool);
                }

                //import School Class
                tempKey = list.get(i)[keys.get("班级")].trim();
                visionSchoolClass = visionSchoolClassHashMap.get(tempKey);
                if (null == visionSchoolClass) {
                    visionSchoolClass = visionSchoolClassMapper.selectByName(tempKey);
                    if (null == visionSchoolClass) {
                        visionSchoolClass = new VisionSchoolClass();
                        visionSchoolClass.setId(ShortUUIDGenerator.newID());
                        visionSchoolClass.setName(list.get(i)[keys.get("班级")]);
                        visionSchoolClass.setVisionSchoolId(visionSchool.getId());
                        visionSchoolClass.setCreatedTime(new Date());
                        Assert.isTrue(1 == visionSchoolClassMapper.insertSelective(visionSchoolClass));
                    }
                    visionSchoolClassHashMap.put(tempKey, visionSchoolClass);
                }

                //import School Class member
                tempKey = list.get(i)[keys.get("学号")];
                visionSchoolClassMember = visionSchoolClassMemberHashMap.get(tempKey);
                if (null == visionSchoolClassMember) {
                    visionSchoolClassMember = visionSchoolClassMemberMapper.selectByCombinedKeys(visionSchoolClass.getId(), visionClient.getId());
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
                        Assert.isTrue(1 == visionSchoolClassMemberMapper.insertSelective(visionSchoolClassMember));
                    } else {
                        visionSchoolClassMember.setModifiedTime(new Date());
                        Assert.isTrue(1 == visionSchoolClassMemberMapper.updateByPrimaryKeySelective(visionSchoolClassMember));
                    }
                    visionSchoolClassMemberHashMap.put(tempKey, visionSchoolClassMember);
                }

                //import activity client(需要查重)
                visionActivityClient = visionActivityClientMapper.selectByPrimaryKey(activityId, visionClient.getId());
                if (null == visionActivityClient) {
                    visionActivityClient = new VisionActivityClient();
                    visionActivityClient.setVisionActivityId(activityId);
                    visionActivityClient.setVisionMemberId(visionSchoolClassMember.getId());
                    visionActivityClient.setVisionClientId(visionClient.getId());
                    visionActivityClient.setCreatedTime(new Date());
                    Assert.isTrue(1 == visionActivityClientMapper.insertSelective(visionActivityClient));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int indexOf(String[] source, String key) {
        for (int i = 0; i < source.length; i++)
            if (source[i].equals(key)) return i;
        return -1;
    }

    private void printLine(int line, String[] source) {
        for (int i = 0; i < source.length; i++)
            System.out.print(String.format("%d %s\t", line, source[i]));
        System.out.println();
    }

    @Test
    public void CheckRecordTest() throws ParseException {
        VisionCheckRecord visionCheckRecord = new VisionCheckRecord();
        visionCheckRecord.setId(ShortUUIDGenerator.newID());
        visionCheckRecord.setVisionClientId("1Bsa70ScNaOHM86yDf4vYr");
        visionCheckRecord.setEyeType("OS");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        visionCheckRecord.setCheckDate(df.parse("2019-03-08 12:00:00"));
        visionCheckRecord.setDataType(1);
        visionCheckRecord.setPictureFile("");
        visionCheckRecord.setPupil(new BigDecimal(2.1));
        visionCheckRecord.setSe1(new BigDecimal(-2.0));
        visionCheckRecord.setDs1(new BigDecimal(2.0));
        visionCheckRecord.setDc1(new BigDecimal(2.0));
        visionCheckRecord.setAxis1(30);
        visionCheckRecord.setSe2(new BigDecimal(2.0));
        visionCheckRecord.setDs2(new BigDecimal(-2.0));
        visionCheckRecord.setDc2(new BigDecimal(3.0));
        visionCheckRecord.setAxis2(180);
        visionCheckRecord.setPd(30);
        visionCheckRecord.setMmHg(new BigDecimal(0));
        visionCheckRecord.setGazeH(111);
        visionCheckRecord.setGazeV(111);
        visionCheckRecord.setEnabled(true);
        visionCheckRecord.setCreateTime(df.parse("2019-03-08 12:00:00"));
        visionCheckRecordMapper.insertSelective(visionCheckRecord);

    }

    @Test
    public void selectActivityClientCheckRecordMapperTest() {
        List<VisionActivityClientCheckRecordView> visionActivityClientCheckRecordViewList = visionActivityClientCheckRecordMapper.selectByActivityId("1emsbyhwN5PHAjTl3wFjAZ");
        Assert.notEmpty(visionActivityClientCheckRecordViewList);
        System.out.println(visionActivityClientCheckRecordViewList.get(0));
    }

    @Test
    public void selectActivityClientCheckRecordServiceTest() {
        List<? extends VisionActivityClientCheckRecordVO> visionActivityClientCheckRecordVOS = activityService.getActivityClientCheckRecordList("1emsbyhwN5PHAjTl3wFjAZ", 20, 1);
        Assert.notEmpty(visionActivityClientCheckRecordVOS);

    }

    @Test
    @Transactional
    public void uploadCSVTest() throws ParseException {

        CsvReader reader = CsvUtil.getReader();
        reader.setContainsHeader(true);
        CsvData data = reader.read(FileUtil.file("../../../../docs/20190313_vision_check_data.csv"));
        List<CsvRow> rows = data.getRows();

        for (CsvRow csvRow : rows) {
            //getRawList返回一个List列表，列表的每一项为CSV中的一个单元格（既逗号分隔部分）
            //Insert into vision check record
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            VisionCheckRecord record = new VisionCheckRecord();
            record.setId(ShortUUIDGenerator.newID());
            record.setVisionClientId(csvRow.getByName("clientId"));
            record.setEyeType(csvRow.getByName("eyeType").equals("1")?"OD":"OS");
            record.setCheckDate(df.parse(csvRow.getByName("date")));
            record.setDataType(new Integer(csvRow.getByName("dataType")));
            record.setPictureFile("");
            record.setPupil(new BigDecimal(csvRow.getByName("pupil")));
            record.setSe1(new BigDecimal(csvRow.getByName("se1")));
            record.setSe2(new BigDecimal(csvRow.getByName("se2")));
            record.setDs1(new BigDecimal(csvRow.getByName("ds1")));
            record.setDs2(new BigDecimal(csvRow.getByName("ds2")));
            record.setDc1(new BigDecimal(csvRow.getByName("dc1")));
            record.setDc2(new BigDecimal(csvRow.getByName("dc2")));
            record.setAxis1(new Integer(csvRow.getByName("axis1")));
            record.setAxis2(new Integer(csvRow.getByName("axis2")));
            record.setPd(new Integer(csvRow.getByName("pd")));
            record.setMmHg(new BigDecimal(csvRow.getByName("mmHg")));
            record.setGazeH(new Integer(csvRow.getByName("gazeH")));
            record.setGazeV(new Integer(csvRow.getByName("gazeV")));
            record.setRemark("");
            record.setEnabled(true);
            df = new SimpleDateFormat("yyyyMMddHHmmss");
            System.out.println(csvRow.getByName("date") + csvRow.getByName("time"));
            record.setCreateTime(df.parse(csvRow.getByName("date") + csvRow.getByName("time")));
            visionCheckRecordMapper.insertSelective(record);

            //Insert into vision activity client check record
            VisionActivityClientCheckRecord checkRecord = new VisionActivityClientCheckRecord();
            checkRecord.setVisionActivityId(csvRow.getByName("activityId"));
            checkRecord.setVisionCheckRecordId(record.getId());
            checkRecord.setVisionClientId(csvRow.getByName("clientId"));
            checkRecord.setCreatedTime(new Date());
            visionActivityClientCheckRecordMapper.insertSelective(checkRecord);
        }
    }

    @Test
    public void dateFormatTest() {
        try {
            String d = "20190313";
            String t = "123303";

            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = df.parse(d + t);
            System.out.println(date);
        }catch (ParseException pe) {
            System.out.println(pe);
        }
    }

}

