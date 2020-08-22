package com.covid19.backend.service.diagnosis;

import com.covid19.backend.controller.BaseController;
import com.covid19.backend.model.Result;
import com.covid19.backend.model.Diagnosis;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.service.diagnosis.GetDiagnosisInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.covid19.backend.utils.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class UpdateDiagnosisService extends BaseService{
    @Autowired
    public GetDiagnosisInfoService getDiagnosisInfoService;

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null) return -1;
        return 0;
    }

    public Diagnosis updateDiagnosis(Diagnosis new_diagnosis)
    {
        Diagnosis diagnosis = getDiagnosisInfoService.getDiagnosisInfoByID(new_diagnosis.getDiagnosis_id());

        diagnosis.setPatient_id(new_diagnosis.getPatient_id());
        diagnosis.setDoctor_id(new_diagnosis.getDoctor_id());
        diagnosis.setTime(new_diagnosis.getTime());
        diagnosis.setSymptom(new_diagnosis.getSymptom());
        diagnosis.setTemperature(new_diagnosis.getTemperature());
        diagnosis.setNucleic_acid(new_diagnosis.getNucleic_acid());

        diagnosisMapper.updateDiagnosis(diagnosis);
        return diagnosis;
    }
}
