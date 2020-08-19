package com.covid19.backend.controller.doctor;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.doctor.GetDoctorInfoService;
import com.github.pagehelper.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 获取医生信息的控制器
 **/
@RestController
@Api(tags = "医生控制器", value = "和医生有关的控制器")
public class GetDoctorInfo {
    @Autowired
    public GetDoctorInfoService getDoctorInfoService;

    @PostMapping("/doctor/getDoctorInfoByID")
    @ApiOperation(value = "根据医生ID获取医生信息", notes = "可以根据医生ID获取医生信息")
    @ApiImplicitParam(name = "doctor_id",value = "医生ID")
    public Result<HashMap<Object, Object>> getDoctorInfoByID(
            @RequestParam(value = "doctor_id") long doctor_id
    )
    {
        HashMap<Object, Object> doctor = getDoctorInfoService.getDetailedDoctorInfoByID(doctor_id);
        if(doctor == null) return Result.error(2012,"不存在该医生");
        return Result.ok(doctor);
    }

    @PostMapping("/doctor/getDoctorInfo")
    @ApiOperation(value = "根据医生除ID外属性批量获取医生信息", notes = "可以根据病人除ID外属性获取病人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "医生姓名"),
            @ApiImplicitParam(name = "gender",value = "医生性别"),
            @ApiImplicitParam(name = "birthday",value = "医生生日"),
            @ApiImplicitParam(name = "department",value = "所在部门"),
            @ApiImplicitParam(name = "hospital_id",value = "所在医院")
    })
    public Result<ArrayList<Doctor>> getDoctorInfo(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender",required = false)String gender,
            @RequestParam(value = "birthday",required = false)String birthday,
            @RequestParam(value = "department",required = false)String department,
            @RequestParam(value = "hospital_id",required = false)String hospital_id,
            @RequestParam Integer page, // 分页
            @RequestParam Integer size
    )
    {
        Page<HashMap<String, String>> pageInfo = PageHelper.startPage(page, size);
        ArrayList<HashMap<String, String>> list = getDoctorInfoService.getDoctorInfo(
                name,
                gender,
                birthday,
                department,
                hospital_id
        );
        if (list == null) return null;
        return Result.pagedOk(pageInfo, pageInfo.getTotal());
    }
}
