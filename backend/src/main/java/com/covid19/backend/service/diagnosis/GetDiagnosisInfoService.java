package com.covid19.backend.service.diagnosis;

import com.covid19.backend.dao.DiagnosisMapper;
import com.covid19.backend.model.Diagnosis;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GetDiagnosisInfoService extends BaseService{

    public Diagnosis getDiagnosisInfoByID(long diagnosis_id)
    {
        return diagnosisMapper.selectDiagnosisByID(diagnosis_id);
    }

    public Integer getDiagnosisNumber(){
        return diagnosisMapper.getNumber();
    }

    public ArrayList<Diagnosis> getDiagnosisInfo(Diagnosis diagnosis) {
        if(diagnosis.getPatient_id() == null) diagnosis.setPatient_id("%");
        if(diagnosis.getDoctor_id() == null) diagnosis.setDoctor_id("%");
        if(diagnosis.getTime() == null) diagnosis.setTime("%");
        if(diagnosis.getSymptom() == null) diagnosis.setSymptom("%");
        if(diagnosis.getTemperature() == null) diagnosis.setTemperature("%");
        if(diagnosis.getNucleic_acid() == null) diagnosis.setNucleic_acid("%");
        return diagnosisMapper.selectDiagnosis(diagnosis);
    }
}
