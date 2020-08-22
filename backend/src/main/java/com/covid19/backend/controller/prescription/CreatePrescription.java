package com.covid19.backend.controller.prescription;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Prescription;
import com.covid19.backend.service.prescription.CreatePrescriptionService;
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


@RestController
@Api(tags = "用药控制器", value = "和用药有关的控制器")
public class CreatePrescription extends BaseController{
    @Autowired
    public CreatePrescriptionService createPrescriptionService;

    @PostMapping("/prescription/createPrescription")
    @ApiOperation(value = "创建用药信息", notes = "可以创建用药信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "patient_id",value = "病人 ID"),
            @ApiImplicitParam(name = "medicine_id",value = "药品 ID"),
            @ApiImplicitParam(name = "dosage",value = "剂量"),
            @ApiImplicitParam(name = "usage",value = "药物使用方法"),
            @ApiImplicitParam(name = "doctor_id",value = "医生ID")
    })
    public Result createMedicine(
            @RequestParam(value = "patient_id") String patient_id,
            @RequestParam(value = "medicine_id")String medicine_id,
            @RequestParam(value = "doctor_id")String doctor_id,
            @RequestParam(value = "dosage",required = false)String dosage,
            @RequestParam(value = "usage",required = false)String usage,
            HttpServletRequest request)
    {
        if(createPrescriptionService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");

        Prescription new_prescription = createPrescriptionService.createPrescription(
                patient_id,
                medicine_id,
                doctor_id,
                dosage,
                usage);
        return Result.ok(new_prescription);
    }
}
