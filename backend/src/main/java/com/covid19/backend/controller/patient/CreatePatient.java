package com.covid19.backend.controller.patient;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Patient;
import com.covid19.backend.service.patient.CreatePatientService;
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
 * 创建病人控制器
 */
@RestController
@Api(tags = "病人控制器", value = "和病人有关的控制器")
public class CreatePatient extends BaseController{
    @Autowired
    public CreatePatientService createPatientService;

    @PostMapping("/patient/createPatient")
    @ApiOperation(value = "创建病人信息", notes = "可以创建病人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "病人名称"),
            @ApiImplicitParam(name = "gender",value = "病人性别"),
            @ApiImplicitParam(name = "birthday",value = "病人生日"),
            @ApiImplicitParam(name = "onset_place",value = "发病地点"),
            @ApiImplicitParam(name = "onset_date",value = "发病时间"),
            @ApiImplicitParam(name = "confirm_date",value = "确诊时间"),
            @ApiImplicitParam(name = "status",value = "住院状态"),
            @ApiImplicitParam(name = "doctor_id",value = "主治大夫"),
            @ApiImplicitParam(name = "hospital_id",value = "治疗医院"),
            @ApiImplicitParam(name = "is_doctor",value = "是否是医生")
    })
    public Result createPatient(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender",required = false)String gender,
            @RequestParam(value = "birthday",required = false)String birthday,
            @RequestParam(value = "onset_place",required = false)String onset_place,
            @RequestParam(value = "onset_date",required = false)String onset_date,
            @RequestParam(value = "confirm_date",required = false)String confirm_date,
            @RequestParam(value = "status",required = false)String status,
            @RequestParam(value = "doctor_id",required = false)String doctor_id,
            @RequestParam(value = "hospital_id",required = false)String hospital_id,
            @RequestParam(value = "is_doctor",required = false)String is_doctor,
            HttpServletRequest request)
    {
        if(createPatientService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");

        Patient new_patient = createPatientService.createPatient(
                name,
                gender,
                birthday,
                onset_place,
                onset_date,
                confirm_date,
                status,
                doctor_id,
                hospital_id,
                is_doctor);
        return Result.ok(new_patient);
    }
}
