package com.covid19.backend.controller.diagnosis;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Diagnosis;
import com.covid19.backend.service.diagnosis.GetDiagnosisInfoService;
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
@Api(tags = "诊断控制器", value = "和诊断有关的控制器")
public class GetDiagnosisInfo {
    @Autowired
    public GetDiagnosisInfoService getDiagnosisInfoService;

    @PostMapping("/diagnosis/getDiagnosisInfoByID")
    @ApiOperation(value = "根据诊断ID获取诊断信息", notes = "可以根据诊断ID获取诊断信息")
    @ApiImplicitParam(name = "diagnosis_id",value = "诊断ID")
    public Result<Diagnosis> getDiagnosisInfoByID(
            @RequestParam(value = "diagnosis_id") long diagnosis_id
    )
    {
        Diagnosis diagnosis = getDiagnosisInfoService.getDiagnosisInfoByID(diagnosis_id);
        if(diagnosis == null) return Result.error(2012,"不存在该诊断");
        return Result.ok(diagnosis);
    }

    @PostMapping("/diagnosis/getDiagnosisInfo")
    @ApiOperation(value = "根据诊断除ID外属性获取诊断信息", notes = "可以根据诊断除ID外属性获取诊断信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patient_id",value = "病人ID"),
            @ApiImplicitParam(name = "doctor_id",value = "医生 ID"),
            @ApiImplicitParam(name = "time",value = "诊断时间"),
            @ApiImplicitParam(name = "symptom",value = "症状"),
            @ApiImplicitParam(name = "temperature",value = "体温"),
            @ApiImplicitParam(name = "nucleic_acid",value = "核酸检测结果"),
    })
    public Result<ArrayList<Diagnosis>> getDiagnosisInfo(
            @RequestParam(value = "patient_id",required = false)String patient_id,
            @RequestParam(value = "doctor_id",required = false)String doctor_id,
            @RequestParam(value = "time",required = false)String time,
            @RequestParam(value = "symptom",required = false)String symptom,
            @RequestParam(value = "temperature",required = false)String temperature,
            @RequestParam(value = "nucleic_acid",required = false)String nucleic_acid
    )
    {
        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setPatient_id(patient_id);
        diagnosis.setDoctor_id(doctor_id);
        diagnosis.setTime(time);
        diagnosis.setSymptom(symptom);
        diagnosis.setTemperature(temperature);
        diagnosis.setNucleic_acid(nucleic_acid);

        ArrayList<Diagnosis> list = getDiagnosisInfoService.getDiagnosisInfo(diagnosis);
        if (list == null) return null;
        return Result.ok(list);
    }
}
