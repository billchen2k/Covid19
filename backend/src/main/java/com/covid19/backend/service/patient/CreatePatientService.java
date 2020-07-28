package com.covid19.backend.service.patient;

import com.covid19.backend.model.Patient;
import com.covid19.backend.service.BaseService;
import com.covid19.backend.utils.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class CreatePatientService extends BaseService{
    /**
     * 检查用户是否登陆
     * @param request
     * @return
     */
    public int checkCurrentUserInfo(HttpServletRequest request)
    {
        Long uid = Authentication.getCurrentUid(request);
        if (uid == null || uid != 0) return -1;
        return 0;
    }

    /**
     * 提供创建病人的服务
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
    public Patient createPatient(
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
        Patient patient = new Patient();
        patient.setName(name).setGender(gender).setBirthday(birthday).setOnset_place(onset_place).setOnset_date(onset_date);
        patient.setConfirm_date(confirm_date).setStatus(status).setDoctor_id(doctor_id).setHospital_id(hospital_id).setIs_doctor(is_doctor);
        patientMapper.insertPatient(patient);
        return patient;
    }
}
