package com.covid19.backend.controller.doctor;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.doctor.CreateDoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建医生控制器
 */
@RestController
@Api(tags = "医生控制器", value = "和医生有关的控制器")
public class CreateDoctor extends BaseController{
    @Autowired
    public CreateDoctorService createDoctorService;

    @PostMapping("/doctor/createDoctor")
    @ApiOperation(value = "创建医生信息", notes = "可以创建医生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "医生姓名"),
            @ApiImplicitParam(name = "gender",value = "医生性别"),
            @ApiImplicitParam(name = "birthday",value = "医生生日"),
            @ApiImplicitParam(name = "department",value = "所在部门"),
            @ApiImplicitParam(name = "hospital_id",value = "所在医院")
    })
    public Result createDoctor(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender",required = false)String gender,
            @RequestParam(value = "birthday",required = false)String birthday,
            @RequestParam(value = "department",required = false)String department,
            @RequestParam(value = "hospital_id",required = false)String hospital_id,
            HttpServletRequest request)
    {
        if(createDoctorService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");

        Doctor new_doctor = createDoctorService.createDoctor(
                name,
                gender,
                birthday,
                department,
                hospital_id);
        return Result.ok(new_doctor);
    }
}
