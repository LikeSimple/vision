package org.vision.service.admin.service;

import org.vision.service.admin.controller.criteria.SchoolClassMemberParam;
import org.vision.service.admin.controller.criteria.SchoolClassParam;
import org.vision.service.admin.controller.criteria.SchoolCriteria;
import org.vision.service.admin.controller.criteria.SchoolParam;
import org.vision.service.admin.service.vo.ProvinceVO;
import org.vision.service.admin.service.vo.SchoolClassMemberVO;
import org.vision.service.admin.service.vo.SchoolClassVO;
import org.vision.service.admin.service.vo.SchoolVO;

import java.util.List;

public interface BasicDataService {
    List<? extends SchoolVO> getSchoolList(SchoolCriteria schoolCriteria, int pageSize, int pageNum);

    SchoolVO createSchool(SchoolParam schoolParam);

    SchoolVO modifySchool(String schoolId, SchoolParam schoolParam);

    List<? extends SchoolClassVO> getSchoolClassList(String schoolId, int pageSize, int pageNum);

    SchoolClassVO createSchoolClass(String schoolId, SchoolClassParam schoolClassParam);

    SchoolClassVO modifySchoolClass(String schoolId, String classId, SchoolClassParam schoolParam);

    List<? extends SchoolClassMemberVO> getSchoolClassMemberList(String schoolId, String classId, int pageSize, int pageNum);

    SchoolClassMemberVO createSchoolClassMember(String schoolId, String classId, SchoolClassMemberParam schoolClassMemberParam);

    SchoolClassMemberVO modifySchoolClassMember(String schoolId, String classId, String memberId, SchoolClassMemberParam schoolClassMemberParam);

    List<? extends ProvinceVO> getProvinceList();
}
