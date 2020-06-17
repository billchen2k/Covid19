package com.covid19.backend.controller.diagnosis;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Diagnosis;
import com.covid19.backend.service.diagnosis.DeleteDiagnosisService;
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
public class DeleteDiagnosis {
    @Autowired
    public DeleteDiagnosisService deleteDiagnosisService;
    @Autowired
    public GetDiagnosisInfoService getDiagnosisInfoService;

    @PostMapping("/diagnosis/deleteDiagnosisByID")
    @ApiOperation(value = "删除诊断信息",notes = "可以删除诊断信息")
    @ApiImplicitParam(name = "diagnosis_id",value = "诊断ID")
    public Result deleteDiagnosisByID(
            @RequestParam(value = "diagnosis_id") long diagnosis_id,
            HttpServletRequest request)
    {
        if(deleteDiagnosisService.checkCurrentUserInfo(request) == -1) return Result.error(Result.CODE_UNAUTHORIZED, "账号信息错误。");
        Diagnosis diagnosis = getDiagnosisInfoService.getDiagnosisInfoByID(diagnosis_id);
        if(diagnosis == null) return Result.error(2012,"不存在该诊断");
        deleteDiagnosisService.deleteDiagnosisByID(diagnosis_id);
        return Result.ok();
    }
}
