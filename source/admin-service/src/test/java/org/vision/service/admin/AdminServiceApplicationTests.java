package org.vision.service.admin;

import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.vision.service.admin.controller.criteria.ActivityCriteria;
import org.vision.service.admin.controller.criteria.ActivityParam;
import org.vision.service.admin.persistence.mapper.*;
import org.vision.service.admin.persistence.model.*;
import org.vision.service.admin.service.ActivityService;
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
@SpringBootTest
public class AdminServiceApplicationTests {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemAuthorityMapper systemAuthorityMapper;

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


    @Test
    @Transactional
    public void contextLoads() {

        String username = "admin";
        String password = "admin";

        SystemUser systemUser = systemUserMapper.selectByUsername(username);

        SystemRole systemRole = new SystemRole();
        systemRole.setId(ShortUUIDGenerator.newID());
        systemRole.setName("ROLE_ADMIN");
        systemRole.setDesc("");
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
        VisionActivity visionActivity = visionActivityMapper.selectByPrimaryKey("2KSDlSrNZfBrUMzrG9iad6");
        //import client
        VisionClient visionClient = new VisionClient();
        visionClient.setId(ShortUUIDGenerator.newID());
        visionClient.setName("赵子杰");
        visionClient.setGender((byte) 1);
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
                    visionClient.setGender((byte) (list.get(i)[keys.get("性别")].equals("男") ? 1 : 2));
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

}

