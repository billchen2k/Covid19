package com.covid19.backend.controller.patient;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Patient;
import com.covid19.backend.service.patient.GetPatientInfoService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 获取病人信息的控制器
 **/
@RestController
@Api(tags = "病人控制器", value = "和病人有关的控制器")
public class GetPatientInfo {
    @Autowired
    public GetPatientInfoService getPatientInfoService;

    @PostMapping("/patient/getPatientInfoByID")
    @ApiOperation(value = "根据病人ID获取病人信息", notes = "可以根据病人ID获取病人信息")
    @ApiImplicitParam(name = "patient_id",value = "病人ID")
    public Result<HashMap<String, String>> getPatientInfoByID(
            @RequestParam(value = "patient_id") long patient_id
    )
    {
        HashMap<String, String> patient = getPatientInfoService.getDetailedPatiendInfoByID(patient_id);
        if(patient == null) return Result.error(2012,"不存在该病人");
        return Result.ok(patient);
    }

    @PostMapping("/patient/getPatientInfo")
    @ApiOperation(value = "根据病人除ID外属性批量获取病人信息", notes = "可以根据病人除ID外属性获取病人信息。具有分页功能。")
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
    public Result<ArrayList<Patient>> getPatientInfo(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "gender",required = false)String gender,
            @RequestParam(value = "birthday",required = false)String birthday,
            @RequestParam(value = "onset_place",required = false)String onset_place,
            @RequestParam(value = "onset_date",required = false)String onset_date,
            @RequestParam(value = "confirm_date",required = false)String confirm_date,
            @RequestParam(value = "status",required = false)String status,
            @RequestParam(value = "doctor_id",required = false)String doctor_id,
            @RequestParam(value = "hospital_id",required = false)String hospital_id,
            @RequestParam(value = "is_doctor",required = false)String is_doctor,
            @RequestParam Integer page, // 分页
            @RequestParam Integer size
    )
    {
        Page<HashMap<String, String>> pageInfo = PageHelper.startPage(page, size);
        ArrayList<HashMap<String, String>> list = getPatientInfoService.getPatientInfo(
                name,
                gender,
                birthday,
                onset_place,
                onset_date,
                confirm_date,
                status,
                doctor_id,
                hospital_id,
                is_doctor
        );
        if (list == null) return null;
        return Result.pagedOk(pageInfo, pageInfo.getTotal());
    }
}
