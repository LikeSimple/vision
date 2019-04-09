package org.vision.service.admin.service.impl;

import org.springframework.stereotype.Service;
import org.vision.service.admin.controller.criteria.SchoolClassMemberParam;
import org.vision.service.admin.controller.criteria.SchoolClassParam;
import org.vision.service.admin.controller.criteria.SchoolCriteria;
import org.vision.service.admin.controller.criteria.SchoolParam;
import org.vision.service.admin.service.BasicDataService;
import org.vision.service.admin.service.vo.ProvinceVO;
import org.vision.service.admin.service.vo.SchoolClassMemberVO;
import org.vision.service.admin.service.vo.SchoolClassVO;
import org.vision.service.admin.service.vo.SchoolVO;

import java.util.List;

@Service("basicDataService")
public class BasicDataServiceImpl implements BasicDataService {
    @Override
    public List<? extends SchoolVO> getSchoolList(SchoolCriteria schoolCriteria, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public SchoolVO createSchool(SchoolParam schoolParam) {
        return null;
    }

    @Override
    public SchoolVO modifySchool(String schoolId, SchoolParam schoolParam) {
        return null;
    }

    @Override
    public List<? extends SchoolClassVO> getSchoolClassList(String schoolId, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public SchoolClassVO createSchoolClass(String schoolId, SchoolClassParam schoolClassParam) {
        return null;
    }

    @Override
    public SchoolClassVO modifySchoolClass(String schoolId, String classId, SchoolClassParam schoolParam) {
        return null;
    }

    @Override
    public List<? extends SchoolClassMemberVO> getSchoolClassMemberList(String schoolId, String classId, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public SchoolClassMemberVO createSchoolClassMember(String schoolId, String classId, SchoolClassMemberParam schoolClassMemberParam) {
        return null;
    }

    @Override
    public SchoolClassMemberVO modifySchoolClassMember(String schoolId, String classId, String memberId, SchoolClassMemberParam schoolClassMemberParam) {
        return null;
    }

    @Override
    public List<? extends ProvinceVO> getProvinceList() {
        return null;
    }
}
