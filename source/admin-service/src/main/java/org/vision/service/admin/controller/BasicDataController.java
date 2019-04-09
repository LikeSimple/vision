package org.vision.service.admin.controller;

import org.springframework.web.bind.annotation.*;
import org.vision.service.admin.common.ResponseData;
import org.vision.service.admin.controller.criteria.SchoolClassMemberParam;
import org.vision.service.admin.controller.criteria.SchoolClassParam;
import org.vision.service.admin.controller.criteria.SchoolCriteria;
import org.vision.service.admin.controller.criteria.SchoolParam;
import org.vision.service.admin.service.BasicDataService;
import org.vision.service.admin.service.vo.*;

import java.util.List;

@RestController
@RequestMapping("/api/basic-data/")
public class BasicDataController {

    private BasicDataService basicDataService;

    public BasicDataController(BasicDataService basicDataService) {
        this.basicDataService = basicDataService;
    }

    @PostMapping("/school/list")
    public ResponseData<List<? extends SchoolVO>> getSchoolList(
            @RequestBody SchoolCriteria schoolCriteria,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        return new ResponseData<>(basicDataService.getSchoolList(schoolCriteria, pageSize, pageNum));
    }

    @PostMapping("/school/new")
    public ResponseData<SchoolVO> createSchool(@RequestBody SchoolParam schoolParam) {
        return new ResponseData<>(basicDataService.createSchool(schoolParam));
    }

    @PostMapping("/school/{schoolId}/modify")
    public ResponseData<SchoolVO> modifySchool(
            @PathVariable("schoolId") String schoolId,
            @RequestBody SchoolParam schoolParam) {
        return new ResponseData<>(basicDataService.modifySchool(schoolId, schoolParam));
    }

    @PostMapping("/school/{schoolId}/class/list")
    public ResponseData<List<? extends SchoolClassVO>> getSchoolClassList(
            @PathVariable("schoolId") String schoolId,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        return new ResponseData<>(basicDataService.getSchoolClassList(schoolId, pageSize, pageNum));
    }

    @PostMapping("/school/{schoolId}/class/new")
    public ResponseData<SchoolClassVO> createSchoolClass(
            @PathVariable("schoolId") String schoolId,
            @RequestBody SchoolClassParam schoolClassParam) {
        return new ResponseData<>(basicDataService.createSchoolClass(schoolId, schoolClassParam));
    }

    @PostMapping("/school/{schoolId}/class/{classId}/modify")
    public ResponseData<SchoolClassVO> modifySchoolClass(
            @PathVariable("schoolId") String schoolId,
            @PathVariable("classId") String classId,
            @RequestBody SchoolClassParam schoolClassParam) {
        return new ResponseData<>(basicDataService.modifySchoolClass(schoolId, classId, schoolClassParam));
    }

    @PostMapping("/school/{schoolId}/class/{classId}/member/list")
    public ResponseData<List<? extends SchoolClassMemberVO>> getSchoolClassList(
            @PathVariable("schoolId") String schoolId,
            @PathVariable("classId") String classId,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        return new ResponseData<>(basicDataService.getSchoolClassMemberList(schoolId, classId, pageSize, pageNum));
    }

    @PostMapping("/school/{schoolId}/class/{classId}/member/new")
    public ResponseData<SchoolClassMemberVO> createSchoolClassMember(
            @PathVariable("schoolId") String schoolId,
            @PathVariable("classId") String classId,
            @RequestBody SchoolClassMemberParam schoolClassMemberParam) {
        return new ResponseData<>(basicDataService.createSchoolClassMember(schoolId, classId, schoolClassMemberParam));
    }

    @PostMapping("/school/{schoolId}/class/{classId}/member/{memberId}/modify")
    public ResponseData<SchoolClassMemberVO> modifySchoolClass(
            @PathVariable("schoolId") String schoolId,
            @PathVariable("classId") String classId,
            @PathVariable("memberId") String memberId,
            @RequestBody SchoolClassMemberParam schoolClassMemberParam) {
        return new ResponseData<>(basicDataService.modifySchoolClassMember(schoolId, classId, memberId, schoolClassMemberParam));
    }

    @PostMapping("/province/list")
    public ResponseData<List<? extends ProvinceVO>> getProvinceList() {
        return new ResponseData<>(basicDataService.getProvinceList());
    }
}
