package com.covid19.backend.controller.doctor;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.doctor.GetDoctorInfoService;
import com.covid19.backend.service.doctor.UpdateDoctorService;
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
 * 更新医生控制器
 */
@RestController
@Api(tags = "医生控制器", value = "和医生有关的控制器")
public class UpdateDoctor extends BaseController{
    @Autowired
    public UpdateDoctorService updateDoctorService;
    @Autowired
    public GetDoctorInfoService getDoctorInfoService;

    @PostMapping("/doctor/updateDoctor")
    @ApiOperation(value = "更新医生信息", notes = "可以更新医生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "doctor_id",value = "医生ID"),
            @ApiImplicitParam(name = "name",value = "医生姓名"),
            @ApiImplicitParam(name = "gender",value = "医生性别"),
            @ApiImplicitParam(name = "birthday",value = "医生生日"),
            @ApiImplicitParam(name = "department",value = "所在部门"),
            @ApiImplicitParam(name = "hospital_id",value = "所在医院")
    })
    public Result updateDoctor(
            @RequestParam(value = "doctor_id") long doctor_id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender",required = false)String gender,
            @RequestParam(value = "birthday",required = false)String birthday,
            @RequestParam(value = "department",required = false)String department,
            @RequestParam(value = "hospital_id",required = false)String hospital_id,
            HttpServletRequest request)
    {
        if(updateDoctorService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Doctor doctor = getDoctorInfoService.getDoctorInfoByID(doctor_id);
        if(doctor == null) return Result.error(2012,"不存在该医生");

        Doctor new_doctor = updateDoctorService.updateDoctor(
                doctor_id,
                name,
                gender,
                birthday,
                department,
                hospital_id);
        return Result.ok(doctor_id);
    }
}
