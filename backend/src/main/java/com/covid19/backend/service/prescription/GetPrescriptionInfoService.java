package com.covid19.backend.service.prescription;

import com.covid19.backend.dao.PrescriptionMapper;
import com.covid19.backend.model.Prescription;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class GetPrescriptionInfoService extends BaseService{
    /**
     * 根据用药ID获取用药信息
     */
    public Prescription getPrescriptionInfoByID(long prescription_id){
        return prescriptionMapper.selectPrescriptionByID(prescription_id);
    }

    public Integer getPrescriptionNumber(){
        return prescriptionMapper.getNumber();
    }

    /**
     * 根据病人ID获取用药信息
     */
    public ArrayList<HashMap<String,String>> gePrescriptionInfoByPatientID(String patient_id){
        return prescriptionMapper.selectPrescriptionByPatientID(patient_id);
    }
    /**
     * 根据用药其他属性获取用药信息
     */
    public ArrayList<Prescription> getPrescriptionInfo(
            String patient_id,
            String medicine_id,
            String dosage,
            String usage,
            String doctor_id
    )
    {
        if(patient_id == null) patient_id = "%";
        if(medicine_id == null) medicine_id = "%";
        if(dosage == null) dosage = "%";
        if(usage == null) usage = "%";
        if(doctor_id == null) doctor_id = "%";

        return prescriptionMapper.selectPrescription(
                patient_id,
                medicine_id,
                dosage,
                usage,
                doctor_id);
    }

    public ArrayList<Prescription> getPrescriptionByPatientId(long patientId){
        return prescriptionMapper.selectPrescriptionByPatientId(patientId);
    }
}
