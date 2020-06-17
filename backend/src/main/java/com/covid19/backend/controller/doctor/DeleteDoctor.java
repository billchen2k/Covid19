package com.covid19.backend.controller.doctor;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Doctor;
import com.covid19.backend.service.doctor.DeleteDoctorService;
import com.covid19.backend.service.doctor.GetDoctorInfoService;
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
import java.util.ArrayList;


@RestController
@Api(tags = "医生控制器", value = "和医生有关的控制器")
public class DeleteDoctor {
    @Autowired
    public DeleteDoctorService deleteDoctorService;
    @Autowired
    public GetDoctorInfoService getDoctorInfoService;

    @PostMapping("/doctor/deleteDoctorByID")
    @ApiOperation(value = "删除医生信息",notes = "可以删除医生的信息")
    @ApiImplicitParam(name = "doctor_id",value = "医生ID")
    public Result deleteDoctorByID(
            @RequestParam(value = "doctor_id") long doctor_id,
            HttpServletRequest request)
    {
        if(deleteDoctorService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Doctor doctor = getDoctorInfoService.getDoctorInfoByID(doctor_id);
        if(doctor == null) return Result.error(2012,"不存在该医生");
        deleteDoctorService.deleteDoctorByID(doctor_id);
        return Result.ok();
    }
}
