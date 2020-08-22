package com.covid19.backend.service.diagnosis;

import com.covid19.backend.model.Result;
import com.covid19.backend.model.Diagnosis;
import com.covid19.backend.service.BaseService;
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
public class DeleteDiagnosisService extends BaseService {

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null) return -1;
        return 0;
    }


    public int deleteDiagnosisByID(long diagnosis_id)
    {
        diagnosisMapper.deleteDiagnosisByID(diagnosis_id);
        return 0;
    }

    public int deleteDiagnosis(Diagnosis diagnosis)
    {

        ArrayList<Diagnosis> Diagnosises = diagnosisMapper.selectDiagnosis(diagnosis);
        for(Diagnosis t_diagnosis: Diagnosises){
            deleteDiagnosisByID(t_diagnosis.getDiagnosis_id());
        }
        return 0;
    }

}
