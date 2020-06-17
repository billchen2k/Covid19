package com.covid19.backend.service.diagnosis;

import com.covid19.backend.model.Diagnosis;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreateDiagnosisService extends BaseService{

    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }

    public Diagnosis createDiagnosis(Diagnosis diagnosis)
    {
        long id = diagnosisMapper.insertDiagnosis(diagnosis);
        diagnosis.setDiagnosis_id(id);
        return diagnosis;
    }
}

