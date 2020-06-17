package com.covid19.backend.controller.prescription;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Prescription;
import com.covid19.backend.service.prescription.GetPrescriptionInfoService;
import com.covid19.backend.service.prescription.UpdatePrescriptionService;
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
 * 更新用药控制器
 */
@RestController
@Api(tags = "用药控制器", value = "和用药有关的控制器")
public class UpdatePrescription extends BaseController{
    @Autowired
    public UpdatePrescriptionService updatePrescriptionService;
    @Autowired
    public GetPrescriptionInfoService getPrescriptionInfoService;

    @PostMapping("/prescription/updatePrescription")
    @ApiOperation(value = "更新用药信息", notes = "可以更新用药信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prescription_id",value = "用药ID"),
            @ApiImplicitParam(name = "patient_id",value = "病人ID"),
            @ApiImplicitParam(name = "medicine_id",value = "药品ID"),
            @ApiImplicitParam(name = "dosage",value = "剂量"),
            @ApiImplicitParam(name = "usage",value = "用法")
    })
    public Result updatePrescription(
            @RequestParam(value = "prescription_id") long prescription_id,
            @RequestParam(value = "patient_id") String patient_id,
            @RequestParam(value = "medicine_id")String medicine_id,
            @RequestParam(value = "dosage",required = false)String dosage,
            @RequestParam(value = "usage",required = false)String usage,
            HttpServletRequest request)
    {
        if(updatePrescriptionService.checkCurrentUserInfo(request) == -1)
            return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Prescription prescription = getPrescriptionInfoService.gePrescriptionInfoByID(prescription_id);
        if(prescription == null) return Result.error(2012,"不存在该用药信息");

        Prescription new_prescription = updatePrescriptionService.updatePrescription(
                prescription_id,
                patient_id,
                medicine_id,
                dosage,
                usage);
        return Result.ok(prescription_id);
    }
}
