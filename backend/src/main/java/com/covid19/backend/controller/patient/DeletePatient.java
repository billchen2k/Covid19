package com.covid19.backend.controller.patient;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Patient;
import com.covid19.backend.service.patient.DeletePatientService;
import com.covid19.backend.service.patient.GetPatientInfoService;
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
@Api(tags = "病人控制器", value = "和病人有关的控制器")
public class DeletePatient {
    @Autowired
    public DeletePatientService deletePatientService;
    @Autowired
    public GetPatientInfoService getPatientInfoService;

    @PostMapping("/patient/deletePatientByID")
    @ApiOperation(value = "删除病人信息",notes = "可以删除病人的信息")
    @ApiImplicitParam(name = "patient_id",value = "病人ID")
    public Result deletePatientByID(
            @RequestParam(value = "patient_id") long patient_id,
            HttpServletRequest request)
    {
        if(deletePatientService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Patient patient = getPatientInfoService.getPatientInfoByID(patient_id);
        if(patient == null) return Result.error(2012,"不存在该病人");
        deletePatientService.deletePatientByID(patient_id);
        return Result.ok();
    }
}
