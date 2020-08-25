package com.covid19.backend.controller.prescription;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Prescription;
import com.covid19.backend.service.prescription.DeletePrescriptionService;
import com.covid19.backend.service.prescription.GetPrescriptionInfoService;
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
@Api(tags = "用药控制器", value = "和用药有关的控制器")
public class DeletePrescription {
    @Autowired
    public DeletePrescriptionService deletePrescriptionService;
    @Autowired
    public GetPrescriptionInfoService getPrescriptionInfoService;

    @PostMapping("/prescription/deletePrescriptionByID")
    @ApiOperation(value = "删除用药信息",notes = "可以删除用药的信息")
    @ApiImplicitParam(name = "prescription_id",value = "用药ID")
    public Result deletePrescriptionByID(
            @RequestParam(value = "prescription_id") long prescription_id,
            HttpServletRequest request)
    {
        if(deletePrescriptionService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Prescription prescription = getPrescriptionInfoService.getPrescriptionInfoByID(prescription_id);
        if(prescription == null) return Result.error(2012,"不存在该用药记录");
        deletePrescriptionService.deletePrescriptionByID(prescription_id);
        return Result.ok();
    }
}
