package com.covid19.backend.service.patient;

import com.covid19.backend.model.Patient;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class GetPatientInfoService extends BaseService {
    /**
     * 根据ID获取病人信息
     * @param patient_id
     * @return
     */
    public Patient getPatientInfoByID(long patient_id){
        return patientMapper.selectPatientByID(patient_id);
    }

    public HashMap<String, String>getDetailedPatiendInfoByID(long patient_id) {
        return patientMapper.selectDetailedPatientByID(patient_id);
    }
    /**
     * 根据病人其他属性获取病人信息
     * @param name
     * @param gender
     * @param birthday
     * @param onset_place
     * @param onset_date
     * @param confirm_date
     * @param status
     * @param doctor_id
     * @param hospital_id
     * @param is_doctor
     * @return
     */
    public ArrayList<Patient> getPatientInfo(
            String name,
            String gender,
            String birthday,
            String onset_place,
            String onset_date,
            String confirm_date,
            String status,
            String doctor_id,
            String hospital_id,
            String is_doctor
    )
    {
        if(name == null) name = "%";
        if(gender == null) gender = "%";
        if(birthday == null) birthday = "%";
        if(onset_place == null) onset_place = "%";
        if(onset_date == null) onset_date = "%";
        if(confirm_date == null) confirm_date = "%";
        if(status == null) status = "%";
        if(doctor_id == null) doctor_id = "%";
        if(hospital_id == null) hospital_id = "%";
        if(is_doctor == null) is_doctor = "%";

        return patientMapper.selectPatient(
                name,
                gender,
                birthday,
                onset_place,
                onset_date,
                confirm_date,
                status,
                doctor_id,
                hospital_id,
                is_doctor);
    }
}
